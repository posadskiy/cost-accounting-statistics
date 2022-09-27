package com.posadskiy.costaccounting.statistics.core.controller;


import com.posadskiy.costaccounting.statistics.core.db.model.DbProject;

import java.util.List;

public interface ProjectController {
    DbProject save(DbProject project);

    DbProject getProject(String id);

    List<String> getMonths(String id);
}
