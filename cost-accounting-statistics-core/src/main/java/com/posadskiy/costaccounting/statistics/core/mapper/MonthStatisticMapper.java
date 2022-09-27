package com.posadskiy.costaccounting.statistics.core.mapper;

import com.posadskiy.costaccounting.statistics.api.dto.MonthStatistics;
import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import com.posadskiy.costaccounting.statistics.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.statistics.core.db.model.DbStatisticCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MonthStatisticMapper {
	
	@Mapping(source = "year", target = "year")
	@Mapping(source = "month", target = "month")
	@Mapping(source = "purchaseCategories", target = "purchaseCategories")
	@Mapping(source = "incomeCategories", target = "incomeCategories")
    MonthStatistics mapToDto(final DbMonthStatistic category);

    @Mapping(source = "year", target = "year")
    @Mapping(source = "month", target = "month")
    @Mapping(source = "purchaseCategories", target = "purchaseCategories")
    @Mapping(source = "incomeCategories", target = "incomeCategories")
    DbMonthStatistic mapFromDto(final MonthStatistics category);
}
