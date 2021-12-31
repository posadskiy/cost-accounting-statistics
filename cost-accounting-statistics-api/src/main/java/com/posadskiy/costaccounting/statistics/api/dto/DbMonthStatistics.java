package com.posadskiy.costaccounting.statistics.api.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DbMonthStatistics {
	private Integer year;
	private String month;
	private Map<String, DbStatisticCategory> purchaseCategories;
	private Map<String, DbStatisticCategory> incomeCategories;
}
