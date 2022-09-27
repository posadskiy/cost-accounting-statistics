package com.posadskiy.costaccounting.statistics.core.mapper;

import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import com.posadskiy.costaccounting.statistics.core.db.model.DbStatisticCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StatisticCategoryMapper {
	
	@Mapping(source = "category", target = "category")
	@Mapping(source = "limit", target = "limit")
	@Mapping(source = "amount", target = "amount")
	@Mapping(source = "eventCount", target = "eventCount")
	@Mapping(source = "maxAmount", target = "maxAmount")
    StatisticCategory mapToDto(final DbStatisticCategory category);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "limit", target = "limit")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "eventCount", target = "eventCount")
    @Mapping(source = "maxAmount", target = "maxAmount")
    DbStatisticCategory mapFromDto(final StatisticCategory category);
}
