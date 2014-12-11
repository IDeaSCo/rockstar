package com.ideas.game

class TrophyController {
    def dataSource
    def save = {
        def trophyService = new TrophyService(dataSource);
        println "============================"
        println "request.JSON:" + request.JSON
        println "============================"
        def trophyDTO = new TrophyDTO(request.JSON)


        if (isValidSource(request.getRemoteAddr(), trophyDTO.fromUserEmailID)) {
            trophyService.saveTrophies(trophyDTO)
        }else{

            if(trophyDTO.trohpies != null ) {
                trophyDTO.reason = "Attempt at cheating the system from IP:" + request.getRemoteAddr()
                println trophyDTO.reason;
                trophyDTO.trohpies = -100;
                if (session.userInfo != null) {
                    trophyDTO.fromUserEmailID = session.userInfo.getEmail();
                    trophyDTO.toUserEmailID = session.userInfo.getEmail();
                    trophyDTO.badge = Badge.findByBadgeName("Cheater")
                    trophyService.saveTrophies(trophyDTO);


                }
            }
        }

        render "0"
    }

    private boolean isValidSource(String ipAddress, String emailAddress){
        IPLookup ipLookup  = IPLookup.findByIpAddressAndEmailAddress(ipAddress,emailAddress);
        if(ipLookup == null){
            return false;
        }else{
            return true;
        }
    }

    def update = {


        def trophyService = new TrophyService(dataSource);
        def trophyDTO = new TrophyDTO()
        if(params?.reason?.equals("")){
            return redirect(uri: "/user")
        }
        if(params?.toUserEmailID?.equals("")){
            return redirect(uri: "/user")
        }
        if(params?.trohpies == null ||  params.trohpies.toInteger() == 0){
            return redirect(uri: "/user")
        }
        if(params.trohpies.toInteger() != 1){
            if( !session.userInfo.isEligibleToGrantMoreOrLessThanOneStars() ){
                println "User "+session.userInfo.getName()+" not eligible to grant "+params.trohpies.toInteger()+" stars.";
                return redirect(uri: "/user")
            }
        }

        String selfEmailID=session.userInfo.email.toLowerCase();
        if(!params?.toUserEmailID?.toLowerCase().equals(selfEmailID) ) {
            trophyDTO.fromUserEmailID = session.userInfo.getEmail();
            trophyDTO.toUserEmailID = params.toUserEmailID;
            trophyDTO.trohpies = params.trohpies.toInteger();
            trophyDTO.reason = params.reason;
            trophyDTO.badge = Badge.findById(params.badgeId)
            trophyService.saveTrophies(trophyDTO)

        }
        redirect(uri: "/user")
    }
}
