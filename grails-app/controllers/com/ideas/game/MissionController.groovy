package com.ideas.game

class MissionController {
    def dataSource
    def create = {
        def missionService = new MissionService(dataSource);
        String selfEmailID=session.userInfo.email.toLowerCase();
        missionService.createMission(params,selfEmailID)
        redirect(uri: "/user/profile")

    }

    def close = {
        def missionService = new MissionService(dataSource);
        missionService.closeMission(params.missionID);
        redirect(uri: "/user/profile")
    }
}
