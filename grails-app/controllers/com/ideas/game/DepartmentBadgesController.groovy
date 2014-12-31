package com.ideas.game


class DepartmentBadgesController {
    def dataSource


    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        BadgeService badgeService = new BadgeService(dataSource)
        def badgeList = Badge.list()
        def departmentBadgesList = badgeService.listAvailableBadges(session.userInfo.department.departmentName)
        [badgeList:badgeList,departmentBadgesList: departmentBadgesList, defaultDepartmentBadgeList:badgeService.getBadgeListForDepartment("Default")]
    }


    def save() {

        if(session.userInfo.isAdmin == true) {

            def departmentBadgeList = DepartmentBadges.findAllByDepartment(session.userInfo.department)
            departmentBadgeList.each() {
                int badgeID = it.badge.id;
                boolean isExist = false;
                params.badge_id.each() {
                    if (it == badgeID + "") {
                        isExist = true;
                    }
                }
                if (!isExist) {
                    DepartmentBadges departmentBadges = DepartmentBadges.findByBadge(it.badge);
                    departmentBadges.delete();
                }

            }

            params.badge_id.each() {
                if (it != -1 + "") {
                    Badge badge = Badge.findById(it);
                    if (DepartmentBadges.findByBadgeAndDepartment(badge,session.userInfo.department) == null) {
                        DepartmentBadges departmentBadges = new DepartmentBadges();
                        departmentBadges.department = session.userInfo.department;
                        departmentBadges.badge = Badge.findById(it)
                        departmentBadges.save(flush: true, failOnError: true)
                    }
                }
            }

        }

        redirect(action: "list")
    }


}
