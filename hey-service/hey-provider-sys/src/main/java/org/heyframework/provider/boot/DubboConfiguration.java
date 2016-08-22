package org.heyframework.provider.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/dubbo-provider.xml")
public class DubboConfiguration {

}
