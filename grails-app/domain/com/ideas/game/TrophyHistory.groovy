package com.ideas.game

class TrophyHistory {
    int id
    User user;
    Date date;
    int trophies;
    User trophiesGivenBy;
    Badge badge
    
    static constraints = {
    }
    static mapping = {
        trophiesGivenBy lazy: false
    }
    
}
