package org.heyframework.provider;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = "org.heyframework", useDefaultFilters = false, includeFilters = {
		@Filter(value = org.springframework.stereotype.Service.class) })
public class MVCConfiguration {

}
