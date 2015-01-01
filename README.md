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

####Configuration
 - star-config.properties 
  - dataSource.driverClassName=com.mysql.jdbc.Driver
  - dataSource.url=jdbc:mysql://localhost:3309/game?autoReconnect=true
  - dataSource.username=admin
  - dataSource.password=admin
 - The application exposes REST API `<BASE_APP_URL>`/trophy/save for bots to credit stars.
  - iplookup tables should contain the list of emailIDs and IP addresses from where legitimate bots can credit stars.
  - sample REST message
    `{
    "fromUserEmailID":"ashish.parkhi@ideas.com",
    "toUserEmailID":"firstname.lastname@company.com",
    "trophies":75,
    "reason":"For coming up with gamification idea.",
    "badgeName":"Innovator"
   }`

####Levels
- As the user starts earning stars, he would reach different levels. Level represent the smallest and the largest objects in our solar system. The objective of the game is to reach the Sun.

![Levels](https://ashishparkhi.files.wordpress.com/2014/10/badges.png?w=276&h=472)
 
####Badges
- Badges are more specific and can be earned for specific activity.
- Every orginization can create their own  badges.
- Different departments in an orginization can have different badges.
- Badges have three levels
  - Bronze
  - Silver
  - Gold
- You can define the number of points that are required to reach a level per badge.
- There are Angel and Devil badges. 
  - The goal is to earn Angel badges.
  - Devil badges represents activities that one should not be performing.
![Badges](https://ashishparkhi.files.wordpress.com/2015/01/new-badge-list.png?w=930)
