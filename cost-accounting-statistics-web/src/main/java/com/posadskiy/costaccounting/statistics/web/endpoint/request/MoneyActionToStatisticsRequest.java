package com.posadskiy.costaccounting.statistics.web.endpoint.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.MoneyAction;
import com.posadskiy.costaccounting.statistics.core.db.model.DbStatisticCategory;
import lombok.Data;

@Data
public class MoneyActionToStatisticsRequest {
    private DbStatisticCategory dbStatisticCategory;
    private MoneyAction moneyAction;
}
