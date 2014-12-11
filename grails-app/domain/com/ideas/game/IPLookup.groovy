package com.ideas.game

/**
 * Created by idnasr on 11/12/2014.
 */
class IPLookup {
    int id;
    String ipAddress;
    String emailAddress;
    static constraints = {
        ipAddress size:7..15
        emailAddress size:1..80
    }
}
