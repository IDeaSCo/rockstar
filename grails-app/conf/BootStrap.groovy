import com.ideas.game.Badge
import com.ideas.game.Department
import com.ideas.game.DepartmentBadges
import com.ideas.game.User

class BootStrap {

    def init = { servletContext ->
        createSeedBadges()
        createDefaultDepartment()
        setupCommonBadges()
    }
    def destroy = {
    }

    def createDefaultDepartment(){
        Department department = new Department();
        department.departmentName="Default";
        department.save(flush: true,  failOnError: false);
    }
    def createSeedBadges(){
        createBadge("fa-line-chart","Test Champ",false,50,200,500,1);
        createBadge("fa-cloud-upload","Frequent Code Checker",false,50,150,350,2);
        createBadge("fa-key","Delivery Champ",false,100,400,900,3);
        createBadge("fa-lightbulb-o","Innovator",false,75,200,400,4);
        createBadge("fa-group","Team Player",false,20,100,300,5);
        createBadge("fa-file-movie-o","Presentation Champ",false,100,300,500,6);
        createBadge("fa-sort-alpha-asc","Process Champ",false,50,300,900,7);
        createBadge("fa-thumbs-up","Appreciator",false,50,300,900,8);
        createBadge("fa-scissors","Build Breaker",true,50,150,300,100);
        createBadge("fa-bolt","Spillover Champ",true,60,200,400,101);
        createBadge("fa-maxcdn","Mission Impossible",false,100,500,1000,9);
        createBadge("fa-stack-overflow","Community Contributor",false,100,300,500,10);
        createBadge("fa-wordpress","Blogger",false,100,400,900,11);
        createBadge("fa-pied-piper-alt","Cheater",true,100,200,300,102);
        createBadge("fa-bug","Code Violator",true,100,200,300,103);
        createBadge("fa-magic","Code Saviour",false,100,200,300,12);
        createBadge("fa-cogs","Process Violator",true,50,200,400,104);
        createBadge("fa-check","Default",false,100,200,300,99);
        createBadge("fa-fire-extinguisher","Fire Fighter",false,100,200,300,13);
        createBadge("fa-bug","Bug Master",true,100,200,300,105);
    }

    def setupCommonBadges(){

        Department defaultDepartment = Department.findByDepartmentName("Default");
        createDepartmentBadge(defaultDepartment,"Innovator");
        createDepartmentBadge(defaultDepartment,"Team Player");
        createDepartmentBadge(defaultDepartment,"Presentation Champ");
        createDepartmentBadge(defaultDepartment,"Process Champ");
        createDepartmentBadge(defaultDepartment,"Appreciator");
        createDepartmentBadge(defaultDepartment,"Community Contributor");
        createDepartmentBadge(defaultDepartment,"Blogger");
        createDepartmentBadge(defaultDepartment,"Process Violator");

    }

    private void createDepartmentBadge(Department defaultDepartment, String badgeName) {
        DepartmentBadges departmentBadge = new DepartmentBadges();
        departmentBadge.department = defaultDepartment
        departmentBadge.badge = Badge.findByBadgeName(badgeName)
        departmentBadge.save(flush: true,  failOnError: false);
    }

    def createBadge(String badgeIcon, String badgeName, boolean isEvil, int levelOnePoints, int levelTwoPoints, int levelThreePoints, int displayOrder  ){
        Badge badge = new Badge();
        badge.badgeIcon=badgeIcon;
        badge.badgeName=badgeName;
        badge.isEvil=isEvil;
        badge.levelOnePoints=levelOnePoints;
        badge.levelTwoPoints=levelTwoPoints;
        badge.levelThreePoints=levelThreePoints;
        badge.displayOrder=displayOrder
        badge.save(flush: true,  failOnError: false);
    }

}
