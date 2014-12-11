package com.ideas.game

/**
 * Created by idnasr on 12/11/2014.
 */
class BadgeService {

    public def listAvailableBadges(){
        return Badge.findAll()
    }

    public def listNonEvilBadges(){
        return Badge.findAllByIsEvil(false);
    }

    public void updateUserBadePoints(User toUser, Badge badge, int points){
        UserBadges userBadge  = UserBadges.findByUserAndBadge(toUser,badge)
        if(userBadge == null){
            userBadge = new UserBadges()
            userBadge.badge=badge;
            userBadge.user=toUser;
        }
        userBadge.points+=points;

        userBadge.save(flush: true,  failOnError: true);
    }
}
