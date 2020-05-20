package com.edenred.fees.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author mihai
 */
@Builder
public class MonetaryValue implements Serializable {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	@Getter
	@NonNull
	private Currency currency;

	@NonNull
	private BigDecimal amount;

	public static MonetaryValue emptyGBP() {
		return new MonetaryValue(Currency.getInstance("GBP"), BigDecimal.ZERO);
	}

	public MonetaryValue add(double value) {
		return new MonetaryValue(currency,
				amount.add(new BigDecimal(value))
						.setScale(2, RoundingMode.HALF_UP));
	}
	
	public MonetaryValue add(MonetaryValue value) {
		return new MonetaryValue(currency,
				amount.add(value.amount)
						.setScale(2, RoundingMode.HALF_UP));
	}

	public MonetaryValue percent(double value) {
		return new MonetaryValue(currency,
				amount.multiply(new BigDecimal(value))
						.divide(ONE_HUNDRED)
						.setScale(2, RoundingMode.HALF_UP));
	}

	public BigDecimal getAmount() {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}

}
