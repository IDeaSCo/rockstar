package com.ideas.game

/**
 * Created by idnasr on 12/11/2014.
 */
class UserBadges {
    User user
    Badge badge
    int points

    static mapping = {
        user lazy: false
        badge lazy: false
    }
    static constraints = {
        user(unique:['badge'])
    }

    String getLevelName(){
        return getLevelName(points)
    }
    String getLevelName(int currentPoints){
        if(currentPoints < badge.levelOnePoints ){
            return "Amature";
        } else if(currentPoints <  badge.levelTwoPoints ){
            return "Bronze";
        }else if(currentPoints <  badge.levelThreePoints ){
            return "Silver";
        }else{
            return "Gold";
        }
    }

    int getLevel(){
        if(points < badge.levelOnePoints ){
            return 0;
        } else if(points <  badge.levelTwoPoints ){
            return 1;
        }else if(points <  badge.levelThreePoints ){
            return 2;
        }else{
            return 3;
        }
    }

    int getLevelProgress(){
        if(points < badge.levelOnePoints ){
            return points*100/badge.levelOnePoints;
        } else if(points <  badge.levelTwoPoints ){
            return (points - badge.levelOnePoints)*100/(badge.levelTwoPoints - badge.levelOnePoints);
        }else if(points <  badge.levelThreePoints ){
            return (points - badge.levelTwoPoints)*100/(badge.levelThreePoints - badge.levelTwoPoints);
        }else{
            return (points - badge.levelThreePoints)*100/(points+500 - badge.levelThreePoints);
        }

    }

    int getLevelMaxPoints(){
        if(points < badge.levelOnePoints ){
            return badge.levelOnePoints;
        } else if(points <  badge.levelTwoPoints ){
            return badge.levelTwoPoints;
        }else if(points <  badge.levelThreePoints ){
            return badge.levelThreePoints;
        }else{
            return points+500;
        }
    }

    String getLevelColor() {
        int level = getLevel();
        return getLevelColor(level)
    }
    String getLevelColor(int level){

        if(level == 0 ){
            return "#008000";
        }else if(level == 1 ){
            return "#cd7f32";
        } else if(level == 2){
            return "#C0C0C0";
        }else{
            return "#FFD700";
        }
    }
}
