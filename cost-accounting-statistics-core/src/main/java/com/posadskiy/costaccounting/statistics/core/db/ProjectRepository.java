package com.posadskiy.costaccounting.statistics.core.db;

import com.posadskiy.costaccounting.statistics.core.db.model.DbProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<DbProject, String> {
}
