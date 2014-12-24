import com.ideas.game.Badge
import com.ideas.game.User

class BootStrap {

    def init = { servletContext ->
        createSeedBadges()

    }
    def destroy = {
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
        createBadge("fa-scissors","Build Breaker",true,0,0,0,9);
        createBadge("fa-bomb","Spillover Champ",true,0,0,0,10);
        createBadge("fa-maxcdn","Mission Impossible",false,100,500,1000,11);
        createBadge("fa-stack-overflow","Community Contributor",false,100,300,500,12);
        createBadge("fa-wordpress","Blogger",false,100,400,900,13);
        createBadge("fa-warning","Cheater",true,100,200,300,14);
        createBadge("fa-warning","Code Violator",true,100,200,300,15);
        createBadge("fa-warning","Code Saviour",false,100,200,300,16);
        createBadge("fa-warning","Process Violator",true,100,200,300,17);
        createBadge("fa-warning","Default",false,100,200,300,18);


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
