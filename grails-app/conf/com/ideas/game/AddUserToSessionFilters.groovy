package com.ideas.game


class AddUserToSessionFilters {

    def dataSource

    private stripDomainName(String userName){
        int index = userName.indexOf('\\');
        if(index>=0){
            return userName.substring(index+1);
        }
        return userName;
    }
    def filters = {
        allURIs(uri: '/**'){
            before = {
                    println "User:"+request.getRemoteUser()+" IP:"+request.getRemoteAddr();
                    if(request.getRemoteUser() != null) {
                        if(session.userInfo == null) {
                            User user = User.findByAccountName(stripDomainName(request.getRemoteUser()));
                            if (user == null) {
                                try {
                                    UserInfoService userInfoService = new UserInfoService();
                                    UserInfoDTO dto = userInfoService.getUserInfoByUserName(request.getRemoteUser());
                                    if (dto == null) return;

                                    UserService userService = new UserService();
                                    user = userService.save(dto)

                                }catch(Exception e){
                                    println("Could not query active directory for user:"+request.getRemoteUser());
                                }
                            }
                            session.userInfo = user;
                        }

                }
            }
        }
    }
}
