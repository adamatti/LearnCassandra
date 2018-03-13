package spring

import adamatti.core.helpers.ConfigHelper

def cfg = ConfigHelper.cfg

beans {
    xmlns context: "http://www.springframework.org/schema/context"
    xmlns cassandra: "http://www.springframework.org/schema/data/cassandra"

    cassandra.cluster(port="9042")

    cassandra.session("keyspace-name":"system")

    cassandra.mapping("entity-base-packages":"adamatti.app")

    cassandra.converter()

    cassandra.template()
}
