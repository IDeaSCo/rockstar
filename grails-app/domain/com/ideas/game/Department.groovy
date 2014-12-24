package com.ideas.game

/**
 * Created by idnasr on 12/24/2014.
 */
class Department {
    int id;
    String departmentName;
    static constraints = {
        departmentName unique: true
    }
}
