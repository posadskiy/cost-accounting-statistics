package com.posadskiy.costaccounting.statistics.core.db;

import com.posadskiy.costaccounting.statistics.core.db.model.DbUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<DbUser, String> {
	DbUser findByChatId(Long chatId);
	List<DbUser> findAllByProjectId(String projectId);
}
