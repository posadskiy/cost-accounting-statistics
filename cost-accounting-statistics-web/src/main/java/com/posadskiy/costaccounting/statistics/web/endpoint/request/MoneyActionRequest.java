package com.posadskiy.costaccounting.statistics.web.endpoint.request;

import lombok.Data;

@Data
public class MoneyActionRequest {
	private String userId;
	private Integer year;
	private Integer month;
}
