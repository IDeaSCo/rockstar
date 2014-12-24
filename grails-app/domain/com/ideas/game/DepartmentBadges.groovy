package com.ideas.game

/**
 * Created by idnasr on 12/24/2014.
 */
class DepartmentBadges {
    int id
    Department department
    Badge badge

    static mapping = {
        department lazy: false
        badge lazy: false
    }
    static constraints = {
        department(unique:['badge'])
    }
}
