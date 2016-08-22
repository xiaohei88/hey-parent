package org.heyframework.provider.properties;

import org.heyframework.config.HeyBootProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hey.druid")
public class DruidProperties extends HeyBootProperties {

}
