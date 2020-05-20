package com.edenred.fees.domain.transaction;

import com.edenred.fees.domain.MonetaryValue;
import com.edenred.fees.domain.product.ProductDefinition;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Mihai
 */
@Builder
public class Transaction {

	@Getter
	@NonNull
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@Getter
	@NonNull
	private TransactionType type;

	@Getter
	@NonNull
	private MonetaryValue value;

	@Getter
	@NonNull
	private LocalDateTime time;

	@Getter
	private boolean isForeignExchange;

	@Getter
	@NonNull
	private ProductDefinition productDefinition;

	public BigDecimal getFee() {
		return productDefinition.getFee(this);
	}
	
	public static class TransactionBuilder {
		public TransactionBuilder value(double val) {
			this.value = MonetaryValue.emptyGBP().add(val);
			return this;
		}
	}

}
