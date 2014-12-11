package com.ideas.game;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com4j.COM4J;
import com4j.ComException;
import com4j.Variant;
import com4j.typelibs.activeDirectory.IADs;
import com4j.typelibs.ado20.ClassFactory;
import com4j.typelibs.ado20.Fields;
import com4j.typelibs.ado20._Command;
import com4j.typelibs.ado20._Connection;
import com4j.typelibs.ado20._Recordset;

class UserInfoService {


	protected Log _log = LogFactory.getLog(UserInfoService.class);

	static String defaultNamingContext = null;

	static final String usefulFields = "distinguishedName,userPrincipalName,sAMAccountName,sn,givenName,title,mail,manager,employeeID,Department,company,division,thumbnailPhoto,thumbnailLogo";
        //static final String usefulFields = "*";

	synchronized void initNamingContext() {
		if (defaultNamingContext == null) {
			IADs rootDSE = COM4J.getObject(IADs.class, "LDAP://RootDSE", null);
			defaultNamingContext = (String)rootDSE.get("defaultNamingContext");
                        _log.info("defaultNamingContext="+defaultNamingContext);
		}
	}

                
	public UserInfoDTO getUserInfoByEmail (String emailID) {
                System.out.println("email:"+emailID);
		String searchField = "mail";
                return getUserInfo(searchField, emailID);
            
        }
	public UserInfoDTO getUserInfoByUserName (String username) {
                System.out.println("username:"+username);
		String searchField = "userPrincipalName";
		int pSlash = username.indexOf('\\');
		if (pSlash > 0) {
			searchField = "sAMAccountName";
			username = username.substring(pSlash+1);
		}            
                return getUserInfo(searchField, username);
        }
        private UserInfoDTO getUserInfo (String searchField, String searchValue) {
		initNamingContext();
		if (defaultNamingContext == null) {
                    _log.error("Could not initialize default naming context");
			return null;
		}

		// Searching LDAP requires ADO [8], so it's good to create a connection upfront for reuse.

		_Connection con = ClassFactory.createConnection();
		con.provider("ADsDSOObject");
		con.open("Active Directory Provider",""/*default*/,""/*default*/,-1/*default*/);

		// query LDAP to find out the LDAP DN and other info for the given user from the login ID

		_Command cmd = ClassFactory.createCommand();
		cmd.activeConnection(con);


		_log.info("Command="+"<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
		cmd.commandText("<LDAP://"+defaultNamingContext+">;("+searchField+"="+searchValue+");"+usefulFields+";subTree");
		_Recordset rs = cmd.execute(null, Variant.getMissing(), -1/*default*/);
                
                UserInfoDTO userInfoDTO=null;
		if(rs.eof()) { // User not found!
			_log.error(searchValue+" not found.");
		}
		else {
			Fields userData = rs.fields();
                        userInfoDTO = new UserInfoDTO();
			if (userData != null) {
				Object o;
				try {
					o = userData.item("sAMAccountName").value();
					if (o != null) userInfoDTO.setAccountName(o.toString());
				} catch (ComException ecom ) {
					_log.error("sAMAccountName not returned:"+ecom.getMessage());
				}
                                try {
					o = userData.item("sn").value();
					if (o != null) userInfoDTO.setLastName(o.toString());
				} catch (ComException ecom ) {
					_log.error("sn not returned:"+ecom.getMessage());
				}
				try {
					o = userData.item("givenName").value();
					if (o != null) userInfoDTO.setFirstName(o.toString());
				} catch (ComException ecom ) {
					_log.error("givenName not returned:"+ecom.getMessage());
				}
				try {
					o = userData.item("mail").value();
					if (o != null) userInfoDTO.setEmail(o.toString()) ;
				} catch (ComException ecom ) {
					_log.error("mail not returned:"+ecom.getMessage());
				}
				try {
					o = userData.item("employeeID").value();
					if (o != null) userInfoDTO.setEmployeeID(o.toString());
				} catch (ComException ecom ) {
					_log.error("employeeID not returned:"+ecom.getMessage());
				}				
				try {
					o = userData.item("department").value();
					if (o != null) userInfoDTO.setDepartment(o.toString());
				} catch (ComException ecom ) {
					_log.error("department not returned:"+ecom.getMessage());
				}	
				try {
					o = userData.item("title").value();
					if (o != null) userInfoDTO.setJobTitle(o.toString());
				} catch (ComException ecom ) {
					_log.error("title not returned:"+ecom.getMessage());
				}				
				try {
					o = userData.item("thumbnailPhoto").value();
					if (o != null) userInfoDTO.setThumbnailPhoto(o.toString());
				} catch (ComException ecom ) {
					_log.error("thumbnailPhoto not returned:"+ecom.getMessage());
				}			
				try {
					o = userData.item("thumbnailLogo").value();
					if (o != null) userInfoDTO.setThumbnailLogo(o.toString());
				} catch (ComException ecom ) {
					_log.error("thumbnailLogo not returned:"+ecom.getMessage());
				}                        }			
			else {
				_log.error("User "+searchValue+" information is empty?");
			}
		}
		rs.close();
		con.close();
                
                return userInfoDTO;
	}

	public static String getDefaultNamingContext() {
		return defaultNamingContext;
	}


}
