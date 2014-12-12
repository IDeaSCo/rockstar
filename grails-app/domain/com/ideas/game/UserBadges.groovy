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
}
