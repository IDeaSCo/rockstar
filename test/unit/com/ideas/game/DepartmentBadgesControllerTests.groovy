package com.ideas.game



import org.junit.*
import grails.test.mixin.*

@TestFor(DepartmentBadgesController)
@Mock(DepartmentBadges)
class DepartmentBadgesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/departmentBadges/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.departmentBadgesInstanceList.size() == 0
        assert model.departmentBadgesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.departmentBadgesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.departmentBadgesInstance != null
        assert view == '/departmentBadges/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/departmentBadges/show/1'
        assert controller.flash.message != null
        assert DepartmentBadges.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/departmentBadges/list'

        populateValidParams(params)
        def departmentBadges = new DepartmentBadges(params)

        assert departmentBadges.save() != null

        params.id = departmentBadges.id

        def model = controller.show()

        assert model.departmentBadgesInstance == departmentBadges
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/departmentBadges/list'

        populateValidParams(params)
        def departmentBadges = new DepartmentBadges(params)

        assert departmentBadges.save() != null

        params.id = departmentBadges.id

        def model = controller.edit()

        assert model.departmentBadgesInstance == departmentBadges
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/departmentBadges/list'

        response.reset()

        populateValidParams(params)
        def departmentBadges = new DepartmentBadges(params)

        assert departmentBadges.save() != null

        // test invalid parameters in update
        params.id = departmentBadges.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/departmentBadges/edit"
        assert model.departmentBadgesInstance != null

        departmentBadges.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/departmentBadges/show/$departmentBadges.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        departmentBadges.clearErrors()

        populateValidParams(params)
        params.id = departmentBadges.id
        params.version = -1
        controller.update()

        assert view == "/departmentBadges/edit"
        assert model.departmentBadgesInstance != null
        assert model.departmentBadgesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/departmentBadges/list'

        response.reset()

        populateValidParams(params)
        def departmentBadges = new DepartmentBadges(params)

        assert departmentBadges.save() != null
        assert DepartmentBadges.count() == 1

        params.id = departmentBadges.id

        controller.delete()

        assert DepartmentBadges.count() == 0
        assert DepartmentBadges.get(departmentBadges.id) == null
        assert response.redirectedUrl == '/departmentBadges/list'
    }
}
