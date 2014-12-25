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

    int getLevel(){
        if(points < badge.levelOnePoints ){
            return 1;
        } else if(points <  badge.levelTwoPoints ){
            return 2;
        }else{
            return 3;
        }
    }

    String getLevelColor(){
        int level = getLevel();
        if(level == 1 ){
            return "#cd7f32";
        } else if(level == 2){
            return "#C0C0C0";
        }else{
            return "#FFD700";
        }
    }
}
