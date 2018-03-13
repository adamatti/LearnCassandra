package spring

import com.datastax.driver.core.Cluster
import org.springframework.data.cassandra.config.SchemaAction

beans {
    xmlns context: "http://www.springframework.org/schema/context"
    //xmlns camel: "http://camel.apache.org/schema/spring"

    context.'annotation-config'()
    context.'component-scan'('base-package':'adamatti')

    importBeans('classpath:spring/cassandra.groovy')
}
