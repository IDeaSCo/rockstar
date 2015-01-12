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
    @BeforeClass
    public static void setup(){
        createDepatment("SD")
        saveUser("test1.user1@ideas.com","test1","user1","SD","1");
        saveUser("test2.user2@ideas.com","test2","user2","SD","2");

    }



}
