package com.ideas.game


class AddUserToSessionFilters {

    def dataSource

    private stripDomainName(String userName){
        int index = userName.indexOf('\\');
        if(index>=0){
            return userName.substring(index+1);
        }
        return userName;
    }
    def filters = {
        allURIs(uri: '/**'){
            before = {

                    println "User:"+request.getRemoteUser()+" IP:"+request.getRemoteAddr();
                    BadgeService badgeService = new BadgeService(dataSource)
                    TrophyService trophyService = new TrophyService(dataSource);
                    if(request.getRemoteUser() != null) {
                        if(session.userInfo == null) {
                            User user = User.findByAccountName(stripDomainName(request.getRemoteUser()));
                            if (user == null) {
                                try {
                                    UserInfoService userInfoService = new UserInfoService();
                                    UserInfoDTO dto = userInfoService.getUserInfoByUserName(request.getRemoteUser());
                                    if (dto == null) return;

                                    UserService userService = new UserService();
                                    user = userService.save(dto)

                                }catch(Exception e){
                                    println("Could not query active directory for user:"+request.getRemoteUser());
                                }
                            }
                            session.userInfo = user;

                            if(controllerName.equals("trophy") && actionName.equals("save")) return;

                            session.badges = badgeService.listAvailableBadges(session.userInfo.getDepartment().departmentName);
                            session.starOfTheDayMap = trophyService.getStarOfTheDay(session.userInfo.getDepartment().departmentName);
                            session.starOfTheWeekMap = trophyService.getStarOfTheWeek(session.userInfo.getDepartment().departmentName);
                            session.starOfTheMonthMap = trophyService.getStarOfTheMonth(session.userInfo.getDepartment().departmentName);
                            session.starMap = trophyService.getStar(session.userInfo.getDepartment().departmentName);
                            session.appreciatorMap = trophyService.getAppreciator(session.userInfo.getDepartment().departmentName);
                            MissionService missionService = new MissionService(dataSource);
                            session.openMission = missionService.getOpenMissions();
                            session.badgeLeaderBoard = badgeService.getBadgeLeaderBoard(session.userInfo.getDepartment().departmentName);

                        }

                }
            }
        }
    }
}
