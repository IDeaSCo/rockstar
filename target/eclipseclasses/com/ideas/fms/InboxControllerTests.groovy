package com.ideas.fms



import org.junit.*
import grails.test.mixin.*

@TestFor(InboxController)
@Mock(Inbox)
class InboxControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inbox/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inboxInstanceList.size() == 0
        assert model.inboxInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.inboxInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inboxInstance != null
        assert view == '/inbox/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inbox/show/1'
        assert controller.flash.message != null
        assert Inbox.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inbox/list'

        populateValidParams(params)
        def inbox = new Inbox(params)

        assert inbox.save() != null

        params.id = inbox.id

        def model = controller.show()

        assert model.inboxInstance == inbox
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inbox/list'

        populateValidParams(params)
        def inbox = new Inbox(params)

        assert inbox.save() != null

        params.id = inbox.id

        def model = controller.edit()

        assert model.inboxInstance == inbox
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inbox/list'

        response.reset()

        populateValidParams(params)
        def inbox = new Inbox(params)

        assert inbox.save() != null

        // test invalid parameters in update
        params.id = inbox.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inbox/edit"
        assert model.inboxInstance != null

        inbox.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inbox/show/$inbox.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inbox.clearErrors()

        populateValidParams(params)
        params.id = inbox.id
        params.version = -1
        controller.update()

        assert view == "/inbox/edit"
        assert model.inboxInstance != null
        assert model.inboxInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inbox/list'

        response.reset()

        populateValidParams(params)
        def inbox = new Inbox(params)

        assert inbox.save() != null
        assert Inbox.count() == 1

        params.id = inbox.id

        controller.delete()

        assert Inbox.count() == 0
        assert Inbox.get(inbox.id) == null
        assert response.redirectedUrl == '/inbox/list'
    }
}
