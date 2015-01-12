package com.ideas.game


class LeaderController {
    def dataSource


    def index() {

        BadgeService badgeService = new BadgeService(dataSource)
        TrophyService trophyService = new TrophyService(dataSource);
        MissionService missionService = new MissionService(dataSource);

        def badges = badgeService.listAvailableBadges(session.userInfo.getDepartment().departmentName);
        def starOfTheDayMap = trophyService.getStarOfTheDay(session.userInfo.getDepartment().departmentName);
        def starOfTheWeekMap = trophyService.getStarOfTheWeek(session.userInfo.getDepartment().departmentName);
        def starOfTheMonthMap = trophyService.getStarOfTheMonth(session.userInfo.getDepartment().departmentName);
        def starMap = trophyService.getStar(session.userInfo.getDepartment().departmentName);
        def appreciatorMap = trophyService.getAppreciator(session.userInfo.getDepartment().departmentName);
        def openMission = missionService.getOpenMissions();
        def badgeLeaderBoard = badgeService.getBadgeLeaderBoard(session.userInfo.getDepartment().departmentName);
        [badges:badges, starOfTheDayMap:starOfTheDayMap, starOfTheWeekMap:starOfTheWeekMap, starOfTheMonthMap:starOfTheMonthMap, starMap:starMap, appreciatorMap:appreciatorMap, openMission:openMission,badgeLeaderBoard:badgeLeaderBoard  ]
    }

}