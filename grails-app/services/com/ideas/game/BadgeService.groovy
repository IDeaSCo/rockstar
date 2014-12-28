package com.ideas.game

import groovy.sql.Sql

/**
 * Created by idnasr on 12/11/2014.
 */
class BadgeService {
    def dataSource

    def BadgeService(def dataSource){
        this.dataSource=dataSource
    }
    public def listAvailableBadges(String departmentName){
        def badges = getBadgeListForDepartment("Default")
        badges.addAll(getBadgeListForDepartment(departmentName))
        return badges.sort{it.displayOrder};
    }

    private def getBadgeListForDepartment(String departmentName) {
        Department department = Department.findByDepartmentName(departmentName);
        def defaultBadgeIdList = DepartmentBadges.findAllByDepartment(department)*.badge.id
        return Badge.findAllByIdInList(defaultBadgeIdList);
    }

    public def listNonEvilBadges(){
        return Badge.findAllByIsEvil(false);
    }

    public void updateUserBadePoints(User toUser, Badge badge, int points){
        UserBadges userBadge  = UserBadges.findByUserAndBadge(toUser,badge)
        if(userBadge == null){
            userBadge = new UserBadges()
            userBadge.badge=badge;
            userBadge.user=toUser;
        }
        userBadge.points+= Math.abs(points);

        userBadge.save(flush: true,  failOnError: true);
    }

    public def getBadgeLeaderBoard(String department){
        def badgeMap = [:]
        def badges = listAvailableBadges(department);

        badges.each { badge ->
            def listOfUsersBadges = UserBadges.findAll("from UserBadges as ub where ub.user.department.departmentName=:department and ub.badge.id=:badgeId order by ub.points desc " ,[department:department, badgeId: badge.id], [max: 3]);
            badgeMap.put(badge,listOfUsersBadges)
        }
        return badgeMap;
    }

    public def getBadgesForUser(def user){
        def userBadges = UserBadges.findAllByUser(user)
        userBadges.each(){
            //println it.badge.badgeName
        }
    }

    public def getUserCompetition(def userBadge){
        def listOfUsersBadges = UserBadges.findAll("from UserBadges as ub where ub.user.department.departmentName=:department and ub.badge.id=:badgeId and ub.points>:points order by ub.points asc " ,[department:userBadge.user.department.departmentName, badgeId: userBadge.badge.id, points:userBadge.points], [max: 1]);
        if(listOfUsersBadges.size()==0){
            return userBadge
        }
        return listOfUsersBadges.get(0)
    }
    public def getUserFollower(def userBadge){
        def listOfUsersBadges = UserBadges.findAll("from UserBadges as ub where ub.user.department.departmentName=:department and ub.badge.id=:badgeId and ub.points<:points order by ub.points desc " ,[department:userBadge.user.department.departmentName, badgeId: userBadge.badge.id, points:userBadge.points], [max: 1]);
        if(listOfUsersBadges.size()==0){
            return userBadge
        }
        return listOfUsersBadges.get(0)
    }

}
