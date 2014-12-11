package com.ideas.game


class AddUserToSessionFilters {

    def dataSource

    def filters = {
        allURIs(uri: '/**'){
            before = {
                    println "User:"+request.getRemoteUser()+" IP:"+request.getRemoteAddr();
                    if(request.getRemoteUser() != null) {
                        if (!session.userInfo) {
                            UserInfoService userInfoService = new UserInfoService();
                            UserInfoDTO dto = userInfoService.getUserInfoByUserName(request.getRemoteUser());
                            if(dto == null) return;
                            User user =  User.findByEmail(dto.getEmail());

                            if(!user){
                                UserService userService = new UserService();
                                user = userService.save(dto)
                            }
                            session.userInfo=user;
                        }
                        TrophyService trophyService = new TrophyService(dataSource);
                        session.starOfTheDayMap = trophyService.getStarOfTheDay(session.userInfo.getDepartment());
                        session.starOfTheWeekMap = trophyService.getStarOfTheWeek(session.userInfo.getDepartment());
                        session.starOfTheMonthMap = trophyService.getStarOfTheMonth(session.userInfo.getDepartment());
                        session.starMap = trophyService.getStar(session.userInfo.getDepartment());
                        session.appreciatorMap = trophyService.getAppreciator(session.userInfo.getDepartment());

                        session.departmentMap = trophyService.getDepartmentMap(session.userInfo.getDepartment());
                        session.userHistoryList = trophyService.getHistory(session.userInfo);
                        session.userDepartmentHistoryList = trophyService.getDepartmentHistory(session.userInfo);

                        MissionService missionService = new MissionService();
                        session.openMission =
                                missionService.getOpenMissions();
                }
            }
        }
    }
}
