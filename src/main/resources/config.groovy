port = System.env.PORT ?: "3000"

cassandra = [
    contactPoint: 'localhost',
    keyspaceName: 'mykeyspace'
    //keyspaceName: 'system'
]
