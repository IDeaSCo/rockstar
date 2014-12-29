package com.ideas.game



import org.junit.*
import grails.test.mixin.*

@TestFor(BadgeController)
@Mock(Badge)
class BadgeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/badge/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.badgeInstanceList.size() == 0
        assert model.badgeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.badgeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.badgeInstance != null
        assert view == '/badge/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/badge/show/1'
        assert controller.flash.message != null
        assert Badge.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        params.id = badge.id

        def model = controller.show()

        assert model.badgeInstance == badge
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        params.id = badge.id

        def model = controller.edit()

        assert model.badgeInstance == badge
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        response.reset()

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null

        // test invalid parameters in update
        params.id = badge.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/badge/edit"
        assert model.badgeInstance != null

        badge.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/badge/show/$badge.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        badge.clearErrors()

        populateValidParams(params)
        params.id = badge.id
        params.version = -1
        controller.update()

        assert view == "/badge/edit"
        assert model.badgeInstance != null
        assert model.badgeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/badge/list'

        response.reset()

        populateValidParams(params)
        def badge = new Badge(params)

        assert badge.save() != null
        assert Badge.count() == 1

        params.id = badge.id

        controller.delete()

        assert Badge.count() == 0
        assert Badge.get(badge.id) == null
        assert response.redirectedUrl == '/badge/list'
    }
}
