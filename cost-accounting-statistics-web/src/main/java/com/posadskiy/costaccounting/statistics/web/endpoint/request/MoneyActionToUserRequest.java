package com.posadskiy.costaccounting.statistics.web.endpoint.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.MoneyAction;
import com.posadskiy.costaccounting.statistics.api.dto.User;
import lombok.Data;

@Data
public class MoneyActionToUserRequest {
    private User foundUser;
    private MoneyAction moneyAction;
}
