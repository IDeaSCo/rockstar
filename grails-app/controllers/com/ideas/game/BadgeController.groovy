package com.ideas.game


class BadgeController {


    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        println "in List"
        [badgeInstanceList: Badge.list(), badgeInstanceTotal: Badge.count()]
    }

    def create() {
        [badgeInstance: new Badge(params)]
    }

    def save() {

        println params;
        def badgeInstance = new Badge(params)
        if (!badgeInstance.save(flush: true)) {
            render(view: "create", model: [badgeInstance: badgeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'badge.label', default: 'Badge'), badgeInstance.id])
        redirect(action: "show", id: badgeInstance.id)
    }

    def show(Long id) {
        println "id:"+id
        def badgeInstance = Badge.get(id)
        if (!badgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), id])
            redirect(action: "list")
            return
        }

        [badgeInstance: badgeInstance]
    }

    def edit(Long id) {
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
