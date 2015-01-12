package com.ideas.game

import org.junit.*



class TrophyServiceTests {
    def dataSource;

    @Test
    public void saveTrophies_negative_stars_should_not_affect_the_user_star_total(){
        User user = User.findByEmail("test2.user2@ideas.com")
        user.trophies=0;
        user.save(flush: true, failOnError: true)

        TrophyService service = new TrophyService(dataSource);
        TrophyDTO dto = new TrophyDTO();
        dto.fromUserEmailID="test1.user1@ideas.com";
        dto.toUserEmailID="test2.user2@ideas.com";
        dto.trophies=-5;
        dto.badgeName="Process Violator";
        dto.badge=Badge.findByBadgeName("Process Violator");
        dto.reason="does not update rally";
        service.saveTrophies(dto);

        user = User.findByEmail("test2.user2@ideas.com");
        Assert.assertEquals(0,user.trophies)
        UserBadges userBadges = UserBadges.findByUserAndBadge(user,dto.badge);
        Assert.assertEquals(5,userBadges.points)
        TrophyHistory history = TrophyHistory.findByUserAndBadge(user,dto.badge)
        Assert.assertEquals(5,history.trophies)
        Assert.assertEquals(User.findByEmail("test1.user1@ideas.com"),history.trophiesGivenBy)
    }

    @Test
    public void saveTrophies_positive_stars_should_increment_user_star_total(){

        User user = User.findByEmail("test2.user2@ideas.com")
        user.trophies=7;
        user.save(flush: true, failOnError: true)

        TrophyService service = new TrophyService(dataSource);
        TrophyDTO dto = new TrophyDTO();
        dto.fromUserEmailID="test1.user1@ideas.com";
        dto.toUserEmailID="test2.user2@ideas.com";
        dto.trophies=5;
        dto.badgeName="Innovator";
        dto.badge=Badge.findByBadgeName(dto.badgeName);
        dto.reason="What an idea";
        service.saveTrophies(dto);

        user = User.findByEmail("test2.user2@ideas.com");
        Assert.assertEquals(12,user.trophies)
    }


    @Test
    public void getStarOfTheDay(){
        TrophyService service = new TrophyService(dataSource);
        def starOfTheDay = service.getStarOfTheDay("QA");
        Assert.assertEquals(3,starOfTheDay.size());
        Assert.assertEquals(7,starOfTheDay.get(User.findByEmail("test7.user7@ideas.com")));
        Assert.assertEquals(5,starOfTheDay.get(User.findByEmail("test6.user6@ideas.com")));
        Assert.assertEquals(3,starOfTheDay.get(User.findByEmail("test5.user5@ideas.com")));

    }

    def static saveUser(String email, String firstName, String lastName, String departmentName, String employeeId){
        User user = new User();
        user.email=email;
        user.department=Department.findByDepartmentName(departmentName);
        user.firstName=firstName;
        user.lastName=lastName;
        user.employeeId=employeeId
        user.accountName=employeeId
        user.title="";
        user.save(flush: true, failOnError: true);
    }

    private static void createDepatment(String departmentName){
        Department department = new Department();
        department.departmentName=departmentName;
        department.save(flush: true, failOnError: true);
    }

    private static void createHistory(String userEmail, String trophiesGivenBy, String badgeName, int trophies){
        TrophyHistory history = new TrophyHistory();
        history.trophies=trophies
        history.badge=Badge.findByBadgeName(badgeName)
        history.user=User.findByEmail(userEmail)
        history.trophiesGivenBy=User.findByEmail(trophiesGivenBy)
        history.date=new Date();
        history.save(flush: true, failOnError: true);
    }
    @BeforeClass
    public static void setup(){
        createDepatment("SD")
        createDepatment("QA")
        saveUser("test1.user1@ideas.com","test1","user1","SD","1");
        saveUser("test2.user2@ideas.com","test2","user2","SD","2");
        saveUser("test3.user3@ideas.com","test3","user3","SD","3");
        saveUser("test4.user4@ideas.com","test4","user4","QA","4");
        saveUser("test5.user5@ideas.com","test5","user5","QA","5");
        saveUser("test6.user6@ideas.com","test6","user6","QA","6");
        saveUser("test7.user7@ideas.com","test7","user7","QA","7");
        createHistory("test4.user4@ideas.com","test1.user1@ideas.com","Innovator",1);
        createHistory("test4.user4@ideas.com","test1.user1@ideas.com","Process Violator",2);
        createHistory("test5.user5@ideas.com","test1.user1@ideas.com","Innovator",3);
        createHistory("test5.user5@ideas.com","test1.user1@ideas.com","Process Violator",4);
        createHistory("test6.user6@ideas.com","test1.user1@ideas.com","Innovator",5);
        createHistory("test6.user6@ideas.com","test1.user1@ideas.com","Process Violator",6);
        createHistory("test7.user7@ideas.com","test1.user1@ideas.com","Innovator",7);
        createHistory("test7.user7@ideas.com","test1.user1@ideas.com","Process Violator",8);


    }



}
