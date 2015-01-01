Rock Star - Gamifying Team Engagement
==============

This application makes use of Gamification techniques for Team Engagement and Team's Performance tracking. Though it originated as an experiement to [Gamify Agile Adoption](http://ashishparkhi.com/2014/10/26/gamifying-agile-adoption-an-experiment/) at [IDeaS](http://ideas.com), it's scope is not limited just for Agile Adoption. It can be used by any orginization that wants their teams to get continuous visual feedback on how they are doing, visualize sense of accountability, visualize sense of achievement, reinforce positive behavior, create healthy competition, create a culture of appreciation, encourage innovation, help performance tracking and create transparency. 

####Build Requirements
- Grails 2.2.4

####Deployment Requirements
- Tomcat 7.0
- MySQL 5.5
- Java 1.6
- [Gravatar](https://en.gravatar.com/) profile image.
- Windows Server with Active Directory Authentication
  - This application queries following fields from Active Directory
    - distinguishedName
    - userPrincipalName
    - sAMAccountName
    - sn
    - givenName
    - title
    - mail
    - manager
    - employeeID
    - Department
    - company
    - division
    - thumbnailPhoto
    - thumbnailLogo

##Configuration
 - star-config.properties
  - dataSource.driverClassName=com.mysql.jdbc.Driver
  - dataSource.url=jdbc:mysql://localhost:3309/game?autoReconnect=true
  - dataSource.username=admin
  - dataSource.password=admin
 
