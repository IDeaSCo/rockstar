package com.ideas.game

class MissionController {
    def dataSource
    def create = {
        def missionService = new MissionService(dataSource);
        String selfEmailID=session.userInfo.email.toLowerCase();
        missionService.createMission(params,selfEmailID)
        redirect(uri: "/index")

    }

    def close = {
        def missionService = new MissionService(dataSource);
        missionService.closeMission(params.missionID);
        redirect(uri: "/index")
    }
}
