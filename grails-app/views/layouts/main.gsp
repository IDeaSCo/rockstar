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
                <meta http-equiv="refresh" content="900">
		    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		    <meta name="description" content="IDeaS Rock Star"/>
		    <meta name="author" content="IDeaSLabs"/>
		    
		    <title> IDeaS Rock Star</title>
			<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"/>

		    <link href="${resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'css', file: 'bootstrap-theme.css')}" rel="stylesheet" type="text/css"/>


		    <link href="${resource(dir: 'assets/fullcalendar/fullcalendar', file: 'bootstrap-fullcalendar.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'assets/jquery-easy-pie-chart', file: 'jquery.easy-pie-chart.css')}" rel="stylesheet" type="text/css" media="screen"/>
		    <link rel="stylesheet" href="${resource(dir: 'css', file: 'owl.carousel.css')}" type="text/css">
		    <link href="${resource(dir: 'css', file: 'style.css')}" rel="stylesheet" type="text/css"/>
		    <link href="${resource(dir: 'css', file: 'style-responsive.css')}" rel="stylesheet" type="text/css"/>

               
	</head>
	<r:layoutResources/>
	<body>
          
          
	  <section id="container" class="">
	      <!--header start-->

	      <header class="header white-bg">
		    <div class="toggle-nav">
			<div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
		    </div>

		    <a href="#" class="logo">IDeaS Rock Stars</a>
            <a class="badge bg-important" href="levels.html" >Levels</a>
            <g:link controller="badge" action="list" class="badge bg-important">Badges</g:link>
		    <div class="top-nav notification-row">

			<ul class="nav pull-right top-menu">
			    <li class="dropdown" style="top:5px;">
					<g:link controller="user" action="profile" >
						<span class="profile-ava" title="${session?.userInfo?.getName()}">
						    <img alt="" src="${session?.userInfo?.getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" height="35" width="30">
						</span>
						<span class="username">${session?.userInfo?.getName()}
						</span>
				        </g:link>

			    </li>

		       </ul>

		  </div>
	      </header>
