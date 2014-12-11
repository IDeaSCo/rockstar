package com.ideas.fms



import org.junit.*
import grails.test.mixin.*

@TestFor(SentItemsController)
@Mock(SentItems)
class SentItemsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/sentItems/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.sentItemsInstanceList.size() == 0
        assert model.sentItemsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.sentItemsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.sentItemsInstance != null
        assert view == '/sentItems/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/sentItems/show/1'
        assert controller.flash.message != null
        assert SentItems.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/sentItems/list'

        populateValidParams(params)
        def sentItems = new SentItems(params)

        assert sentItems.save() != null

        params.id = sentItems.id

        def model = controller.show()

        assert model.sentItemsInstance == sentItems
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/sentItems/list'

        populateValidParams(params)
        def sentItems = new SentItems(params)

        assert sentItems.save() != null

        params.id = sentItems.id

        def model = controller.edit()

        assert model.sentItemsInstance == sentItems
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/sentItems/list'

        response.reset()

        populateValidParams(params)
        def sentItems = new SentItems(params)

        assert sentItems.save() != null

        // test invalid parameters in update
        params.id = sentItems.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/sentItems/edit"
        assert model.sentItemsInstance != null

        sentItems.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/sentItems/show/$sentItems.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        sentItems.clearErrors()

        populateValidParams(params)
        params.id = sentItems.id
        params.version = -1
        controller.update()

        assert view == "/sentItems/edit"
        assert model.sentItemsInstance != null
        assert model.sentItemsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/sentItems/list'

        response.reset()

        populateValidParams(params)
        def sentItems = new SentItems(params)

        assert sentItems.save() != null
        assert SentItems.count() == 1

        params.id = sentItems.id

        controller.delete()

        assert SentItems.count() == 0
        assert SentItems.get(sentItems.id) == null
        assert response.redirectedUrl == '/sentItems/list'
    }
}
