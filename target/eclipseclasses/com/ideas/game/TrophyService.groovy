package com.ideas.game
import groovy.sql.Sql
import groovy.sql.GroovyRowResult


class TrophyService {

    //def dataSource
    def groovySql

    def saveTrophies(TrophyDTO trophyDTO) {
        
        
        User fromUser = getFromUser(trophyDTO)
        User toUser = getToUser(trophyDTO)
        
        TrohpyHistory history = new TrohpyHistory();
        history.setDate(new Date());
        history.setTrophies(trophyDTO.getTrohpies())
        history.setUser(toUser)
        history.setTrophiesGivenBy(fromUser)
        history.save(flush: true,  failOnError: true)
        
        TrohpyReason reason = new TrohpyReason();
        reason.setHistoryID(history)
        reason.setReason(trophyDTO.getReason())
        reason.save(flush: true,  failOnError: true)

        
        toUser.setTrophies(toUser.getTrophies()+trophyDTO.getTrohpies());
        toUser.save(flush: true,  failOnError: true)
        
    }
    
    private User getUser(String email){
        UserInfoService userInfoService = new UserInfoService();
        UserService userService = new UserService();
        User user = User.findByEmail(email);
        if(user == null){                                    
            UserInfoDTO dto = userInfoService.getUserInfoByEmail(email);
            user = userService.save(dto);
        }
        return user;
        
    }
    
    private User getFromUser(TrophyDTO trophyDTO){
        return getUser(trophyDTO.getFromUserEmailID());
    }

    private User getToUser(TrophyDTO trophyDTO){
        return getUser(trophyDTO.getToUserEmailID());
    }
    
    def getStarOfTheDay(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
         //println dataSource
        //Sql sql = new Sql(dataSource);
        println groovySql 
        def starOfTheDayMap = [:]
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date)=date(now()) group by 1 order by 2 desc limit 3';
        groovySql.eachRow( query ) { 
            int id = it.user_id;
            starOfTheDayMap.put(User.findById(id), it.trophies)
        }
        return starOfTheDayMap;
    }

    def getStarOfTheWeek(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
                //println dataSource
        //Sql sql = new Sql(dataSource);
        
        def starOfTheWeekMap = [:]
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date) between date_sub(date(now()), interval 7 day) and date(now()) group by 1 order by 2 desc limit 3';
        groovySql.eachRow( query ) { 
            int id = it.user_id;
            starOfTheWeekMap.put(User.findById(id), it.trophies)
        }
        return starOfTheWeekMap;
    }

    def getStarOfTheMonth(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
                //println dataSource
        //Sql sql = new Sql(dataSource);
        
        def starOfTheMonthMap = [:]
        String query = 'select user_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.user_id=user.id where department="'+department+'" and date(date) between date_sub(now(),interval DAYOFMONTH(now()) -1 day) and date(now()) group by 1 order by 2 desc limit 3';
        groovySql.eachRow( query ) { 
            int id = it.user_id;
            starOfTheMonthMap.put(User.findById(id), it.trophies)
        }
        return starOfTheMonthMap;
    }

    def getStar(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
                //println dataSource
        //Sql sql = new Sql(dataSource);
        
        def starMap = [:]
        String query = 'select id,trophies from user where department="'+department+'" order by 2 desc limit 3';
        groovySql.eachRow( query ) { 
            int id = it.id;
            starMap.put(User.findById(id), it.trophies)
        }
        return starMap;
    }
    
    def getDepartmentMap(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
                //println dataSource
        //Sql sql = new Sql(dataSource);
        
        def departmentMap = [:]
        String query = 'select id,trophies from user where department="'+department+'" order by 2 desc';
        groovySql.eachRow( query ) { 
            int id = it.id;                       
            departmentMap.put(User.findById(id), it.trophies)
        }
        return departmentMap;
    }
    
    
    def getAppreciator(String department){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
        //println dataSource
        //Sql sql = new Sql(dataSource);
        
        def appreciatorMap = [:]
        String query = 'select trophies_given_by_id,sum(trohpy_history.trophies) as trophies from trohpy_history inner join user  on trohpy_history.trophies_given_by_id=user.id where trohpy_history.trophies>0 and department="'+department+'" group by 1 order by 2 desc limit 3';
        
        groovySql.eachRow( query ) { 
            int id = it.trophies_given_by_id;
            appreciatorMap.put(User.findById(id), it.trophies)
        }
        return appreciatorMap;
    }

    def getHistory(User user){
        ////Sql sql = Sql.newInstance("jdbc:mysql://localhost:3309/game?autoReconnect=true", "admin", "admin", "com.mysql.jdbc.Driver");
        //println dataSource
        //Sql sql = new Sql(dataSource);
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

}

