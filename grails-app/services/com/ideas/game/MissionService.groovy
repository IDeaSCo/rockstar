package com.ideas.game

import groovy.sql.Sql

class MissionService {
    def dataSource
    UserService userService = new UserService();
    MissionService(def dataSource){
        this.dataSource = dataSource;
    }

    def closeMission(def missionID){
        Mission mission = Mission.findById (missionID);
        mission.status=0;
        mission.save(flush: true,  failOnError: true)
    }
    def createMission(def params, String missionCreatorEmail) {

        User fromUser = userService.getUser(missionCreatorEmail)
        Mission newMission = new Mission();
        newMission.creator = fromUser;
        newMission.date = new Date();
        newMission.missionStatement = params.missionStatement;
        newMission.status = 1;
        newMission.trophies = params.trohies.toInteger();
        newMission.save(flush: true,  failOnError: true)
        
    }

    def getOpenMissions(){
        def missionList = [];
        List list = Mission.findAll("from Mission where status=1 order by trophies desc");
        list.each(){
            def individualMission = [];
            individualMission.add(it.creator.getUserImage());
            individualMission.add(it.creator.getName());
            individualMission.add(it.missionStatement);
            individualMission.add(it.trophies);
            individualMission.add(it.creator.email);
            individualMission.add(it.id);
            missionList.add(individualMission);
        }

        return missionList;
    }

}

