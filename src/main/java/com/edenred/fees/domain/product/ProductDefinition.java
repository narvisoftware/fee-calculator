package com.edenred.fees.domain.product;

import com.edenred.fees.domain.transaction.Transaction;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Mihai
 */
public abstract class ProductDefinition {
	
	private String id = UUID.randomUUID().toString();
	
	@Getter @NonNull
	private String name;
	
	@Getter @NonNull
	private ProductType type;

	public ProductDefinition(String name, ProductType type) {
		this.name = name;
		this.type = type;
	}
	
	public abstract BigDecimal getFee(Transaction transaction);
	
}
