package com.posadskiy.costaccounting.statistics.core.controller;

import dev.posadskiy.costaccounting.db.model.DbMonthStatistic;
import dev.posadskiy.costaccounting.db.model.DbStatisticCategory;
import dev.posadskiy.costaccounting.db.model.MoneyAction;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface StatisticController {
	Map<Integer, List<MoneyAction>> monthEvents(String userId, int year, int month);
	DbMonthStatistic statisticByMonth(@NotNull final String userId, int year, int month);
	DbStatisticCategory statisticPurchaseTotalByMonth(@NotNull final String userId, int year, int month);
	DbStatisticCategory statisticIncomeTotalByMonth(@NotNull final String userId, int year, int month);
	DbMonthStatistic statisticForUserByMonth(@NotNull final String userId, int year, int month);
	DbStatisticCategory statisticPurchaseTotalForUserByMonth(@NotNull final String userId, int year, int month);
	DbStatisticCategory statisticIncomeTotalForUserByMonth(@NotNull final String userId, int year, int month);
	List<String> getStatisticMonthsForProject(@NotNull final String userId);
}
