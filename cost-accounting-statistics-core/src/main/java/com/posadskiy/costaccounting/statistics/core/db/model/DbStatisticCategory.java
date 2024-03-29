package com.posadskiy.costaccounting.statistics.core.db.model;

import lombok.Data;

@Data
public class DbStatisticCategory {
	private DbCategory category;
	private Double limit;
	private Double amount;
	private Integer eventCount;
	private Double maxAmount;
}
