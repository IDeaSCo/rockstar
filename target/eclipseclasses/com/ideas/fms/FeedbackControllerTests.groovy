package com.ideas.fms



import org.junit.*
import grails.test.mixin.*

@TestFor(FeedbackController)
@Mock(Feedback)
class FeedbackControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/feedback/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.feedbackInstanceList.size() == 0
        assert model.feedbackInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.feedbackInstance != null
    }

    void testSave() {
        controller.save()

        assert model.feedbackInstance != null
        assert view == '/feedback/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/feedback/show/1'
        assert controller.flash.message != null
        assert Feedback.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/feedback/list'

        populateValidParams(params)
        def feedback = new Feedback(params)

        assert feedback.save() != null

        params.id = feedback.id

        def model = controller.show()

        assert model.feedbackInstance == feedback
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/feedback/list'

        populateValidParams(params)
        def feedback = new Feedback(params)

        assert feedback.save() != null

        params.id = feedback.id

        def model = controller.edit()

        assert model.feedbackInstance == feedback
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/feedback/list'

        response.reset()

        populateValidParams(params)
        def feedback = new Feedback(params)

        assert feedback.save() != null

        // test invalid parameters in update
        params.id = feedback.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/feedback/edit"
        assert model.feedbackInstance != null

        feedback.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/feedback/show/$feedback.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        feedback.clearErrors()

        populateValidParams(params)
        params.id = feedback.id
        params.version = -1
        controller.update()

        assert view == "/feedback/edit"
        assert model.feedbackInstance != null
        assert model.feedbackInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/feedback/list'

        response.reset()

        populateValidParams(params)
        def feedback = new Feedback(params)

        assert feedback.save() != null
        assert Feedback.count() == 1

        params.id = feedback.id

        controller.delete()

        assert Feedback.count() == 0
        assert Feedback.get(feedback.id) == null
        assert response.redirectedUrl == '/feedback/list'
    }
}
