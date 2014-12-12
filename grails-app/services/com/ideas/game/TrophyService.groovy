package com.ideas.game
import groovy.sql.Sql
import groovy.sql.GroovyRowResult
import org.codehaus.groovy.grails.commons.GrailsApplication



class TrophyService  {
    def dataSource

    void testSomething() {
        def var = TrohpyReason.findAll()
        var.each {

            try {
                TrohpyReasonBack back = new TrohpyReasonBack();
                back.historyID = it.historyID;
                back.reason = it.reason;
                back.save(flush: true, failOnError: false)
            }catch(Exception e){
                e.printStackTrace()

            }

        }
    }

    UserService userService = new UserService();

    TrophyService(def dataSource){
        this.dataSource = dataSource;
    }

    def saveTrophies(TrophyDTO trophyDTO) {


        User fromUser = getFromUser(trophyDTO)
        User toUser = getToUser(trophyDTO)
        
        TrohpyHistory history = new TrohpyHistory();
        history.setDate(new Date());
        history.setTrophies(trophyDTO.getTrohpies())
        history.setUser(toUser)
        history.setTrophiesGivenBy(fromUser)
        history.setBadge(trophyDTO.badge)
        history.save(flush: true,  failOnError: true)
        
        TrohpyReason reason = new TrohpyReason();
        reason.setHistoryID(history)
        reason.setReason(trophyDTO.getReason())
        reason.save(flush: true,  failOnError: true)

        
        toUser.setTrophies(toUser.getTrophies()+trophyDTO.getTrohpies());
        toUser.save(flush: true,  failOnError: true)

        BadgeService badgeService = new BadgeService();
        badgeService.updateUserBadePoints(toUser,trophyDTO.badge,trophyDTO.getTrohpies())

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
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date)=date(now()) group by 1 order by 2 desc limit 3';
        sql.eachRow( query ) { 
            int id = it.user_id;
            starOfTheDayMap.put(User.findById(id), it.trophies)
        }
        return starOfTheDayMap;
    }

    def getStarOfTheWeek(String department){
        Sql sql = new Sql(dataSource);
        
        def starOfTheWeekMap = [:]
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date) between date_sub(date(now()), interval 7 day) and date(now()) group by 1 order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.user_id;
            starOfTheWeekMap.put(User.findById(id), it.trophies)
        }
        return starOfTheWeekMap;
    }

    def getStarOfTheMonth(String department){
        Sql sql = new Sql(dataSource);

        def starOfTheMonthMap = [:]
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date) between date_sub(now(),interval DAYOFMONTH(now()) -1 day) and date(now()) group by 1 order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.user_id;
            starOfTheMonthMap.put(User.findById(id), it.trophies)
        }
        return starOfTheMonthMap;
    }

    def getStar(String department){
        Sql sql = new Sql(dataSource);
        def starMap = [:]
        String query = 'select id,trophies from user where department="'+department+'" order by 2 desc limit 3';
        sql.eachRow( query ) {
            int id = it.id;
            starMap.put(User.findById(id), it.trophies)
        }
        return starMap;
    }
    
    def getDepartmentMap(String department){
        Sql sql = new Sql(dataSource);

        def departmentMap = [:]
        String query = 'select id,trophies from user where department="'+department+'" order by 2 desc';
        sql.eachRow( query ) {
            int id = it.id;                       
            departmentMap.put(User.findById(id), it.trophies)
        }
        return departmentMap;
    }
    
    
    def getAppreciator(String department){
        Sql sql = new Sql(dataSource);
        def appreciatorMap = [:]
        String query = 'select trophies_given_by_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.trophies_given_by_id=user.id where trohpy_history.trophies>0 and department="'+department+'" group by 1 order by 2 desc limit 10';
        
        sql.eachRow( query ) {
            int id = it.trophies_given_by_id;
            appreciatorMap.put(User.findById(id), it.trophies)
        }
        return appreciatorMap;
    }

    def getHistory(User user){
        def historyList = []
		List list  = TrohpyHistory.findAllByUser(user,[sort: "date", order: "desc", max: 30]);        
        list.each(){
            def trophieReasonList = [];
            trophieReasonList.add(it.trophies)            
            trophieReasonList.add(TrohpyReason.findByHistoryID(it)?.reason)
            trophieReasonList.add(it.trophiesGivenBy)
            trophieReasonList.add(it.date)            
            
            historyList.add(trophieReasonList)
        }

        return historyList;
    }

    def getDepartmentHistory(User user){
        def historyList = []
        List list  = TrohpyHistory.findAll("from TrohpyHistory as a where a.trophies>0 and user.id<>"+user.id+" and  user.department='"+user.department+"' order by date desc",[max: 60]);
        list.each(){
            def trophieReasonList = [];
            trophieReasonList.add(it.trophies)
            trophieReasonList.add(TrohpyReason.findByHistoryID(it)?.reason)
            trophieReasonList.add(it.user)
            trophieReasonList.add(it.date)

            historyList.add(trophieReasonList)
        }

        return historyList;
    }


}

