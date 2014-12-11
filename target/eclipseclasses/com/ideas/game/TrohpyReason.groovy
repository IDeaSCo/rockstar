package com.ideas.game
import com.ideas.jasypt.GormEncryptedStringType

class TrohpyReason {
    TrohpyHistory historyID;
    String reason;
    
    static constraints = {
    	reason size:0..256
    }
    static mapping = {
    	reason	type:GormEncryptedStringType
    }
}
