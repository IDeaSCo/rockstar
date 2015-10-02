package com.ideas.game

import org.grails.orm.hibernate.HibernateSession
import org.grails.orm.hibernate.support.HibernateBeanWrapper
import org.hibernate.Hibernate
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession;

@WebFilter

class AuthFilter implements Filter {

    public void  init(FilterConfig config)
            throws ServletException{

    }

    private stripDomainName(String userName){
        int index = userName.indexOf('\\');
        if(index>=0){
            return userName.substring(index+1);
        }
        return userName;
    }

    public void  doFilter(ServletRequest request,
                          ServletResponse response,
                          FilterChain chain)
            throws java.io.IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = request.getSession(false);

        String remoteUser = httpRequest.getRemoteUser();


        println "User:"+remoteUser+" IP:"+httpRequest.getRemoteAddr();

        if(remoteUser != null)
        {
            if( session.getAttribute("userInfo") == null) {
                User.withNewSession {
                    User user = User.findByAccountName(stripDomainName(remoteUser));
                    if (user == null) {
                        try {
                            UserInfoService userInfoService = new UserInfoService();
                            UserInfoDTO dto = userInfoService.getUserInfoByUserName(remoteUser);
                            if (dto == null) {
                                println("Could not query active directory as dto was null :" + remoteUser);
                                return;
                            } else {
                                println(dto.firstName + " " + dto.lastName)
                            }

                            UserService userService = new UserService();
                            user = userService.save(dto)

                        } catch (Exception e) {
                            println("Could not query active directory for user:" + remoteUser);
                            e.printStackTrace();

                        }
                    }
                    session.setAttribute("userInfo",user);
                }

            }

        }
        chain.doFilter(request,response);
    }

    public void destroy( ){
        /* Called before the Filter instance is removed
        from service by the web container*/
    }
}
