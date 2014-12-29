<%@ page import="com.ideas.game.Badge" %>

    <div class="panel panel-default">
      <!-- Default panel contents -->
      <div class="panel-heading">Badge</div>


      <!-- Table -->
      <table class="table">
        <tr><td><g:message code="badge.badgeName.label" default="Badge Name" /></td><td><g:textField name="badgeName" value="${badgeInstance?.badgeName}"/></td></tr>
        <tr><td><g:message code="badge.badgeIcon.label" default="Badge Icon" /></td><td><g:textField name="badgeIcon" value="${badgeInstance?.badgeIcon}"/></td></tr>
        <tr><td><g:message code="badge.isEvil.label" default="Is Evil" /></td><td><g:checkBox name="isEvil" value="${badgeInstance?.isEvil}" /></td></tr>
        <tr><td><g:message code="badge.levelOnePoints.label" default="Bronze" /></td><td><g:field name="levelOnePoints" type="number" value="${badgeInstance?.levelOnePoints}" required=""/></td></tr>
        <tr><td><g:message code="badge.levelTwoPoints.label" default="Silver" /></td><td><g:field name="levelTwoPoints" type="number" value="${badgeInstance?.levelTwoPoints}" required=""/></td></tr>
        <tr><td><g:message code="badge.levelThreePoints.label" default="Gold" /></td><td><g:field name="levelThreePoints" type="number" value="${badgeInstance?.levelThreePoints}" required=""/></td></tr>
        <tr><td><g:message code="badge.displayOrder.label" default="Display Order" /></td><td><g:field name="displayOrder" type="number" value="${badgeInstance?.displayOrder}" required=""/></td></tr>

      </table>
    </div>
    </div>


