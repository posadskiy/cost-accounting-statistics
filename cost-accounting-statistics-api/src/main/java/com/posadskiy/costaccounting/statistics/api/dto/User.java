package com.posadskiy.costaccounting.statistics.api.dto;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class User implements Serializable {

    @Id
    private String id;
    private Map<String, MonthStatistics> statistics;
    private Map<String, CategoryCurrentLimit> limits;
    private List<Category> purchaseCategories;
    private List<Category> incomeCategories;
}
