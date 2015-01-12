package com.ideas.game
import groovy.sql.Sql

class TrophyService  {
    def dataSource


    UserService userService = new UserService();
    def TrophyService(){

    }

    def TrophyService(def dataSource){
        this.dataSource = dataSource;
    }


    def saveTrophies(TrophyDTO trophyDTO) {
        trophyDTO.trophies = Math.abs(trophyDTO.trophies)

        User fromUser = getFromUser(trophyDTO)
        User toUser = getToUser(trophyDTO)
        
        TrophyHistory history = new TrophyHistory();
        history.setDate(new Date());


        saveTrophyHistory(history, trophyDTO, toUser, fromUser)
        
        TrophyReason reason = new TrophyReason();
        saveHistoryReason(reason, history, trophyDTO)

        if(!trophyDTO.badge.isEvil) {
            toUser.setTrophies(toUser.getTrophies() + trophyDTO.trophies);
            toUser.save(flush: true, failOnError: true)
        }

        BadgeService badgeService = new BadgeService();
        badgeService.updateUserBadePoints(toUser,trophyDTO.badge,trophyDTO.getTrophies())

    }

    private void saveHistoryReason(TrophyReason reason, TrophyHistory history, TrophyDTO trophyDTO) {
        reason.setHistoryID(history)
        reason.setReason(trophyDTO.getReason())
        reason.save(flush: true, failOnError: true)
    }

    private void saveTrophyHistory(TrophyHistory history, TrophyDTO trophyDTO, User toUser, User fromUser) {
        history.setTrophies(trophyDTO.getTrophies())
        history.setUser(toUser)
        history.setTrophiesGivenBy(fromUser)
        history.setBadge(trophyDTO.badge)
        history.setTrophies(trophyDTO.trophies)
        history.save(flush: true, failOnError: true)
    }


    private User getFromUser(TrophyDTO trophyDTO){
        return userService.getUser(trophyDTO.getFromUserEmailID());
    }

    private User getToUser(TrophyDTO trophyDTO){
        return userService.getUser(trophyDTO.getToUserEmailID());
    }


    def getStarOfTheDay(String department){

        Sql sql = new Sql(dataSource);
        def starOfTheDayMap = [:]
        String query = 'select user_id,sum(Trophy_history.trophies) as trophies from Trophy_history inner join user  on Trophy_history.user_id=user.id inner join department on user.department_id=department.id inner join badge on Trophy_history.badge_id=badge.id where department_name=\''+department+'\' and extract(year from date)=extract(year from CURRENT_DATE()) and extract(month from date)=extract(month from CURRENT_DATE()) and extract(day from date)=extract(day from CURRENT_DATE()) and badge.is_evil=0 group by user_id order by trophies desc limit 3';
        sql.eachRow( query ) { 
            int id = it.user_id;
            starOfTheDayMap.put(User.findById(id), it.trophies)
        }
        return starOfTheDayMap;
    }

    def getStarOfTheWeek(String department){
        Sql sql = new Sql(dataSource);
        
        def starOfTheWeekMap = [:]
        String query = 'select user_id,sum(Trophy_history.trophies) as trophies from Trophy_history inner join user  on Trophy_history.user_id=user.id inner join department on user.department_id=department.id inner join badge on Trophy_history.badge_id=badge.id  where department_name="'+department+'" and date(date) between date_sub(date(now()), interval 7 day) and date(now()) and badge.is_evil=0  group by 1 order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.user_id;
            starOfTheWeekMap.put(User.findById(id), it.trophies)
        }
        return starOfTheWeekMap;
    }

    def getStarOfTheMonth(String department){
        Sql sql = new Sql(dataSource);

        def starOfTheMonthMap = [:]
        String query = 'select user_id,sum(Trophy_history.trophies) as trophies from Trophy_history inner join user  on Trophy_history.user_id=user.id inner join department on user.department_id=department.id inner join badge on Trophy_history.badge_id=badge.id  where department_name="'+department+'" and date(date) between date_sub(now(),interval DAYOFMONTH(now()) -1 day) and date(now()) and badge.is_evil=0  group by 1 order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.user_id;
            starOfTheMonthMap.put(User.findById(id), it.trophies)
        }
        return starOfTheMonthMap;
    }

    def getStar(String department){
        Sql sql = new Sql(dataSource);
        def starMap = [:]
        String query = 'select user.id,trophies from user inner join department on user.department_id = department.id where department_name="'+department+'" order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.id;
            starMap.put(User.findById(id), it.trophies)
        }
        return starMap;
    }
    
    def getDepartmentMap(String department){
        Sql sql = new Sql(dataSource);

        def departmentMap = [:]
        String query = 'select user.id,trophies from user inner join department on user.department_id=department.id where department_name="'+department+'" order by 2 desc';
        sql.eachRow( query ) {
            int id = it.id;                       
            departmentMap.put(User.findById(id), it.trophies)
        }
        return departmentMap;
    }
    
    
    def getAppreciator(String department){
        Sql sql = new Sql(dataSource);
        def appreciatorMap = [:]
        String query = 'select trophies_given_by_id,sum(Trophy_history.trophies) as trophies from Trophy_history inner join user  on Trophy_history.trophies_given_by_id=user.id inner join department on user.department_id=department.id inner join badge on Trophy_history.badge_id=badge.id  where department_name="'+department+'"  and badge.is_evil=0  group by 1 order by 2 desc limit 10';
        
        sql.eachRow( query ) {
            int id = it.trophies_given_by_id;
            appreciatorMap.put(User.findById(id), it.trophies)
        }
        return appreciatorMap;
    }


    def getReasonsForBadge(def userBadgeList){
        def reasonOfStarsReceivedByBadge = [:]

        userBadgeList.each(){
            List historyList = TrophyHistory.findAll("from TrophyHistory as a where  user.id="+it.user.id+" and a.badge.id="+it.badge.id+" order by date desc",[max: 30]);
            def listOfReasons = [];
            historyList.each(){
                def trophieReasonList = [];
                trophieReasonList.add(it);
                trophieReasonList.add(TrophyReason.findByHistoryID(it)?.reason)
                listOfReasons.add(trophieReasonList);
            }
            reasonOfStarsReceivedByBadge.put(it.badge.id,listOfReasons);
        }

        return reasonOfStarsReceivedByBadge;
    }

    def getHistory(User user){
        def historyList = []
		List list  = TrophyHistory.findAllByUser(user,[sort: "date", order: "desc", max: 30]);
        list.each(){
            def trophieReasonList = [];
            trophieReasonList.add(it.trophies)            
            trophieReasonList.add(TrophyReason.findByHistoryID(it)?.reason)
            trophieReasonList.add(it.trophiesGivenBy)
            trophieReasonList.add(it.date)            
            
            historyList.add(trophieReasonList)
        }

        return historyList;
    }

    def getDepartmentHistory(User user){
        def historyList = []
        List list  = TrophyHistory.findAll("from TrophyHistory as a where a.trophies>0 and user.id<>"+user.id+" and  user.department.departmentName='"+user.department.departmentName+"' order by date desc",[max: 60]);
        list.each(){
            def trophieReasonList = [];
            trophieReasonList.add(it.trophies)
            trophieReasonList.add(TrophyReason.findByHistoryID(it)?.reason)
            trophieReasonList.add(it.user)
            trophieReasonList.add(it.date)

            historyList.add(trophieReasonList)
        }

        return historyList;
    }


}

