package com.ideas.game


class UserController {
    def dataSource

    def view(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(view: "error")
            return
        }

        BadgeService service = new BadgeService();
        def userBadges = service.getBadgesForUser(userInstance);

        TrophyService trophyService = new TrophyService(dataSource);
        def reasonOfStarsReceivedByBadge = trophyService.getReasonsForBadge(userBadges);

        [userInstance: userInstance, userBadges:userBadges, reasonOfStarsReceivedByBadge:reasonOfStarsReceivedByBadge]
    }

    def profile(){
        println "reaced profile page"
        TrophyService trophyService = new TrophyService(dataSource);
        BadgeService badgeService = new BadgeService(dataSource);

        def departmentMap = trophyService.getDepartmentMap(session.userInfo.getDepartment().departmentName);
        def userBadges = badgeService.getBadgesForUser(session.userInfo);
        def userCompetitorBadges = [];
        def userFollowerBadges = [];
        userBadges.each(){
            userCompetitorBadges.add(badgeService.getUserCompetition(it));
            userFollowerBadges.add(badgeService.getUserFollower(it));
        }
        [departmentMap: departmentMap, userBadges:userBadges, userCompetitorBadges:userCompetitorBadges, userFollowerBadges:userFollowerBadges]
    }
}
