package com.ideas.game

/**
 * Created by idnasr on 12/24/2014.
 */
class DepartmentBadges {
    Department department
    Badge badge

    static mapping = {
        department lazy: false
        badge lazy: false
    }

}
