
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
                    <g:link controller="leader" action="index" class="logo">IDeaS Rock Stars</g:link>
                    <g:link controller="levels.html" class="badge bg-important">Levels</g:link>
                    <g:link controller="badge" action="list" class="badge bg-important">Badges</g:link>

                    <!--logo end-->


              </header>
              <div class="col-lg-9">
              <p/>
              <p/><p/><p/>
                    <div class="panel panel-default">
                      <!-- Default panel contents -->
                      <g:form action="save">
                          <div class="panel-heading">${session.userInfo.department.departmentName}</div>


                                <g:if test="${flash.message}">
                                <div class="message" role="status">${flash.message}</div>
                                </g:if>
                                <table class="table">
                                    <thead >
                                        <tr>
                                            <th><g:message code="departmentBadges.badge.label" default="Badge" /></th>
                                            <th><g:message code="departmentBadges.available.label" default="Available" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <g:hiddenField name="badge_id" value="-1" />
                                    <g:each in="${badgeList}" status="i" var="badge">
                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                            <td>${fieldValue(bean: badge, field: "badgeName")}</td>

                                            <td><g:checkBox name="badge_id" value="${badge.id}" checked="${departmentBadgesList.contains(badge)}" disabled="${defaultDepartmentBadgeList.contains(badge)}"/></td>

                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </div>

                            <g:if test="${session.userInfo.isAdmin}">
                                  <fieldset class="buttons">
                                    <g:actionSubmit class="save" action="save" value="${message(code: 'default.button.save.label', default: 'Save')}" />
                                </fieldset>
                            </g:if>
                        </g:form>

                    </div>

           </div>
          </section>
	</body>
</html>
