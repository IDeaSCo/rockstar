package com.ideas.game


class BadgeController {
    def dataSource

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        BadgeService badgeService = new BadgeService(dataSource)
        def badgeInstanceList;
        if(session.userInfo.isAdmin){
            badgeInstanceList = Badge.list();
        }else{
            badgeInstanceList = badgeService.listAvailableBadges(session.userInfo.department.departmentName);
        }

        [badgeInstanceList: badgeInstanceList , badgeInstanceTotal: Badge.count()]
    }

    def create() {
        if(session.userInfo.isAdmin == false) {
            redirect(action: "list")
            return
        }
        [badgeInstance: new Badge(params)]
    }

    def save() {
        if(session.userInfo.isAdmin == false) {
            redirect(action: "list")
            return
        }
        def badgeInstance = new Badge(params)
        if (!badgeInstance.save(flush: true)) {
            render(view: "create", model: [badgeInstance: badgeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'badge.label', default: 'Badge'), badgeInstance.id])
        redirect(action: "show", id: badgeInstance.id)
    }

    def show(Long id) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        [badgeInstance: badgeInstance]
    }

    def edit(Long id) {
        if(session.userInfo.isAdmin == false) {
            redirect(action: "list")
            return
        }
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        [badgeInstance: badgeInstance]
    }

    def update(Long id, Long version) {
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (badgeInstance.version > version) {
                badgeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'badge.label', default: 'Badge')] as Object[],
                        "Another user has updated this Badge while you were editing")
                render(view: "edit", model: [badgeInstance: badgeInstance])
                return
            }
        }

        badgeInstance.properties = params

        if (!badgeInstance.save(flush: true)) {
            render(view: "edit", model: [badgeInstance: badgeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'badge.label', default: 'Badge'), badgeInstance.id])
        redirect(action: "show", id: badgeInstance.id)
    }
}