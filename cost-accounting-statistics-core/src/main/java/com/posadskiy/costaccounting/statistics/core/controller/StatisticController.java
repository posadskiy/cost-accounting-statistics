package com.posadskiy.costaccounting.statistics.core.controller;

import com.posadskiy.costaccounting.statistics.api.dto.MonthStatistics;
import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface StatisticController {
    MonthStatistics statisticByMonth(@NotNull final String userId, int year, int month);
	StatisticCategory statisticPurchaseTotalByMonth(@NotNull final String userId, int year, int month);
	StatisticCategory statisticIncomeTotalByMonth(@NotNull final String userId, int year, int month);
	MonthStatistics statisticForUserByMonth(@NotNull final String userId, int year, int month);
	StatisticCategory statisticPurchaseTotalForUserByMonth(@NotNull final String userId, int year, int month);
	StatisticCategory statisticIncomeTotalForUserByMonth(@NotNull final String userId, int year, int month);
	List<String> getStatisticMonthsForProject(@NotNull final String userId);
}
