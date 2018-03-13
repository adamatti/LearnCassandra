package adamatti.app

import adamatti.core.helpers.SpringHelper
import com.datastax.driver.core.Cluster
import groovy.util.logging.Slf4j
import org.springframework.context.ApplicationContext
import org.springframework.data.cassandra.core.CassandraTemplate

@Slf4j
class Main {
    static void main(String [] args){
        log.info "Starting"
        def context = SpringHelper.buildContext()
        log.info "Started"

        //listBeans(context)
        testCrud(context)

        context.close()
        log.info "Closed"
        System.exit(0)
    }

    private static void testCrud(ApplicationContext context){
        def cassandraTemplate = context.getBean(CassandraTemplate.class)

        log.info "Count: " + cassandraTemplate.count(Person.class)

        // INSERT
        log.info "Inserting"
        def id = UUID.randomUUID().toString()
        def person = new Person(id: id, firstName: "Marcelo")
        cassandraTemplate.insert(person)

        // UPDATE
        log.info "Updating"
        person.firstName = "adamatti"
        cassandraTemplate.update(person)

        // FIND
        log.info "Finding"
        person = cassandraTemplate.selectOneById(id, Person.class)

        // DELETE
        log.info "Deleting"
        cassandraTemplate.delete(person)
    }

    private static void listBeans(ApplicationContext context){
        context.getBeansOfType(Object.class).each {k, v ->
            println k
        }
    }
}