<!--container starts-->
<div class="container">
<!--right side content starts-->
<div id="fluid" class="col-lg-10">
      <section id="main-content">
          <section class="wrapper">
              <!--overview start-->
              <div class="row state-overview">
                  <div class="col-lg-4">

                </div>

              </div>

 <!--today start-->
              <div class="col-lg-4">

                        <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Day</a>
                                  </li>

                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                               <div id="mainwinner">
                                 <g:if test="${request?.starOfTheDayMap?.size() > 0}">
                                   <div style="width:75px;">

                                   	<g:link controller="user" action="view" id="${((com.ideas.game.User)request?.starOfTheDayMap?.keySet()?.toList()?.first())?.id}" ><img style="height:72px;width72px;padding-bottom: 5px;" alt=""  src="${((com.ideas.game.User)request?.starOfTheDayMap?.keySet()?.toList()?.first())?.getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                   	<span class="badge bg-important" style="position: relative;left: 30px;bottom: 15px;background: #FF4F2D;">${((com.ideas.game.User)request?.starOfTheDayMap?.keySet()?.toList()?.first())?.getUserBadge()}</span>
                                   </div>
                                   
                                   <div style="padding-left:0px;"> <p class="profile-name">${request?.starOfTheDayMap?.keySet()?.toList()?.first()?.getName()}</p> </div>
                                   <div style="width: 45%;text-align: right;"><span class="badge bg-info" style="font-size:30px">${request?.starOfTheDayMap?.get(request?.starOfTheDayMap?.keySet()?.toList()?.first())}</span> <i style="padding-left:10px;font-size:50px; color:blue;" class="fa fa-star"></i></div>
                                 </g:if>
 
                                </div>

                              <table class="table table-hover personal-task">
                              <tbody>
                                    <g:each status="i" var="user" in="${request?.starOfTheDayMap?.keySet()}">
                                                    <g:if test="${i > 0}">
                                        <tr>

                                            <!--<td>
                                             <p class="badge bg-info1">2</p>
                                          </td>-->
                                          <td style="width:10px;">
                                            <span >
                                            <g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                            <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
                                            </span>
                                          </td>
                                          <td>
                                              <p class="profile-name">${user.getName()}</p>
                                          </td>
                                          <td>
                                              <span class="badge bg-info" style="font-size:20px">${request?.starOfTheDayMap?.get(user)}</span>
                                              <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                          </td>
                                          </tr>
                                      </g:if>
                                      </g:each>

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

              <!--weekly winner's starts-->
                 <div class="col-lg-4">

                        <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Week</a>
                                  </li>

                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                               <div id="mainwinner">
                                 <g:if test="${request?.starOfTheWeekMap?.size() > 0}">
                                   <div style="width:75px;"> 
                                   <g:link controller="user" action="view" id="${((com.ideas.game.User)request?.starOfTheWeekMap?.keySet()?.toList()?.first()).id}" ><img style="height:72px;width72px;padding-bottom: 5px;" alt=""  src="${((com.ideas.game.User)request?.starOfTheWeekMap?.keySet()?.toList()?.first()).getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                   <span class="badge bg-important" style="position: relative;left: 30px;bottom: 15px;background: #FF4F2D;">${((com.ideas.game.User)request?.starOfTheWeekMap?.keySet()?.toList()?.first()).getUserBadge()}</span>
                                   </div>
                                   <div style="padding-left:20px;"> <p class="profile-name">${request?.starOfTheWeekMap?.keySet().toList().first().getName()}</p> </div>
                                   <div style="width: 45%;text-align: right;"><span class="badge bg-info" style="font-size:30px">${request?.starOfTheWeekMap?.get(request?.starOfTheWeekMap?.keySet().toList().first())}</span> <i style="padding-left:10px;font-size:50px; color:blue;" class="fa fa-star"></i></div>
                                 </g:if>
                                </div>

                        <table class="table table-hover personal-task">
                              <table class="table table-hover personal-task">
                              <tbody>
                                <g:each status="i" var="user" in="${request?.starOfTheWeekMap?.keySet()}">
                                                <g:if test="${i > 0}">
                                    <tr>

                                        <!--<td>
                                         <p class="badge bg-info1">2</p>
                                      </td>-->
                                      <td style="width:10px;">
                                        <span >
                                        <g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                        <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
                                        </span>
                                      </td>
                                      <td>
                                          <p class="profile-name">${user.getName()}</p>
                                      </td>
                                      <td>
                                          <span class="badge bg-info" style="font-size:20px">${request?.starOfTheWeekMap?.get(user)}</span>
                                         <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                      </td>
                                      </tr>
                                  </g:if>
                                  </g:each>

                              </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>

                              </div>
                          </div>
                      </section>
              </div>
              <!--weekly winner's ends-->

              <!--monthly winner's starts-->
                <div class="col-lg-4">

                        <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active">
                                      <a data-toggle="tab" href="#home">Star of the Month</a>
                                  </li>

                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                              <section class="panel">
                               <div id="mainwinner">
                                 <g:if test="${request?.starOfTheMonthMap?.size() > 0}">
                                   <div style="width:75px;"> 
                                   <g:link controller="user" action="view" id="${((com.ideas.game.User)request?.starOfTheMonthMap?.keySet()?.toList()?.first()).id}" ><img style="height:72px;width72px;padding-bottom: 5px;" alt=""  src="${((com.ideas.game.User)request?.starOfTheMonthMap?.keySet()?.toList()?.first()).getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                   <span class="badge bg-important" style="position: relative;left: 30px;bottom: 15px;background: #FF4F2D;">${((com.ideas.game.User)request?.starOfTheMonthMap?.keySet()?.toList()?.first()).getUserBadge()}</span>
                                   </div>
                                   <div style="padding-left:20px;"> <p class="profile-name">${request?.starOfTheMonthMap?.keySet().toList().first().getName()}</p> </div>
                                   <div style="width: 45%;text-align: right;"><span class="badge bg-info" style="font-size:30px">${request?.starOfTheMonthMap?.get(request?.starOfTheMonthMap?.keySet().toList().first())}</span> <i style="padding-left:10px;font-size:50px; color:blue;" class="fa fa-star"></i></div>
                                 </g:if>
                                </div>

                            <table class="table table-hover personal-task">
                                  <table class="table table-hover personal-task">
                                  <tbody>
                                <g:each status="i" var="user" in="${request?.starOfTheMonthMap?.keySet()}">
                                <g:if test="${i > 0}">
                                    <tr>

                                        <!--<td>
                                         <p class="badge bg-info1">2</p>
                                      </td>-->
                                      <td style="width:10px;">
                                        <span >
                                        <g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                        <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
                                        </span>
                                      </td>
                                      <td style="width:190px;">
                                          <p class="profile-name">${user.getName()}</p>

                                      </td>
                                      <td>
                                          <span class="badge bg-info" style="font-size:20px">${request?.starOfTheMonthMap?.get(user)}</span>
                                         <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                      </td>
                                      </tr>
                                  </g:if>
                                  </g:each>

                                  </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>

                              </div>
                          </div>
                      </section>
              </div>
              <!--monthly winner's ends-->

                 <!--awardee starts-->
              <div class="col-lg-4">

                        <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active" >
                                      <a style="background-color:#45B941;" data-toggle="tab" href="#home">Most Appreciated</a>
                                  </li>

                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                      <section class="panel">

                        <table class="table table-hover personal-task">
				      <tbody>
					<g:each status="i" var="user" in="${request?.starMap?.keySet()}">
						<tr>

						    <!--<td>
						     <p class="badge bg-info1">2</p>
						  </td>-->
						  <td style="width:10px;">
						    <span >
							<g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
							<span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
						    </span>
						  </td>
						  <td>
						      <p class="profile-name">${user.getName()}</p>

						  </td>
						  <td>
						      <span class="badge bg-info" style="font-size:20px">${request?.starMap?.get(user)}</span>
						     <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
						  </td>
					      </tr>
				      </g:each>

				      </tbody>
                          </table>
                      </section>
                      <!--Project Team end-->
                                  </div>

                              </div>
                          </div>
                      </section>
              </div>
              <!--awardee end-->
               <!--awardee starts-->
               <div class="col-lg-4">

                        <section class="panel">
                          <header class="panel-heading tab-bg-primary ">
                              <ul class="nav nav-tabs">
                                  <li class="active" >
                                      <a style="background-color:#45B941;" data-toggle="tab" href="#home">Most Appreciative</a>
                                  </li>

                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="home" class="tab-pane active">
                                      <!--project team start-->
                                      <section class="panel">

                                        <table class="table table-hover personal-task">
                                              <tbody>
                                                <g:each status="i" var="user" in="${request?.appreciatorMap?.keySet()}">
                                                    <tr>

                                                        <!--<td>
                                                         <p class="badge bg-info1">2</p>
                                                      </td>-->
                                                      <td style="width:10px;">
                                                        <span >
                                                        <g:link controller="user" action="view" id="${user.id}" ><img alt="" class="simple" src="${user.getUserImage()}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';"/></g:link>
                                                        <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${user.getUserBadge()}</span>
                                                        </span>
                                                      </td>
                                                      <td>
                                                          <p class="profile-name">${user.getName()}</p>

                                                      </td>
                                                      <td>
                                                          <span class="badge bg-info" style="font-size:20px">${request?.appreciatorMap?.get(user)}</span>
                                                          <i style="padding-left:10px; color:blue;" class="fa fa-star"></i>
                                                      </td>
                                                      </tr>
                                                    </g:each>

                                              </tbody>
                                          </table>
                                      </section>
                                      <!--Project Team end-->
                                  </div>

                              </div>
                          </div>
                      </section>
              </div>

                             <div class="col-lg-4">

                                      <section class="panel">
                                        <header class="panel-heading tab-bg-primary ">
                                            <ul class="nav nav-tabs">
                                                <li class="active" >
                                                    <a style="background-color:#45B941;" data-toggle="tab" href="#home">Open Missions</a>
                                                </li>

                                            </ul>
                                        </header>
                                        <div class="panel-body">
                                            <div class="tab-content">
                                                <div id="home" class="tab-pane active">
                                                    <!--project team start-->
                                                    <section class="panel">

                                                      <table class="table table-hover personal-task">
                                                            <tbody>
                                                              <g:each status="i" var="mission" in="${request?.openMission}">
                                                                  <tr>

                                                                      <!--<td>
                                                                       <p class="badge bg-info1">2</p>
                                                                    </td>-->
                                                                    <td style="width:10px;">
                                                                      <span >
                                                                      <img alt="" class="simple" src="${mission.get(0)}" height="40" onError="this.src='${session?.userInfo?.getNoImage()}';">
                                                                      </span>
                                                                    </td>
                                                                    <td>
                                                                        <p class="profile-occupation">${mission.get(1)}</p>
                                                                        <p class="profile-name" >${mission.get(2)}</p>
                                                                    </td>
                                                                    <td>
                                                                        <span class="badge bg-info" style="font-size:20px">${mission.get(3)}</span>
                                                                        <i style="color:blue;" class="fa fa-star"></i>
                                                                    </td>
                                                                    <td>
                                                                    <g:if test="${mission.get(4).toLowerCase().equals(session.userInfo.email.toLowerCase()) }">
                                                                        <g:form id="closeMission" name="closeMission" controller="mission" action="close">
                                                                                <input type="hidden" name="missionID" value="${mission.get(5)}">
                                                                                <g:submitButton name="close" class="btn btn-primary" value="Close"/>
                                                                        </g:form>
                                                                    </g:if>
                                                                    </td>


                                                                    </tr>


                                                                  </g:each>

                                                            </tbody>
                                                        </table>
                                                    </section>
                                                    <!--Project Team end-->
                                                </div>

                                            </div>
                                        </div>
                                    </section>
                            </div>
              <!--awardee end-->

          </section>
      </section>
      <!--main content end-->
      </div>
                <div id="fixed">
                    <g:each status="i" var="badge" in="${request?.badgeLeaderBoard?.keySet()}">
                      <div id="badge${i}" style="text-align: left;  padding-left: 10px;">
                          <p style="word-spacing: 15px;">
                              <div>
                                  <div class="badge-icon">
                                  	<g:if test="${request?.badgeLeaderBoard?.get(badge).size()>0}">
                                  	    <span class="fa-stack fa-lg" title="${badge.badgeName} - Level - ${request?.badgeLeaderBoard?.get(badge)?.get(0)?.getLevelName()}">
                                  	</g:if>
                                  	<g:else>
                                  		<span class="fa-stack fa-lg" title="${badge.badgeName} - Level - Amature">
                                  	</g:else>
                                    <g:if test="${request?.badgeLeaderBoard?.get(badge).size()>0}">
                                        <i class="fa fa-circle fa-stack-2x" style="color: ${request?.badgeLeaderBoard?.get(badge)?.get(0)?.getLevelColor()};"></i>
                                    </g:if>
                                    <g:else>
                                        <i class="fa fa-circle fa-stack-2x" style="color: #008000"></i>
                                    </g:else>
                                    <g:if test="${badge.isEvil}">
                                            <i class="fa ${badge.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                    </g:if>
                                     <g:else>
                                        <i class="fa ${badge.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
                                     </g:else>
                                      </span>
                                  </div>
                                  <div class="badge-leader">
                                       <p>${badge.badgeName}</p>
                                      <g:each status="j" var="userBadge" in="${request?.badgeLeaderBoard?.get(badge)}">
                                          <span title="${userBadge?.user?.getName()}">
                                            <g:link controller="user" action="view" id="${userBadge?.user?.id}" ><img src="${userBadge?.user?.getUserImage()}" onError="this.src='${session?.userInfo?.getNoImage()}';" alt="" style="width:35px; height: 35px;"/></g:link>
                                          </span>
                                      </g:each>
                                  </div>
                                  <div style="clear:both"></div>
                              	  </div>
                          </p>
                          <hr>
                      </div>

                    </g:each>
        </div>
  </section>
  <!-- container section start -->

    <!-- javascripts -->


  <script>

  </script>

  </body>
</html>
