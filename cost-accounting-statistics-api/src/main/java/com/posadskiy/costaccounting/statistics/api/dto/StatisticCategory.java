package com.posadskiy.costaccounting.statistics.api.dto;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import lombok.Data;

@Data
public class StatisticCategory {
	private Category category; 
	private Double limit;
	private Double amount;
	private Integer eventCount;
	private Double maxAmount;
}
