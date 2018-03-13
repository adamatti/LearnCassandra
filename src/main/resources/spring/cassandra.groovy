package spring

import adamatti.core.helpers.ConfigHelper
import org.springframework.data.cassandra.config.CassandraEntityClassScanner
import org.springframework.data.cassandra.config.SchemaAction

def cfg = ConfigHelper.cfg

beans {
    xmlns context: "http://www.springframework.org/schema/context"
/*
    xmlns cql: "http://www.springframework.org/schema/data/cql"

    cql.cluster("contact-points": cfg.cassandra.contactPoint){
        cql.keyspace (action: "CREATE_DROP", name:cfg.cassandra.keyspaceName)
    }
*/

    cluster(adamatti.core.cassandra.ClusterFactoryBean) {
        contactPoint = cfg.cassandra.contactPoint
    }

    simpleUserTypeResolver(org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver, ref('cluster'),cfg.cassandra.keyspaceName)

    mappingContext(org.springframework.data.cassandra.core.mapping.CassandraMappingContext){
        userTypeResolver = ref('simpleUserTypeResolver')
        initialEntitySet = CassandraEntityClassScanner.scan("adamatti.app")
    }

    converter(org.springframework.data.cassandra.core.convert.MappingCassandraConverter,ref('mappingContext'))

    session(org.springframework.data.cassandra.config.CassandraSessionFactoryBean){
        cluster = ref('cluster')
        keyspaceName = cfg.cassandra.keyspaceName
        converter = ref('converter')
        schemaAction = SchemaAction.CREATE_IF_NOT_EXISTS
/*
        startupScripts = [
            new File("src/main/resources/cassandra/mykeyspace.txt").getText()
        ]
*/
    }

/*
    session(cluster:'connect','system')
*/
    cassandraTemplate(org.springframework.data.cassandra.core.CassandraTemplate, ref('session'))
}
