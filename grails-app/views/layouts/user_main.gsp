<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<r:layoutResources/>
	<head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		    <meta name="description" content="IDeaS Rock Star"/>
		    <meta name="author" content="IDeaSLabs"/>
		    <meta http-equiv="refresh" content="900">
		    <title>IDeaS Rock Star</title>
			<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"/>

		    <link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'css', file: 'bootstrap-theme.css')}" rel="stylesheet" type="text/css"/>


		    <link href="${resource(dir: 'assets/fullcalendar/fullcalendar', file: 'bootstrap-fullcalendar.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'assets/jquery-easy-pie-chart', file: 'jquery.easy-pie-chart.css')}" rel="stylesheet" type="text/css" media="screen"/>
		    <link rel="stylesheet" href="${resource(dir: 'css', file: 'owl.carousel.css')}" type="text/css">
		    <link href="${resource(dir: 'css', file: 'style.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'css', file: 'style-responsive.css')}" rel="stylesheet" type="text/css"/>


            <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
            <!--[if lt IE 9]>
              <script src="js/html5shiv.js"></script>
              <script src="js/respond.min.js"></script>
              <script src="js/lte-ie7.js"></script>
            <![endif]-->

	</head>
	<r:layoutResources/>
	<body>
        
          
	  <section id="container" class="">
	      <!--header start-->

      <header class="header white-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
            <g:link controller="index" class="logo">IDeaS Rock Stars</g:link>
            <g:link controller="badges.html" class="badge bg-important">Levels</g:link>
            
            <!--logo end-->
 
            
      </header>      
      <!--header end-->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">            
              <!--overview start-->
              <div class="row state-overview">
                  <div class="col-lg-3">
                  <!--user profile info start-->
                  <section class="panel">
                      <div class="profile-widget profile-widget-info">
                          <div class="panel-body">
                            <div class="col-lg-4 col-sm-4 profile-widget-name">
                              <h4>${session?.userInfo?.getName()}</h4>               
                              <div class="follow-ava">
                                  <a href="user/view/${session?.userInfo?.id}" ><img src="${session?.userInfo?.getUserImage()}" alt="" onError="this.src='${session?.userInfo?.getNoImage()}';"></a>
                                  
                              </div>
                              <h6>${session?.userInfo?.getTitle()}</h6>
                            </div>
                            <div class="col-lg-8 col-sm-8 follow-info">
                                <p style="font-size: 50px;font-weight: bolder;color: #222;">${session?.userInfo?.getTrophies()} <i style="padding-left:10px; color:blue" class="fa fa-star"></i>
                              <p>
                                  <g:each status="i" var="userBadge" in="${session?.userBadges}">
                                     
                                          <span class="fa-stack fa-lg" title="${userBadge.badge.badgeName}">
						<i class="fa fa-circle fa-stack-2x" style="color: ${userBadge.getLevelColor()};"></i>
				             	<g:if test="${userBadge.badge.isEvil}">
                                             		<i class="fa ${userBadge.badge.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                          	</g:if>	
						     <g:else>
							<i class="fa ${userBadge.badge.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
						     </g:else>	
                                          </span>
			
                                     
                                     
                                 </g:each>
                              </p>
                             </div>
                          </div>
			   			 <footer class="profile-widget-foot">
			   			 Appreciate someone today!
			   			 <button class="btn btn-default" data-toggle="modal" data-target="#myModal" ><i class="fa fa-thumbs-up"></i></button>
			   			 <g:if test="${session.userInfo.isEligibleToGrantMoreOrLessThanOneStars()}">
			   			    <button title="Create Mission" class="btn btn-default" data-toggle="modal" data-target="#missionModal" ><img alt="Create Mission" src="images/mission.png" height="10" ></button>
                         </g:if>


                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
                            	<g:form id="updateTrophies" name="updateTrophies" controller="trophy" action="update">
	                              <div class="modal-dialog">
	                                <div class="modal-content">
	                                  <div class="modal-header">
	                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                                    <h4 class="modal-title" id="myModalLabel">Appreciate</h4>
	                                  </div>
	                                  <div class="modal-body" id="myBody" placeholder="Type @ for auto complete">
                                        Whom do you want to Appreciate?
                                        <input type="text" class="form-control" placeholder="Type in the email address..." name="toUserEmailID" id="toUserEmailID"/>
	                                    <textarea class="form-control" rows="7" placeholder="Type in your reason..." name="reason" id="reason"></textarea>
	                                    <g:if test="${session.userInfo.isEligibleToGrantMoreOrLessThanOneStars()}">
                                             <input type="text" class="form-control" placeholder="Type in no of stars..." name="trohpies" id="trohpies" value="1"/>
                                        </g:if>
                                        <g:else>
                                             <input type="hidden" class="form-control" placeholder="Type in no of stars..." name="trohpies" id="trohpies" value="1"/>
                                        </g:else>
                                        <g:select id="badgeId" name="badgeId"
                                                  noSelection="${['-1':'Select One Category...']}"
                                                  from="${session.badges}"
                                                  optionValue="badgeName"
                                                  optionKey="id" />
	                                  </div>
	                                  <div class="modal-footer">
	                                    <button type="button" class="btn btn-default" data-dismiss="modal">Changed my mind</button>
	                                    <g:submitButton name="update" class="btn btn-primary" value="Send Appreciation" onclick="return isValidInput();"/>

	                                  </div>
	                                </div>
	                              </div>
                              </g:form>
                            </div>


                            <div class="modal fade" id="missionModal" tabindex="-1" role="dialog" aria-labelledby="missionModal" aria-hidden="false">
                            	<g:form id="createMission" name="createMission" controller="mission" action="create">
	                              <div class="modal-dialog">
	                                <div class="modal-content">
	                                  <div class="modal-header">
	                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                                    <h4 class="modal-title" id="myModalLabel">Create a new Mission!</h4>
	                                  </div>
	                                  <div class="modal-body" id="myBody" placeholder="Type @ for auto complete">
	                                    <textarea class="form-control" rows="7" placeholder="Enter the mission description here?" name="missionStatement" id="missionStatement"></textarea>

                                        <input type="text" class="form-control" placeholder="Type in the number of Stars on offer..." name="trohpies" id="trohpies" />

	                                  </div>
	                                  <div class="modal-footer">
	                                    <button type="button" class="btn btn-default" data-dismiss="modal">Changed my mind</button>
	                                    <g:submitButton name="update" class="btn btn-primary" value="Create Mission" onclick="return isValidMissionInput();"/>

	                                  </div>
	                                </div>
	                              </div>
                              </g:form>
                            </div>
                              <!--end modal-->

                          </footer>
                      </div>
                  </section>
                  <!--user profile info end-->
                </div>
                  <div class="col-lg-0">
                      </div>
                   
                  
                <!--today start-->
              <div class="col-lg-3">
                    <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Day</a>
                                  </li>
                             
                              </ul>
                          </header>
                        <div class="panel-body" style="height: 140px;">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                                
                                   
                        <table class="table table-hover personal-task">
                              <tbody>
                                <g:if test="${session?.starOfTheDayMap?.size() > 0}">
                                <tr>
                                    <!--<td>
                                     <p class="badge bg-info1">2</p>
                                  </td>-->
                                  <td style="width:10px;">
                                    <span >
                                        <a href="user/view/${((com.ideas.game.User)session?.starOfTheDayMap?.keySet()?.toList()?.first()).id}" ><img alt="" class="simple" src="${((com.ideas.game.User)session?.starOfTheDayMap?.keySet()?.toList()?.first()).getUserImage()}" height="35" onError="this.src='${session?.userInfo?.getNoImage()}';"></a>
                                        <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${((com.ideas.game.User)session?.starOfTheDayMap?.keySet()?.toList()?.first()).getUserBadge()}</span>
                                        
                                    </span>
                                  </td>
                                  <td>
                                      <p class="profile-name">${session?.starOfTheDayMap?.keySet().toList().first().getName()}</p>
                                      <p class="profile-occupation">${session?.starOfTheDayMap?.keySet().toList().first().getTitle()}</p>
                                  </td>
                                  <td>
                                      <span class="badge bg-info">${session?.starOfTheDayMap?.get(session?.starOfTheDayMap?.keySet().toList().first())}</span>
                                     <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                  </td>                                  
                              </tr>
                             
                              </g:if>
                           
                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>
                                  
                              </div>
                          </div>
                      </section>
              </div>
              <!--today end-->
                  
                     <!--week start-->
              <div class="col-lg-3">
                    <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Week</a>
                                  </li>
                             
                              </ul>
                          </header>
                          <div class="panel-body" style="height: 140px;">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                                
                                   
                        <table class="table table-hover personal-task">
                              <tbody>
                                <g:if test="${session?.starOfTheWeekMap?.size() > 0}">
                                     <tr>
                                         <!--<td>
                                          <p class="badge bg-info1">2</p>
                                       </td>-->
                                       <td style="width:10px;">
                                         <span >
                                             <img alt="" class="simple" src="${((com.ideas.game.User)session?.starOfTheWeekMap?.keySet()?.toList()?.first()).getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" height="35">
                                             <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${((com.ideas.game.User)session?.starOfTheWeekMap?.keySet()?.toList()?.first()).getUserBadge()}</span>
                                         </span>
                                       </td>
                                       <td>
                                           <p class="profile-name">${session?.starOfTheWeekMap?.keySet().toList().first().getName()}</p>
                                           <p class="profile-occupation">${session?.starOfTheWeekMap?.keySet().toList().first().getTitle()}</p>
                                       </td>
                                       <td>
                                           <span class="badge bg-info">${session?.starOfTheWeekMap?.get(session?.starOfTheWeekMap?.keySet().toList().first())}</span>
                                          <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                       </td>                                  
                                   </tr>
                             
                                </g:if>
                           
                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>
                                  
                              </div>
                          </div>
                      </section>
              </div>
              <!--week end-->
                  <!--month start-->
              <div class="col-lg-3">
                    <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Month</a>
                                  </li>
                             
                              </ul>
                          </header>
                        <div class="panel-body" style="height: 140px;">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                                
                                   
                        <table class="table table-hover personal-task">
                              <tbody>
                                <g:if test="${session?.starOfTheMonthMap?.size() > 0}">
                                    <tr>
                                        <!--<td>
                                         <p class="badge bg-info1">2</p>
                                      </td>-->
                                      <td style="width:10px;">
                                        <span >
                                            <img alt="" class="simple" src="${((com.ideas.game.User)session?.starOfTheMonthMap?.keySet()?.toList()?.first()).getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" height="35">
                                            <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${((com.ideas.game.User)session?.starOfTheMonthMap?.keySet()?.toList()?.first()).getUserBadge()}</span>
                                        </span>
                                      </td>
                                      <td>
                                          <p class="profile-name">${session?.starOfTheMonthMap?.keySet().toList().first().getName()}</p>
                                          <p class="profile-occupation">${session?.starOfTheMonthMap?.keySet().toList().first().getTitle()}</p>
                                      </td>
                                      <td>
                                          <span class="badge bg-info">${session?.starOfTheMonthMap?.get(session?.starOfTheMonthMap?.keySet().toList().first())}</span>
                                         <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                      </td>                                  
                                  </tr>
                                </g:if>
                              
                           
                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>
                                  
                              </div>
                          </div>
                      </section>
              </div>
              <!--month end-->
              </div>
              <!--overview end-->
              
              

              <!-- project team  start -->
              <div class="row">
                  <div class="col-lg-4">
                      <!--project team start-->
                      <section class="panel">
                        <div class="panel-body project-team">
                            <div class="task-progress">
                                  <h1>My Department</h1>  
                            </div>
                        </div>
                        <table class="table table-hover personal-task">
                              <tbody>
                                    <g:each  var="user" in="${session?.departmentMap?.keySet()}">
                                        <tr>

                                            <!--<td>
                                             <p class="badge bg-info1">2</p>
                                          </td>-->
                                          <td style="width:10px;">
                                            <span >
                                            <g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" height="40"></g:link>
                                            <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
                                            </span>
                                          </td>
                                          <td>
                                              <p class="profile-name">${user.getName()}</p>
                                              <p class="profile-occupation">${user.getTitle()}</p>
                                          </td>
                                          <td>
                                              <span class="badge bg-info" style="font-size:150%">${session?.departmentMap?.get(user)}</span>
                                          </td>
                                          <td>
                                                 <i style="padding-left:0px; color:blue; font-size:150%" class="fa fa-star"></i>
                                          </td>
                                          <td >
                                                 <g:if test="${!user.getEmail().equals(session?.userInfo?.getEmail())}">
                                                     <button class="btn btn-default" data-toggle="modal" data-target="#myModal" onclick="setEmail('${user.getEmail()}')"><i style="font-size:150%" class="fa fa-thumbs-up"></i></button>
                                                </g:if>

                                          </td>
                                          </tr>
                              </g:each>

                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                  </div>
              <div class="row">
                  <div class="col-lg-4">
                      <!--project team start-->
                      <section class="panel">
                        <div class="panel-body project-team">
                            <div class="task-progress">
                                  <h1>Who is getting Stars?</h1>
                            </div>
                        </div>
                        <table class="table table-hover personal-task">
                              <tbody>
                                <g:each  var="history" in="${session?.userDepartmentHistoryList}">
                                    <tr>

                                      <td style="width:10px;">
                                        <span >
                                        <img alt="" class="simple" src="${history.get(2).getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" height="40">
                                        </span>
                                      </td>
                                      <td style="text-align:left">
                                          <p class="profile-occupation">${history.get(2).getName()}</p>
                                          <p class="profile-name" style="font-size:100%"> ${history.get(0)} * ${history.get(1)}</p>
                                      </td>
                                      </tr>
                              </g:each>

                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                  </div>
                <g:each var="history" in="${session?.userHistoryList}">
                    <div class="col">
                      <div class="col-lg-4">
                        <!--comment1-->
                        <div class="panel panel-primary">

                              <div class="panel-heading" style="padding:5px;"><img  alt="" src="${history.get(2).getUserImage()}" height="35" onError="this.src='${session?.userInfo?.getNoImage()}';"> ${history.get(2).getName()} gave you  <button class="btn btn-default" disabled> <g:if test="${history?.get(0) > 0}"> <i class="fa fa-thumbs-up"> </g:if><g:else> <i class="fa fa-thumbs-down"> </g:else></i></button> ${history.get(0)} trophies on ${history.get(3)}</div>

                              <div class="panel-body">
                                ${history.get(1)}

                              </div>
                        </div>
                          <!--end comment1-->
                      </div>
                      </div>
	
                </g:each>

                                         
              </div>
               

          </section>
         
      </section>
      <!--main content end-->
  </section>
  <!-- container section start -->

      <!-- javascripts -->
    <script src="js/jquery.js"></script>
    <script src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
    <!-- bootstrap -->
    <script src="js/bootstrap.min.js"></script>


    
  <script>
    function isValidMissionInput(){

            var missionStatement =document.getElementById("missionStatement").value;


            if(missionStatement.trim().length < 1) {
                alert("Mission Statement is mandatory.")
                return false;
            }
            if(!isNumber(document.getElementById("trohpies").value)) {
                window.alert("Number of Stars on Offer should be a valid positive number.")
                return false;
            }

            document.getElementById("createMission").submit();
    }

    function isValidInput(){

        var emailIDContent =document.getElementById("toUserEmailID").value;
        var reason =document.getElementById("reason").value;

        var badgeId =document.getElementById("badgeId").value;


        if(emailIDContent.trim().length < 1) {
            alert("EmailID is mandatory.")
            return false;
        }
        if(reason.trim().length < 1) {
            alert("Reason is mandatory.")
            return false;
        }
        if(!isNumber(document.getElementById("trohpies").value)) {
            window.alert("Number of Stars should be a valid number.")
            return false;
        }
        if(badgeId == -1){
            alert("Badge is mandatory.")
            return false;
        }

        document.getElementById("updateTrophies").submit();

    }

    function isNumber(n) {
          return !isNaN(parseFloat(n)) && isFinite(n);
    }

	function setEmail(email)
	{
		document.getElementById("toUserEmailID").value=email;
	}
	
      //knob
      $(function() {
        $(".knob").knob({
          'draw' : function () { 
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
          $("#owl-slider").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });


  </script>

  </body>
</html>
