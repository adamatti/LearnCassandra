package adamatti.core.cassandra

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.HostDistance
import com.datastax.driver.core.PoolingOptions
import com.datastax.driver.core.policies.RetryPolicy
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.FactoryBean

@Slf4j
class ClusterFactoryBean implements FactoryBean<Cluster> {
    String contactPoint
    //int connectionsPerHost = 1
    //long reconnectionPolicy

    @Override
    Cluster getObject() throws Exception {
        log.info("Building cluster")

        def cluster = Cluster.builder().addContactPoint(contactPoint)
            //.withPoolingOptions(new PoolingOptions().setCoreConnectionsPerHost(HostDistance.LOCAL, connectionsPerHost))
            //.poolingOptions().setCoreConnectionsPerHost(LOCAL, connectionsPerHost).withRetryPolicy(INSTANCE)
            //.withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(reconnectionPolicy))
            .build()

        log.info "Cluster created"

        createKeyspace(cluster)

        cluster
    }

    // TODO this shouldn't be here, but this is just a POC
    private void createKeyspace(Cluster cluster){
        log.info "Connecting to system"
        def session = cluster.connect()
        log.info "Creating mykeyspace"
        def sql = new File("src/main/resources/cassandra/mykeyspace.txt").getText()
        session.execute(sql)
        log.info "mykeyspace created"
        session.close()
    }

    @Override
    Class<?> getObjectType() {
        Cluster
    }

    @Override
    boolean isSingleton() {
        true
    }
}
