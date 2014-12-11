package com.ideas.game
import com.ideas.jasypt.GormEncryptedStringType

class TrohpyReasonBack {
    TrohpyHistory historyID;
    String reason;
    
    static constraints = {
    	reason size:0..1000
    }

}
