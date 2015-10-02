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
        createBadge("fa-line-chart","Test Champ","Write junit tests to collect this badge.",1,false,50,200,500,1);
        createBadge("fa-cloud-upload","Frequent Code Checker","Check-in your code frequently, integrate frequently and collect this badge.",1,false,50,150,350,2);
        createBadge("fa-key","Delivery Champ","Deliver your work on time to collect this badge.",10,false,100,400,900,3);
        createBadge("fa-lightbulb-o","Innovator","Come up with a creative solution, idea, implement it to collect this badge.",10,false,75,200,400,4);
        createBadge("fa-group","Team Player","Help team members to collect this badge.",1,false,20,100,300,5);
        createBadge("fa-file-movie-o","Presentation Champ","Share your knowledge to collect this badge.",30,false,100,300,500,6);
        createBadge("fa-sort-alpha-asc","Process Champ","Follow the processes to collect this badge.",1,false,50,300,900,7);
        createBadge("fa-thumbs-up","Appreciator","Appreciate others to collect this badge",1,false,50,300,900,8);
        createBadge("fa-scissors","Build Breaker","Producing an unstable build would result in this badge.",10,true,50,150,300,100);
        createBadge("fa-bolt","Spillover Champ","Not delivering the stories on time would result in this badge",20,true,60,200,400,101);
        createBadge("fa-maxcdn","Mission Impossible","Accept challenges posted, resolve them to collect this badge",1,false,100,500,1000,9);
        createBadge("fa-stack-overflow","Community Contributor","Contribute to opensource projects to collect this badge.",75,false,100,300,500,10);
        createBadge("fa-wordpress","Blogger","Write technical blogs, provide answers on stackoverflow, spread your knowledge to collect this badge",10,false,100,400,900,11);
        createBadge("fa-pied-piper-alt","Cheater","Cheating this system would result in this badge.",100,true,100,200,300,102);
        createBadge("fa-bug","Code Violator","When sonar, findbugs detects issues with your code, you will get this badge.",1,true,100,200,300,103);
        createBadge("fa-magic","Code Saviour","Fix issues highlighted by sonar, findbugs to collect this badge.",1,false,100,200,300,12);
        createBadge("fa-cogs","Process Violator","Not following processes would result in this badge",1,true,50,200,400,104);
        createBadge("fa-check","Default","When no matching category found use this badge.",0,false,100,200,300,99);
        createBadge("fa-fire-extinguisher","Fire Fighter","Provide help, support at the time of crisis to collect this badge.",10,false,100,200,300,13);
        createBadge("fa-bug","Bug Master","Client finding issues with your work would result in this badge",10,true,100,200,300,105);
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

    def createBadge(String badgeIcon, String badgeName, String description, int starsAwarded, boolean isEvil, int levelOnePoints, int levelTwoPoints, int levelThreePoints, int displayOrder  ){
        Badge badge = new Badge();
        badge.badgeIcon=badgeIcon;
        badge.badgeName=badgeName;
        badge.description=description;
        badge.starsAwarded=starsAwarded;
        badge.isEvil=isEvil;
        badge.levelOnePoints=levelOnePoints;
        badge.levelTwoPoints=levelTwoPoints;
        badge.levelThreePoints=levelThreePoints;
        badge.displayOrder=displayOrder
        badge.save(flush: true,  failOnError: false);
    }

}
