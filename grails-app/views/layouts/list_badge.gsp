
<%@ page import="com.ideas.game.Badge" %>
<!DOCTYPE html>
<html>
	<head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		    <meta name="description" content="IDeaS Rock Star"/>
		    <meta name="author" content="IDeaSCo"/>
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
	<body>
		  <section id="container" class="">
    	      <!--header start-->

              <header class="header white-bg">
                    <div class="toggle-nav">
                        <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
                    </div>

                    <!--logo start-->
                    <g:link controller="index" class="logo">IDeaS Rock Stars</g:link>
                    <g:link controller="levels.html" class="badge bg-important">Levels</g:link>
                    <g:link controller="badge" action="list" class="badge bg-important">Badges</g:link>

                    <!--logo end-->


              </header>
              <div class="col-lg-9">
              <p/>
              <p/><p/><p/>
                    <div class="panel panel-default">
                      <!-- Default panel contents -->
                      <div class="panel-heading">Badge</div>


                      <!-- Table -->
                    <table class="table">
                        <thead>
                            <tr>

                                <g:sortableColumn property="badgeName" title="${message(code: 'badge.badgeName.label', default: 'Badge Name')}" />

                                <g:sortableColumn property="badgeIcon" title="${message(code: 'badge.badgeIcon.label', default: 'Badge Icon')}" />

                                <g:sortableColumn property="isEvil" title="${message(code: 'badge.isEvil.label', default: 'Is Evil')}" />

                                <g:sortableColumn property="levelOnePoints" title="${message(code: 'badge.levelOnePoints.label', default: 'Bronze')}" />

                                <g:sortableColumn property="levelTwoPoints" title="${message(code: 'badge.levelTwoPoints.label', default: 'Silver')}" />

                                <g:sortableColumn property="levelThreePoints" title="${message(code: 'badge.levelThreePoints.label', default: 'Gold')}" />

                                <g:sortableColumn property="displayOrder" title="${message(code: 'badge.displayOrder.label', default: 'Display Order')}" />
                            </tr>
                        </thead>
                        <tbody>
                        <g:each in="${badgeInstanceList}" status="i" var="badgeInstance">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" id="${badgeInstance?.id}">${fieldValue(bean: badgeInstance, field: "badgeName")}</g:link></td>

                                <td>${fieldValue(bean: badgeInstance, field: "badgeIcon")}</td>

                                <td><g:formatBoolean boolean="${badgeInstance?.isEvil}" /></td>

                                <td>

                                         <span class="fa-stack fa-lg" >
                                             <i class="fa fa-circle fa-stack-2x" style="color: #cd7f32"></i>
                                             <g:if test="${badgeInstance.isEvil}">
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                             </g:if>
                                             <g:else>
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
                                             </g:else>
                                        </span>
                                     ${fieldValue(bean: badgeInstance, field: "levelOnePoints")}

                                     </td>

                                <td>
                                         <span class="fa-stack fa-lg" >
                                             <i class="fa fa-circle fa-stack-2x" style="color: #C0C0C0"></i>
                                             <g:if test="${badgeInstance.isEvil}">
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                             </g:if>
                                             <g:else>
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
                                             </g:else>
                                        </span>

                                ${fieldValue(bean: badgeInstance, field: "levelTwoPoints")}</td>

                                <td>
                                         <span class="fa-stack fa-lg" >
                                             <i class="fa fa-circle fa-stack-2x" style="color: #FFD700"></i>
                                             <g:if test="${badgeInstance.isEvil}">
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#000000;"></i>
                                             </g:if>
                                             <g:else>
                                                <i class="fa ${badgeInstance.badgeIcon} fa-stack-1x" style="color:#FFFFFF"></i>
                                             </g:else>
                                        </span>

                                ${fieldValue(bean: badgeInstance, field: "levelThreePoints")}</td>

                                <td>${fieldValue(bean: badgeInstance, field: "displayOrder")}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>


             </div>
                        <g:form >
                            <fieldset class="buttons">
                                <g:if test="${session.userInfo.isAdmin}">
                                    <g:actionSubmit class="create" action="create" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                               </g:if>
                            </fieldset>
                        </g:form>
          </section>
	</body>
</html>
