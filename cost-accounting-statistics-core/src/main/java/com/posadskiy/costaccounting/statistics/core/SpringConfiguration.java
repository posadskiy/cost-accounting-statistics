package com.posadskiy.costaccounting.statistics.core;

import com.posadskiy.costaccounting.statistics.core.mapper.MonthStatisticMapper;
import com.posadskiy.costaccounting.statistics.core.mapper.MonthStatisticMapperImpl;
import com.posadskiy.costaccounting.statistics.core.mapper.StatisticCategoryMapper;
import com.posadskiy.costaccounting.statistics.core.mapper.StatisticCategoryMapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
@ComponentScan
public class SpringConfiguration {
    @Value("${currencyConverterApi.apiKey}")
    private String currencyConverterApiApiKey;

    @Value("${currencyLayer.apiKey}")
    private String currencyLayerApiKey;

    @Value("${openExchangeRates.apiKey}")
    private String openExchangeRatesApiKey;

	@Bean
	public MonthStatisticMapper categoryMapper() {
		return new MonthStatisticMapperImpl();
	}

    @Bean
	public StatisticCategoryMapper incomeMapper() {
		return new StatisticCategoryMapperImpl();
	}

}
