package com.posadskiy.costaccounting.statistics.core.db.model;

import lombok.Data;

@Data
public class DbCategoryCurrentLimit {
	private DbCategory category;
	private Double limit;
}
