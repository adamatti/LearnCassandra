package adamatti.core.helpers

import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.support.GenericGroovyApplicationContext

abstract class SpringHelper {
    static ConfigurableApplicationContext buildContext(String fileName = "main"){
        def context = new GenericGroovyApplicationContext("classpath:/spring/${fileName}.groovy")
        context.registerShutdownHook()
        context
    }
}
