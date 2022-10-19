package com.posadskiy.costaccounting.statistics.core.controller.impl;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import com.posadskiy.costaccounting.moneyactions.api.dto.MoneyAction;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.costaccounting.statistics.api.dto.CategoryCurrentLimit;
import com.posadskiy.costaccounting.statistics.api.dto.MonthStatistics;
import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import com.posadskiy.costaccounting.statistics.api.dto.User;
import com.posadskiy.costaccounting.statistics.core.controller.CategoryStatisticsController;
import com.posadskiy.costaccounting.statistics.core.db.model.DbCategory;
import com.posadskiy.costaccounting.statistics.core.db.model.DbCategoryCurrentLimit;
import com.posadskiy.costaccounting.statistics.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.statistics.core.db.model.DbStatisticCategory;
import com.posadskiy.costaccounting.statistics.core.exception.MonthStatisticDoesNotExistException;
import com.posadskiy.costaccounting.statistics.core.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CategoryStatisticsControllerImpl implements CategoryStatisticsController {

    @Override
    public void decreaseMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction) {
        dbStatisticCategory.setAmount(dbStatisticCategory.getAmount() - moneyAction.getAmount());
        dbStatisticCategory.setEventCount(dbStatisticCategory.getEventCount() - 1);
        if (Objects.equals(dbStatisticCategory.getMaxAmount(), moneyAction.getAmount())) {
            dbStatisticCategory.setMaxAmount(dbStatisticCategory.getEventCount() != 0 ? dbStatisticCategory.getAmount() / dbStatisticCategory.getEventCount() : 0.0);
        }
    }

    @Override
    public void increasePurchaseToStatisticCategory(@NotNull final User foundUser, @NotNull final MoneyAction dbPurchase) {
        /*final String monthAndYear = Utils.getMonthAndYear(dbPurchase.getDate());
        final DbMonthStatistic statisticMonth = getDbMonthStatisticByUser(foundUser, monthAndYear);

        final DbStatisticCategory dbStatisticCategory = statisticMonth
            .getPurchaseCategories()
            .get(dbPurchase.getCategory().getId());

        if (dbStatisticCategory == null) {
            log.error("Add Purchase to Statistic: Category not found " + dbPurchase.getCategory());
            return;
        }

        addMoneyActionToStatisticCategory(dbStatisticCategory, dbPurchase);*/
    }

    @Override
    public void increaseIncomeToStatisticCategory(@NotNull final User foundUser, @NotNull final MoneyAction dbIncome) {
        /*final String monthAndYear = Utils.getMonthAndYear(dbIncome.getDate());
        final DbMonthStatistic statisticMonth = getDbMonthStatisticByUser(foundUser, monthAndYear);

        final DbStatisticCategory dbStatisticCategory = statisticMonth
            .getIncomeCategories()
            .get(dbIncome.getCategory().getId());

        if (dbStatisticCategory == null) {
            log.error("Add Income to Statistic: Category not found " + dbIncome.getCategory());
            return;
        }

        addMoneyActionToStatisticCategory(dbStatisticCategory, dbIncome);*/
    }

    @Override
    public void addMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction) {
        dbStatisticCategory.setAmount(dbStatisticCategory.getAmount() + moneyAction.getAmount());
        dbStatisticCategory.setEventCount(dbStatisticCategory.getEventCount() + 1);
        if (dbStatisticCategory.getMaxAmount() < moneyAction.getAmount()) {
            dbStatisticCategory.setMaxAmount(moneyAction.getAmount());
        }
    }

    @Override
    public @NotNull DbMonthStatistic getStatisticByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month) {
        DbMonthStatistic monthStatistic = statistics.get(month);
        if (monthStatistic == null) throw new MonthStatisticDoesNotExistException();

        return monthStatistic;
    }

    @Override
    public @NotNull DbStatisticCategory getPurchaseStatisticTotalByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month) {
        DbMonthStatistic monthStatistic = statistics.get(month);
        if (monthStatistic == null) throw new MonthStatisticDoesNotExistException();

        return getStatisticTotal(monthStatistic.getPurchaseCategories());
    }

    public @NotNull DbStatisticCategory getIncomeStatisticTotalByMonth(@NotNull Map<String, DbMonthStatistic> statistics, @NotNull String month) {
        DbMonthStatistic monthStatistic = statistics.get(month);
        if (monthStatistic == null) throw new MonthStatisticDoesNotExistException();

        return getStatisticTotal(monthStatistic.getIncomeCategories());
    }

    @Override
    public @NotNull DbMonthStatistic createDefaultMonthStatistic(@NotNull String monthAndYear, @NotNull List<Category> purchaseCategories, @NotNull List<Category> incomeCategories, @NotNull Map<String, CategoryCurrentLimit> limits) {
        /*DbMonthStatistic statisticMonth = new DbMonthStatistic();
        //TODO: shit below should be refactored
        final String month = monthAndYear.split(" ")[0];
        final Integer year = Integer.valueOf(monthAndYear.split(" ")[1]);
        statisticMonth.setMonth(month);
        statisticMonth.setYear(year);

        final Map<String, DbStatisticCategory> defaultMonthStatisticCategory = purchaseCategories
            .stream()
            .map(dbCategory -> createDefaultStatisticCategoryWithLimit(dbCategory, getCategoryLimit(limits, dbCategory.getId())))
            .collect(Collectors.toMap(dbStatisticCategory -> dbStatisticCategory.getCategory().getId(), dbStatisticCategory -> dbStatisticCategory));
        statisticMonth.setPurchaseCategories(defaultMonthStatisticCategory);

        final Map<String, DbStatisticCategory> incomesStatistic = incomeCategories
            .stream()
            .map(this::createDefaultStatisticCategory)
            .collect(Collectors.toMap(dbStatisticCategory -> dbStatisticCategory.getCategory().getId(), dbStatisticCategory -> dbStatisticCategory));
        statisticMonth.setIncomeCategories(incomesStatistic);
        return statisticMonth;*/
        return null;
    }

    private MonthStatistics getDbMonthStatisticByUser(@NotNull final User user, @NotNull final String monthAndYear) {
        /*final MonthStatistics statisticMonth = user.getStatistics().get(monthAndYear);
        if (statisticMonth != null) return statisticMonth;

        final Map<String, MonthStatistics> userStatistics = user.getStatistics();
        final MonthStatistics newMonthStatistic = createDefaultMonthStatistic(monthAndYear, user.getPurchaseCategories(), user.getIncomeCategories(), user.getLimits());
        userStatistics.put(monthAndYear, newMonthStatistic);

        return newMonthStatistic;*/
        return null;
    }

    private @NotNull DbStatisticCategory getStatisticTotal(@NotNull Map<String, DbStatisticCategory> categories) {
        double amountSum = categories.values().stream().mapToDouble(DbStatisticCategory::getAmount).sum();
        double limitSum = categories.values().stream().mapToDouble(DbStatisticCategory::getLimit).sum();
        int eventCountSum = categories.values().stream().mapToInt(DbStatisticCategory::getEventCount).sum();
        double maxAmountSum = categories.values().stream().mapToDouble(DbStatisticCategory::getMaxAmount).max().orElse(0.0);

        DbStatisticCategory statisticCategory = new DbStatisticCategory();
        statisticCategory.setAmount(amountSum);
        statisticCategory.setLimit(limitSum);
        statisticCategory.setEventCount(eventCountSum);
        statisticCategory.setMaxAmount(maxAmountSum);

        return statisticCategory;
    }

    //TODO: StatisticCategory fields should not be null
    private DbStatisticCategory createDefaultStatisticCategory(DbCategory category) {
        final DbStatisticCategory dbStatisticCategory = new DbStatisticCategory();
        dbStatisticCategory.setCategory(category);
        dbStatisticCategory.setAmount(0.0);
        dbStatisticCategory.setLimit(0.0);
        dbStatisticCategory.setMaxAmount(0.0);
        dbStatisticCategory.setEventCount(0);
        return dbStatisticCategory;
    }

    private DbStatisticCategory createDefaultStatisticCategoryWithLimit(DbCategory category, Double limit) {
        final DbStatisticCategory defaultStatisticCategory = createDefaultStatisticCategory(category);
        defaultStatisticCategory.setLimit(limit);
        return defaultStatisticCategory;
    }

    private Double getCategoryLimit(Map<String, DbCategoryCurrentLimit> limits, String categoryId) {
        return limits.get(categoryId).getLimit();
    }
}
