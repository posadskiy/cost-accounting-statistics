package com.posadskiy.costaccounting.statistics.web.endpoint;

import com.posadskiy.costaccounting.statistics.core.controller.CategoryStatisticsController;
import com.posadskiy.costaccounting.statistics.web.endpoint.request.MoneyActionToStatisticsRequest;
import com.posadskiy.costaccounting.statistics.web.endpoint.request.MoneyActionToUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category-statistics")
public class CategoryStatisticEndpoint {
    private final CategoryStatisticsController categoryStatisticsController;

    @Autowired
    public CategoryStatisticEndpoint(CategoryStatisticsController categoryStatisticsController) {
        this.categoryStatisticsController = categoryStatisticsController;
    }

    @PostMapping("decreaseMoneyActionToStatisticCategory")
    public void decreaseMoneyActionToStatisticCategory(@RequestBody final MoneyActionToStatisticsRequest statisticsByMonthRequest) {
        categoryStatisticsController.decreaseMoneyActionToStatisticCategory(statisticsByMonthRequest.getDbStatisticCategory(), statisticsByMonthRequest.getMoneyAction());
    }

    @PostMapping("increasePurchaseToStatisticCategory")
    public void increasePurchaseToStatisticCategory(@RequestBody final MoneyActionToUserRequest moneyActionToUserRequest) {
        categoryStatisticsController.increasePurchaseToStatisticCategory(moneyActionToUserRequest.getFoundUser(), moneyActionToUserRequest.getMoneyAction());
    }

    @PostMapping("increaseIncomeToStatisticCategory")
    public void increaseIncomeToStatisticCategory(@RequestBody final MoneyActionToUserRequest moneyActionToUserRequest) {
        categoryStatisticsController.increaseIncomeToStatisticCategory(moneyActionToUserRequest.getFoundUser(), moneyActionToUserRequest.getMoneyAction());
    }

    @PostMapping("addMoneyActionToStatisticCategory")
    public void addMoneyActionToStatisticCategory(@RequestBody final MoneyActionToStatisticsRequest moneyActionToStatisticsRequest) {
        categoryStatisticsController.addMoneyActionToStatisticCategory(moneyActionToStatisticsRequest.getDbStatisticCategory(), moneyActionToStatisticsRequest.getMoneyAction());
    }

}
