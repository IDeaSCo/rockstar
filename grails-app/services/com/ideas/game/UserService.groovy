package com.ideas.game

class UserService {

    def save(UserInfoDTO dto) {

        Department department = Department.findByDepartmentName(dto.getDepartment())
        if(department == null){
            department = new Department();
            department.departmentName=dto.getDepartment();
            department.save(flush: true, failOnError: true);
        }

        def user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setEmployeeId(dto.getEmployeeID());
        user.setAccountName(dto.getAccountName().toLowerCase());
        user.setDepartment(department);
        user.setTitle(dto.getJobTitle());
        user.save(flush: true, failOnError: true);
        return user;

    }
    
    def getUserEmailList(){
        def emailIDs = []
        def users = User.findAll()
        users.each{
            println it.email
            emailIDs.add(it.email)
        }
        return emailIDs
    }

    def User getUser(String email){
        UserInfoService userInfoService = new UserInfoService();
        UserService userService = new UserService();
        User user = User.findByEmail(email);
        if(user == null){
            UserInfoDTO dto = userInfoService.getUserInfoByEmail(email);
            user = userService.save(dto);
        }
        return user;

    }


}
