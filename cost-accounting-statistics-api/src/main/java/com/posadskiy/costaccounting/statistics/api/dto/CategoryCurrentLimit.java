package com.posadskiy.costaccounting.statistics.api.dto;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import lombok.Data;

@Data
public class CategoryCurrentLimit {
	private Category category;
	private Double limit;
}
