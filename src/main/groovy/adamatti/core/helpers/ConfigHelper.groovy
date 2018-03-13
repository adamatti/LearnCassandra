package adamatti.core.helpers

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.stereotype.Component

@Component
class ConfigHelper extends PropertyPlaceholderConfigurer {
    private static ConfigObject cfg

    static {
        def fileName = "config.groovy"
        def config = this.class.getResource(fileName)
        if (!config){
            //TODO change
            config = new File("src/main/resources/${fileName}")
        }
        cfg = new ConfigSlurper().parse(config.text)
    }

    //@Override
    protected void loadProperties(Properties props) throws IOException {
        props.putAll(cfg.toProperties())
    }
}
