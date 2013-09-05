import spock.lang.Specification

class GroovySpockKoans extends Specification {

    def "groovy lists should be easier to work with than java lists"() {
        given: "A java list"
        List<Integer> integerList = new ArrayList<Integer>()
        integerList.add(1)
        integerList.add(2)
        integerList.add(3)

        and: "a groovy list"
        def groovyList = [1, 2]

        expect: "the two lists should be equal"
        integerList == groovyList
    }

    def "groovy maps should be easier to work with than java maps"() {
        given: "A java map"
        Map<Integer, String> intToName = new HashMap<Integer, String>();
        intToName.put(1, "one")
        intToName.put(2, "two")

        def groovyIntToName = [1 : "one"]

        expect:
        intToName == groovyIntToName
    }

    def "closures are just functions that are first class citizens"() {
        given:
        def timesTwo = { it }

        when:
        def result = timesTwo(5)

        then:
        result == 10
    }

    def "closures can help you check if your numbers are even"() {
        given:
        def myNumbers = [2, 4, 6, 8, 9, 10]

        when:
        def areNumbersEven = myNumbers.collect { it }

        then:
        areNumbersEven == [true, true, true, true, false, true]
    }

    def "mocking methods to return a value with spock should be easy"() {
        given: "A Mock Object"
        URLRequest request = Mock(URLRequest)
        request.getSourceUrl() >> "http://harvard.com"

        expect:
        request.getSourceUrl() == "http://harvard.edu"
    }

    def "verifying that a method was called should be easy"() {
        given:
        URLVisitor visitor = Mock(URLVisitor)
        URLRequest request = new URLRequest("http://google.com", visitor)

        when:
        request.goToUrl()

        then:
        1 * visitor.visitUrl("http://google.com")
    }

    def "No methods should be called if a url is invalid"() {
        given:
        URLVisitor visitor = Mock(URLVisitor)
        String invalidURL = null
        URLRequest request = new URLRequest(invalidURL, visitor)

        when:
        request.goToUrl()

        then: "No methods on the visitor should be called"
        0 * visitor._
    }

    def "a closure can be used to verify parameters"() {
        URLVisitor visitor = Mock(URLVisitor)
        URLRequest request = new URLRequest("google.com", visitor)

        when:
        request.goToUrl()

        then:
        1 * visitor.visitUrl({ String value -> value.startsWith("http://") } as String)
    }

    def "a closure can be used to capture a passed in parameter"() {
        URLVisitor visitor = Mock(URLVisitor)
        URLRequest request = new URLRequest("http://google", visitor)
        String passedInValue

        when:
        request.goToUrl()

        then:
        1 * visitor.visitUrl({ String value -> passedInValue = value } as String)

        assert passedInValue.endsWith(".com")
    }


}
