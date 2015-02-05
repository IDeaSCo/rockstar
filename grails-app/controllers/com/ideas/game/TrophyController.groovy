package com.ideas.game

class TrophyController {
    def dataSource
    def grailsApplication
    def save = {
        def trophyService = new TrophyService(dataSource);
        println "============================"
        println "request.JSON:" + request.JSON
        println "============================"
        def trophyDTO = new TrophyDTO(request.JSON)

        trophyDTO.badge = Badge.findByBadgeName(request.JSON.badgeName);

        if (isValidSource(request.getRemoteAddr(), trophyDTO.fromUserEmailID)) {
            trophyService.saveTrophies(trophyDTO)
        }else{

            if(trophyDTO.trophies != null ) {
                trophyDTO.reason = "Attempt at cheating the system from IP:" + request.getRemoteAddr()
                println trophyDTO.reason;
                trophyDTO.trophies = -100;
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
        return ipLookup != null
    }

    def update = {
        def trophyService = new TrophyService(dataSource);
        def trophyDTO = new TrophyDTO()
        if(params?.reason?.equals("")){
            return redirect(uri: "/user/profile")
        }
        if(params?.toUserEmailID?.equals("")){
            return redirect(uri: "/user/profile")
        }
        if(params?.trophies == null ||  params.trophies.toInteger() == 0){
            return redirect(uri: "/user/profile")
        }
        if(params.trophies.toInteger() != 1){
            if( !session.userInfo.isEligibleToGrantMoreOrLessThanOneStars() ){
                println "User "+session.userInfo.getName()+" not eligible to grant "+params.trophies.toInteger()+" stars.";
                return redirect(uri: "/user/profile")
            }
        }
        String selfEmailID=session.userInfo.email.toLowerCase();
        if(!params?.toUserEmailID?.toLowerCase().equals(selfEmailID) ) {
            trophyDTO.fromUserEmailID = session.userInfo.getEmail();
            trophyDTO.toUserEmailID = params.toUserEmailID;
            trophyDTO.trophies = params.trophies.toInteger();
            trophyDTO.reason = params.reason;
            trophyDTO.badge = Badge.findById(params.badgeId)
            trophyService.saveTrophies(trophyDTO)
            EmailService emailService = new EmailService(grailsApplication);
            emailService.sendMail(params.toUserEmailID, session.userInfo.getName()+ " gave you "+trophyDTO.trophies+" star(s) for badge "+ trophyDTO.badge.badgeName,trophyDTO.reason);
        }
        redirect(uri: "/user/profile")
    }

    def claim = {
        def trophyService = new TrophyService(dataSource);
        def trophyDTO = new TrophyDTO()
        if(params?.claimReason?.equals("")){
            return redirect(uri: "/user/profile")
        }
        if(params?.claimBadgeId == null){
            return redirect(uri: "/user/profile")
        }
        if(params?.claimTrophies == null ){
            return redirect(uri: "/user/profile")
        }

        Badge badge= Badge.findById(params.claimBadgeId);

        int trophies=badge.starsAwarded;
        if(params.claimTrophies.toInteger() != 0 && params.claimTrophies.toInteger() != badge.starsAwarded){
            if( session.userInfo.isEligibleToGrantMoreOrLessThanOneStars() ) {
                trophies=params.claimTrophies.toInteger();
            }else{
                println "User "+session.userInfo.getName()+" not eligible to grant "+params.claimTrophies.toInteger()+" stars.";
                return redirect(uri: "/user/profile")
            }
        }
        trophyDTO.fromUserEmailID = session.userInfo.getEmail();
        trophyDTO.toUserEmailID = session.userInfo.getEmail();
        trophyDTO.trophies = trophies;
        trophyDTO.reason = "Claiming Stars For:"+params.claimReason;
        trophyDTO.badge = badge;
        trophyService.saveTrophies(trophyDTO)

        redirect(uri: "/user/profile")
    }


}
