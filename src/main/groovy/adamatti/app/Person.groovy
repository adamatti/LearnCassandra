package adamatti.app

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table
class Person {
    //@Id
    @PrimaryKey
    String id

    String firstName
}
