package com.posadskiy.costaccounting.statistics.core.controller.impl;

import com.posadskiy.costaccounting.statistics.core.controller.ProjectController;
import com.posadskiy.costaccounting.statistics.core.db.ProjectRepository;
import com.posadskiy.costaccounting.statistics.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.statistics.core.db.model.DbProject;
import com.posadskiy.costaccounting.statistics.core.exception.ProjectDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Component
public class ProjectControllerImpl implements ProjectController {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectControllerImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public DbProject save(@NotNull DbProject project) {
        return projectRepository.save(project);
    }

    @Override
    public @NotNull DbProject getProject(@NotNull String id) {
        Optional<DbProject> foundProject = projectRepository.findById(id);
        if (!foundProject.isPresent()) throw new ProjectDoesNotExistException();

        return foundProject.get();
    }

    @Override
    public List<String> getMonths(String id) {
        DbProject project = getProject(id);
        final Map<String, DbMonthStatistic> statistics = project.getStatistics();
        
        if (CollectionUtils.isEmpty(statistics)) return new ArrayList<>();

        final List<String> months = (List<String>) CollectionUtils.arrayToList(
            statistics.keySet().toArray()
        );
        Collections.reverse(months);
        return months;
    }

}

