package com.ideas.game;

/**
 *
 * @author idnasr
 */
class UserInfoDTO {
	String dn;	// distinguishedName
	String upn; // userPrincipalName
	String accountName; // sAMAccountName;
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	String lastName;	// surname
	String firstName;
	String email;
	String managerName;
	String jobTitle;
	String employeeID;
	String department;
	String company;
	String division;
        String thumbnailPhoto;
        String thumbnailLogo;
	
        public String getThumbnailPhoto(){
            return thumbnailPhoto;
        }
        
        public String getThumbnailLogo(){
            return thumbnailLogo;
        }

        public void setThumbnailPhoto(String thumbnailPhoto){
            this.thumbnailPhoto=thumbnailPhoto;
        }
        
        public void setThumbnailLogo(String thumbnailLogo){
            this.thumbnailLogo=thumbnailLogo;
        }
        
        public String getDivision() {
		return division;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public UserInfoDTO () {

	}

	public String getDn() {
		return dn;
	}

	public String getUpn() {
		return upn;
	}

	public String getAccountName() {
		return accountName;
	}

        public void setAccountName( String accountName) {
		this.accountName=accountName;
	}
}

