package com.posadskiy.costaccounting.statistics.core.controller.impl;

import com.posadskiy.costaccounting.statistics.core.controller.UserController;
import com.posadskiy.costaccounting.statistics.core.db.UserRepository;
import com.posadskiy.costaccounting.statistics.core.db.model.DbUser;
import com.posadskiy.costaccounting.statistics.core.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserControllerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DbUser getById(String userId) {
        Optional<DbUser> foundUser = userRepository.findById(userId);
        if (!foundUser.isPresent()) throw new UserDoesNotExistException();

        return foundUser.get();
    }

    @Override
    public DbUser getByChatId(Long chatId) {
        DbUser foundUser = userRepository.findByChatId(chatId);
        if (foundUser == null) throw new UserDoesNotExistException();

        return foundUser;
    }
}
