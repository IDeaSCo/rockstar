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

####Leader Board
- Department Leader Board has two sections
 - Left pannel shows the top three Badge leaders.
 - Right pannel shows
  - Star of the Day ( *Top three individuals, who earned most stars in a day.*)
   - Star of the Week.
   - Star of the Month.
   - Most Appreciated ( *Top three individuals who have earned most stars so far.*)
   - Most Appreciative ( *Individuals who appreciate other’s contribution. Encourages individuals to recognize other’s contributions.* )
   - Open Missions ( *Open missions/quests are visible across departments. Quests help in creating the culture of taking initiatives, being pro-active; helps in earning more Stars. Encourages cross department collaboration in addressing issues.* )

![Leaderboard](https://ashishparkhi.files.wordpress.com/2015/01/new-leader-board.png?w=930) 

####Self User Profile
Clicking on the user name on the right top corner brings the user on the User Profile page. The user profile page also two sections
 - Left Pannel shows
  - User section
    - Total number of stars earned so far.
    - All the badges earned by the user. The color represents the badge level.
    - Appreciate someone by clicking on thumbs up icon.
    - Create new mission by clicking on the bulls eye icon.
  - Department Section
    - List all the users in the department in descending order of the number of stars received.
    - Appreciate the individual using the thumbs up icon.
 - Right Pannel
  - Accordian shows all the badges that the user has received.
  - Progress bar shows the number of stars received for a given badge and the number of stars to achieve to reach to the next level of the badge.
  - Photos
    - The middle photo is of the self user.
    - The left hand side photo represents the user who is just ahead of the self user.  
    - The right hand side photo represents the user who is just behind the self user.    - 

![User Profile](https://ashishparkhi.files.wordpress.com/2015/01/new-user-profile.png?w=930)

####User Details
Click on any image on any screen and you would land up on the user details screen. The screen shows all the badges earned by the particular user. 
 - Hover on the badge and you will see the reason why the user received stars for the concerned badge.
![User Details](https://ashishparkhi.files.wordpress.com/2015/01/new-user-details.png?w=930)

###Bots
####Jenkins
 - Extended version of [Jenkins CI-Plugin](https://github.com/jenkinsci/ci-game-plugin) is available as [rockstar-plugins-jenkins-ci-game](https://github.com/IDeaSCo/rockstar-plugins-jenkins-ci-game)
 - Extended version of Jenkins CI-Plugin would send stars to Rock Star App.

####Rally
 - WIP
