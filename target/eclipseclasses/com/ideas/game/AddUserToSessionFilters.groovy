package com.ideas.game


class AddUserToSessionFilters {


    def filters = {
        allURIs(uri: '/**'){
            before = {
            println "==============================="
            println "controllerName:"+controllerName
            println "==============================="
                    if(controllerName.equals("trophy")){
                        return;
                    }
                    if(!session.userInfo){
                        UserInfoService userInfoService = new UserInfoService();
                        UserInfoDTO dto = userInfoService.getUserInfoByUserName(request.getRemoteUser());                        
                        User user =  User.findByEmail(dto.getEmail());                        
                        if(!user){
                            UserService userService = new UserService();
                            user = userService.save(dto)
                        }
                        session.userInfo=user;
                    }  

                    TrophyService trophyService = new TrophyService();
                    session.starOfTheDayMap = trophyService.getStarOfTheDay(session.userInfo.getDepartment());
                    session.starOfTheWeekMap = trophyService.getStarOfTheWeek(session.userInfo.getDepartment());
                    session.starOfTheMonthMap = trophyService.getStarOfTheMonth(session.userInfo.getDepartment());
                    session.starMap = trophyService.getStar(session.userInfo.getDepartment());
                    session.appreciatorMap = trophyService.getAppreciator(session.userInfo.getDepartment());
                    
                    session.departmentMap = trophyService.getDepartmentMap(session.userInfo.getDepartment());
                    session.userHistoryList = trophyService.getHistory(session.userInfo);
                
                    UserService userService = new UserService();
                    session.userEmailList = userService.getUserEmailList();
                    
                    
                    
            }
        }
    }
}
