package com.posadskiy.costaccounting.statistics.core.controller.impl;

import com.posadskiy.costaccounting.statistics.core.controller.StatisticController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StatisticControllerImpl implements StatisticController {

	private final UserController userController;
	private final ProjectController projectController;
	private final CategoryStatisticsController categoryStatisticsController;

	@Autowired
	public StatisticControllerImpl(UserController userController, ProjectController projectController, CategoryStatisticsController categoryStatisticsController) {
		this.userController = userController;
		this.projectController = projectController;
		this.categoryStatisticsController = categoryStatisticsController;
	}

	@Override
	public Map<Integer, List<MoneyAction>> monthEvents(@NotNull String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);

		final LocalDateTime monthStart = LocalDateTime.of(year, month, 1, 0, 0);
		final LocalDateTime monthFinish = monthStart.plusMonths(1);
		final List<MoneyAction> monthPurchases = foundUser.getPurchases().stream().filter(dbPurchase -> {
			final LocalDateTime date = Utils.convertToLocalDateTimeViaInstant(dbPurchase.getDate());
			return date.isAfter(monthStart) && date.isBefore(monthFinish);
		}).collect(Collectors.toList());

		final List<MoneyAction> monthIncomes = foundUser.getIncomes().stream().filter(dbIncome -> {
			final LocalDateTime date = Utils.convertToLocalDateTimeViaInstant(dbIncome.getDate());
			return date.isAfter(monthStart) && date.isBefore(monthFinish);
		}).collect(Collectors.toList());

		final Map<Integer, List<MoneyAction>> mapByDays = Stream.of(monthPurchases, monthIncomes)
			.flatMap(Collection::stream)
			.collect(Collectors.groupingBy(event -> Utils.convertDateToDays(event.getDate())));
		
		mapByDays.forEach((integer, moneyActions) -> moneyActions.sort((o1, o2) -> o1.getDate().before(o2.getDate()) ? 1 : -1));
		
		return mapByDays;
	}
	
	public DbMonthStatistic statisticByMonth(@NotNull final String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = projectController.getProject(foundUser.getProjectId()).getStatistics();
		return categoryStatisticsController.getStatisticByMonth(statistics, monthAndYear);
	}

	public DbStatisticCategory statisticPurchaseTotalByMonth(@NotNull final String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = projectController.getProject(foundUser.getProjectId()).getStatistics();
		return categoryStatisticsController.getPurchaseStatisticTotalByMonth(statistics, monthAndYear);
	}

	public DbStatisticCategory statisticIncomeTotalByMonth(@NotNull final String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = projectController.getProject(foundUser.getProjectId()).getStatistics();
		return categoryStatisticsController.getIncomeStatisticTotalByMonth(statistics, monthAndYear);
	}

	@Override
	public DbMonthStatistic statisticForUserByMonth(@NotNull String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
		return categoryStatisticsController.getStatisticByMonth(statistics, monthAndYear);
	}

	@Override
	public DbStatisticCategory statisticPurchaseTotalForUserByMonth(@NotNull String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
		return categoryStatisticsController.getPurchaseStatisticTotalByMonth(statistics, monthAndYear);
	}

	@Override
	public DbStatisticCategory statisticIncomeTotalForUserByMonth(@NotNull String userId, int year, int month) {
		final DbUser foundUser = userController.getById(userId);
		final String monthAndYear = Utils.getMonthAndYear(LocalDate.of(year, month, 1));
		final Map<String, DbMonthStatistic> statistics = foundUser.getStatistics();
		return categoryStatisticsController.getIncomeStatisticTotalByMonth(statistics, monthAndYear);
	}

	@Override
	public List<String> getStatisticMonthsForProject(@NotNull String userId) {
		final DbUser foundUser = userController.getById(userId);
		return projectController.getMonths(foundUser.getProjectId());
	}
}
