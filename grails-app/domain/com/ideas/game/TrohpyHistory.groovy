package com.ideas.game

class TrohpyHistory {
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
