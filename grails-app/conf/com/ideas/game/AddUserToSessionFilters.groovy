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
                    if(request.getRemoteUser() != null) {

                        User user =  User.findByAccountName(stripDomainName(request.getRemoteUser()));
                        if(user == null){
                            UserInfoService userInfoService = new UserInfoService();
                            UserInfoDTO dto = userInfoService.getUserInfoByUserName(request.getRemoteUser());
                            if(dto == null) return;

                            UserService userService = new UserService();
                            user = userService.save(dto)

                        }

                        session.userInfo=user;

                        TrophyService trophyService = new TrophyService(dataSource);

                        session.starOfTheDayMap = trophyService.getStarOfTheDay(session.userInfo.getDepartment().departmentName);
                        session.starOfTheWeekMap = trophyService.getStarOfTheWeek(session.userInfo.getDepartment().departmentName);
                        session.starOfTheMonthMap = trophyService.getStarOfTheMonth(session.userInfo.getDepartment().departmentName);
                        session.starMap = trophyService.getStar(session.userInfo.getDepartment().departmentName);
                        session.appreciatorMap = trophyService.getAppreciator(session.userInfo.getDepartment().departmentName);

                        session.departmentMap = trophyService.getDepartmentMap(session.userInfo.getDepartment().departmentName);
                        session.userHistoryList = trophyService.getHistory(session.userInfo);
                        session.userDepartmentHistoryList = trophyService.getDepartmentHistory(session.userInfo);

                        MissionService missionService = new MissionService(dataSource);
                        session.openMission =
                                missionService.getOpenMissions();

                        BadgeService badgeService = new BadgeService(dataSource)
                        session.badges = badgeService.listAvailableBadges();
                        session.badgeLeaderBoard = badgeService.getBadgeLeaderBoard(session.userInfo.getDepartment().departmentName);
                        session.userBadges = badgeService.getBadgesForUser(session.userInfo);
                }
            }
        }
    }
}
