package com.posadskiy.costaccounting.statistics.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.*;
import com.posadskiy.costaccounting.statistics.api.dto.CategoryCurrentLimit;
import com.posadskiy.costaccounting.statistics.api.dto.User;
import com.posadskiy.costaccounting.statistics.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.statistics.core.db.model.DbStatisticCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface CategoryStatisticsController {
	void decreaseMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction);
	void increasePurchaseToStatisticCategory(@NotNull final User foundUser, @NotNull final MoneyAction dbPurchase);
	void increaseIncomeToStatisticCategory(@NotNull final User foundUser, @NotNull final MoneyAction dbIncome);
	void addMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction);
	@NotNull DbMonthStatistic getStatisticByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month);
	@NotNull DbStatisticCategory getPurchaseStatisticTotalByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month);
	@NotNull DbStatisticCategory getIncomeStatisticTotalByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month);
	@NotNull DbMonthStatistic createDefaultMonthStatistic(@NotNull String monthAndYear, @NotNull List<Category> purchaseCategories, @NotNull List<Category> incomeCategories, @NotNull Map<String, CategoryCurrentLimit> limits);
}
