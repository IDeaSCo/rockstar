package com.ideas.game

class TrophyReason {
    TrophyHistory historyID;
    String reason;
    
    static constraints = {
    	reason size:0..1000
    }

}
