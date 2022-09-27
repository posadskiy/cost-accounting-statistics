package com.posadskiy.costaccounting.statistics.web;

import com.posadskiy.costaccounting.statistics.core.MongoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration("web-configuration")
@ComponentScan({"com.posadskiy.costaccounting.statistics.core.controller", "com.posadskiy.costaccounting.statistics.core.mapper"})
@Import({com.posadskiy.costaccounting.statistics.core.SpringConfiguration.class, MongoConfiguration.class})
public class SpringConfiguration {
}
