package com.ideas.game

class UserService {

    def save(UserInfoDTO dto) {

        def user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEmployeeId(dto.getEmployeeID());
        user.setAccountName(dto.getAccountName().toLowerCase());
        user.setDepartment(dto.getDepartment());
        user.setTitle(dto.getJobTitle());
        user.save(flush: true, failOnError: true);
        return user;

    }
    
    def getUserEmailList(){
        def emailIDs = []
        def users = User.findAll()
        users.each{
            //println it.email
            emailIDs.add(it.email)
        }
        return emailIDs
    }
}
