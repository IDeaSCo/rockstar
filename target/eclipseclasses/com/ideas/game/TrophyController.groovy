package com.ideas.game

class TrophyController {

    def save = {
        def trophyService = new TrophyService();
        println "============================"
        println "request.JSON:"+request.JSON
        println "============================"
        def trophyDTO = new TrophyDTO(request.JSON)
        trophyService.saveTrophies(trophyDTO)  
        render "0"
    }
    
    def update = {
    
        def trophyService = new TrophyService();        
        def trophyDTO = new TrophyDTO()
        trophyDTO.fromUserEmailID=session.userInfo.getEmail();
        trophyDTO.toUserEmailID=params.toUserEmailID;
        trophyDTO.trohpies=1
        trophyDTO.reason=params.reason;
        
        trophyService.saveTrophies(trophyDTO)  
        redirect(uri: "/user")
    }
}
