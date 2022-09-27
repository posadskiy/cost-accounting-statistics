package com.posadskiy.costaccounting.statistics.api.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MonthStatistics {
	private Integer year;
	private String month;
	private Map<String, StatisticCategory> purchaseCategories;
	private Map<String, StatisticCategory> incomeCategories;
}
