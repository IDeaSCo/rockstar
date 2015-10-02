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
		    <meta name="author" content="IDeaSCo"/>
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
            <g:link controller="leader" action="index" class="logo">IDeaS Rock Stars</g:link>            
            <g:link controller="levels.html" class="badge bg-important">Levels</g:link>
            <g:link controller="badge" action="list" class="badge bg-important">Badges</g:link>
            <!--logo end-->
 
            
      </header>      
        <div class="container">
            <!--right side ocntent starts-->
            <div id="fluid" class="col-lg-12">
                <div style="background: #EDEDED;padding: 1px;padding-left: 10px;margin-top: 10px;">
                    <h3>Badges</h3>
                </div>
                <div>
                    <p style="word-spacing: 15px; padding: 10px;">
                                  <g:each status="ii" var="userBadge" in="${request?.userBadges}">
                                          <span class="fa-stack fa-lg" title="${userBadge.badge.badgeName} - Level - ${userBadge.getLevelName()}" onMouseOver="setReasonDivContent(${userBadge.badge.id},'${userBadge.badge.badgeName}')">
				             <i class="fa fa-circle fa-stack-2x" style="color: ${userBadge.getLevelColor()};"></i>
				             <g:if test="${userBadge.badge.isEvil}">
                                             	<i class="fa ${userBadge.badge.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                             </g:if>	
				             <g:else>
                                             	<i class="fa ${userBadge.badge.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
                                             </g:else>	
                                             <span class="badge bg-important" style="position: relative;left: 30px;top: -15px;background: #FF4F2D;">${userBadge.points}</span>
                                          </span>
                                 </g:each>

                        
                    </p>
                    
                </div>
                
                <div style="background: #EDEDED;padding: 1px;padding-left: 10px;margin-top: 10px;">
                    <h3><div id="work_title"> </div></h3>
                    
                    <div id="work" >
                    	
                    </div>
                    
                    <g:each status="i" var="badge" in="${request?.reasonOfStarsReceivedByBadge?.keySet()}">
                    	<div id="work_${badge}" style="visibility: hidden;">
                    		<g:each status="j" var="reasonList" in="${request?.reasonOfStarsReceivedByBadge?.get(badge)}">	
				    <div class="col">
				      <div class="col-lg-9">
					<!--comment1-->
					<div class="panel panel-primary">

					      <div class="panel-heading" style="padding:5px;"><img  alt="" src="${reasonList.get(0).trophiesGivenBy.getUserImage()}" height="35" onError="this.src='${session?.userInfo?.getNoImage()}';"> ${reasonList.get(0).trophiesGivenBy.getName()} gave <button class="btn btn-default" disabled> <g:if test="${reasonList.get(0).badge.isEvil == false}"> <i class="fa fa-thumbs-up"> </g:if><g:else> <i class="fa fa-thumbs-down"> </g:else></i></button> ${reasonList.get(0).trophies} trophies on ${reasonList.get(0).date}</div>

					      <div class="panel-body">
						${reasonList.get(1)}

					      </div>
					</div>
					  <!--end comment1-->
				      </div>
				      </div>

		                 </g:each>

			</div>			      
                    </g:each>
                    
                </div>
                
                
              
            </div>
            <!--right side content ends-->
            <!--left fix panel starts-->
            <div id="fixed">
                      <div class="profile-widget profile-widget-info">
                          <div class="panel-body">
                            <div class="col-lg-4 col-sm-4 profile-widget-name">
                              <h4>${request?.userInstance?.getName()}</h4>               
                              <div class="follow-ava">
                                 <g:link controller="user" action="view" id="${request?.userInstance?.id}" > <img src="${request?.userInstance?.getUserImage()}" alt="" onError="this.src='${request?.userInstance?.getNoImage()}';"></g:link>
                                  
                              </div>
                              <h6>${request?.userInstance?.getTitle()}</h6>
                            </div>
                            <div class="col-lg-8 col-sm-8 follow-info">
                                <p style="font-size: 50px;font-weight: bolder;color: #222;">${request?.userInstance?.getTrophies()} <i style="padding-left:10px; color:blue" class="fa fa-star"></i>
                              <p>
                              </p>
                             </div>
                          </div>
      
            </div>
            
            <!--left fixed panel ends-->
        </div>


  </section>
  <!-- container section start -->

    
  <script>
  	function setReasonDivContent(divId, badgeName){
  	
  		document.getElementById("work").innerHTML=document.getElementById("work_"+divId).innerHTML;  		
  		document.getElementById("work_title").innerHTML="Badge - "+badgeName;  		
  	}
</script>

  </body>
</html>
