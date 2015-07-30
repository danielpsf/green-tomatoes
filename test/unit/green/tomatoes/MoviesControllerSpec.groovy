package green.tomatoes

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MoviesController)
class MoviesControllerSpec extends Specification {

    def setup() {
		controller.moviesService = Mock(MoviesService)
    }

    def "should return the movies in box office"() {
		given:
		controller.params.count = 5
		
		when:
		controller.getBoxOffice()
		
		then:
		1 * controller.moviesService.getBoxOffice(5) >> [
			movies : [
				[title:"Ant-Man"],
				[title:"Pixels"],
				[title:"Minions"],
				[title:"Trainwreck"],
				[title:"Southpaw"]
			]
		]
		
		and:
		response.status == 200
		response.json == [
			movies : [
				[title:"Ant-Man"],
				[title:"Pixels"],
				[title:"Minions"],
				[title:"Trainwreck"],
				[title:"Southpaw"]
			]
		]
	}
}
