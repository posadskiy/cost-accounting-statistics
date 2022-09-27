package com.posadskiy.costaccounting.statistics.core.controller;


import com.posadskiy.costaccounting.statistics.core.db.model.DbUser;

public interface UserController {
    DbUser getById(String userId);

    DbUser getByChatId(Long chatId);
}
