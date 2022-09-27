package com.posadskiy.costaccounting.statistics.core.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "user")
public class DbUser implements Serializable {

	@Id
	private String id;
	private Long chatId;
	private String projectId;
	private List<DbCategory> purchaseCategories;
	private List<DbCategory> incomeCategories;
	private List<String> roles;
	private Map<String, DbCategoryCurrentLimit> limits;
	private Map<String, DbMonthStatistic> statistics;
}
