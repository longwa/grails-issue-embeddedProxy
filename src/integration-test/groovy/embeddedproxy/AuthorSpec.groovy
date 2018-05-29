package embeddedproxy

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.codehaus.groovy.runtime.metaclass.ClosureMetaMethod
import spock.lang.Specification

@Integration
@Rollback
class AuthorSpec extends Specification {
    Long authorId

    void setupData() {
        Author.withNewSession {
             Author auth = new Author(
                name: 'Aaron',
                activePeriod: new Period(startDate: new Date(), endDate: null),
                publisher: new Publisher(name: 'Foo')
            )
            auth.save(flush: true, failOnError: true)
            authorId = auth.id
        }
    }

    void "embedded attribute should have metaProperty type of Period"() {
        setupData()

        when:
        Author auth = Author.get(authorId)

        then:
        MetaBeanProperty beanProperty = auth.hasProperty('activePeriod') as MetaBeanProperty
        beanProperty.type == Period
    }

    void "embedded attribute should not have getter and setter wrapped in closure" () {
        setupData()

        when:
        Author auth = Author.get(authorId)

        then:
        MetaBeanProperty beanProperty = auth.hasProperty('activePeriod') as MetaBeanProperty
        beanProperty.getter.getClass() != ClosureMetaMethod
        beanProperty.setter.getClass() != ClosureMetaMethod
    }
}
