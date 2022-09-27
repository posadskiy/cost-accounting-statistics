package com.posadskiy.costaccounting.statistics.core.controller.impl;

import com.posadskiy.costaccounting.statistics.api.dto.MonthStatistics;
import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import com.posadskiy.costaccounting.statistics.core.controller.CategoryStatisticsController;
import com.posadskiy.costaccounting.statistics.core.controller.ProjectController;
import com.posadskiy.costaccounting.statistics.core.controller.StatisticController;
import com.posadskiy.costaccounting.statistics.core.controller.UserController;
import com.posadskiy.costaccounting.statistics.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.statistics.core.db.model.DbUser;
import com.posadskiy.costaccounting.statistics.core.mapper.MonthStatisticMapper;
import com.posadskiy.costaccounting.statistics.core.mapper.StatisticCategoryMapper;
import com.posadskiy.costaccounting.statistics.core.util.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class StatisticControllerImpl implements StatisticController {

    private final UserController userController;
    private final ProjectController projectController;
    private final CategoryStatisticsController categoryStatisticsController;
    private final StatisticCategoryMapper statisticCategoryMapper;
    private final MonthStatisticMapper monthStatisticMapper;

    @Autowired
    public StatisticControllerImpl(UserController userController, ProjectController projectController, CategoryStatisticsController categoryStatisticsController, StatisticCategoryMapper statisticCategoryMapper, MonthStatisticMapper monthStatisticMapper) {
        this.userController = userController;
        this.projectController = projectController;
        this.categoryStatisticsController = categoryStatisticsController;
        this.statisticCategoryMapper = statisticCategoryMapper;
        this.monthStatisticMapper = monthStatisticMapper;
    }

    public MonthStatistics statisticByMonth(@NotNull final String projectId, int year, int month) {

        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = projectController.getProject(projectId).getStatistics();
        return monthStatisticMapper.mapToDto(
            categoryStatisticsController.getStatisticByMonth(statistics, monthAndYear)
        );
    }

    public StatisticCategory statisticPurchaseTotalByMonth(@NotNull final String projectId, int year, int month) {
        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = projectController.getProject(projectId).getStatistics();
        return statisticCategoryMapper.mapToDto(
            categoryStatisticsController.getPurchaseStatisticTotalByMonth(statistics, monthAndYear)
        );
    }

    public StatisticCategory statisticIncomeTotalByMonth(@NotNull final String projectId, int year, int month) {
        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = projectController.getProject(projectId).getStatistics();
        return statisticCategoryMapper.mapToDto(
            categoryStatisticsController.getIncomeStatisticTotalByMonth(statistics, monthAndYear)
        );
    }

    @Override
    public MonthStatistics statisticForUserByMonth(@NotNull String userId, int year, int month) {
        final DbUser foundUser = userController.getById(userId);
        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
        return monthStatisticMapper.mapToDto(
            categoryStatisticsController.getStatisticByMonth(statistics, monthAndYear)
        );
    }

    @Override
    public StatisticCategory statisticPurchaseTotalForUserByMonth(@NotNull String userId, int year, int month) {
        final DbUser foundUser = userController.getById(userId);
        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
        return statisticCategoryMapper.mapToDto(
            categoryStatisticsController.getPurchaseStatisticTotalByMonth(statistics, monthAndYear)
        );
    }

    @Override
    public StatisticCategory statisticIncomeTotalForUserByMonth(@NotNull String userId, int year, int month) {
        final DbUser foundUser = userController.getById(userId);
        final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
        final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
        return statisticCategoryMapper.mapToDto(
            categoryStatisticsController.getIncomeStatisticTotalByMonth(statistics, monthAndYear)
        );
    }

    @Override
    public List<String> getStatisticMonthsForProject(@NotNull String userId) {
        final DbUser foundUser = userController.getById(userId);
        return projectController.getMonths(foundUser.getProjectId());
    }
}
