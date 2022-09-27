package com.posadskiy.costaccounting.statistics.web.endpoint;

import com.posadskiy.costaccounting.statistics.api.dto.MonthStatistics;
import com.posadskiy.costaccounting.statistics.api.dto.StatisticCategory;
import com.posadskiy.costaccounting.statistics.core.controller.StatisticController;
import com.posadskiy.costaccounting.statistics.web.endpoint.request.MoneyActionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticEndpoint {

    private final StatisticController statisticController;

    @Autowired
    public StatisticEndpoint(StatisticController statisticController) {
        this.statisticController = statisticController;
    }

    @PostMapping("month")
    public MonthStatistics statisticByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("monthPurchaseTotal")
    public StatisticCategory statisticPurchaseTotalByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticPurchaseTotalByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("monthIncomeTotal")
    public StatisticCategory statisticIncomeTotalByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticIncomeTotalByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("monthForUser")
    public MonthStatistics statisticForUserByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticForUserByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("monthPurchaseTotalForUser")
    public StatisticCategory statisticPurchaseTotalForUserByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticPurchaseTotalForUserByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("monthIncomeTotalForUser")
    public StatisticCategory statisticIncomeTotalForUserByMonth(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.statisticIncomeTotalForUserByMonth(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }

    @PostMapping("projectMonthsList")
    public List<String> getStatisticMonthsForProject(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return statisticController.getStatisticMonthsForProject(moneyActionRequest.getUserId());
    }
}
