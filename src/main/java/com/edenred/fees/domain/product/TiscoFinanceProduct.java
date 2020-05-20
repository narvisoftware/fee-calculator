package com.edenred.fees.domain.product;

import com.edenred.fees.domain.MonetaryValue;
import com.edenred.fees.domain.TimeInterval;
import com.edenred.fees.domain.transaction.Transaction;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

/**
 *
 * @author Mihai
 */
public class TiscoFinanceProduct extends ProductDefinition {

	private static final double LATE_HOURS_FEE = 5;
	
	private static final TimeInterval FEE_FREE_TIME_INTERVAL = TimeInterval.between("06:00", "22:00");
	
	@Builder(builderMethodName = "productBuilder")
	public TiscoFinanceProduct(String name) {
		super(name, ProductType.TISCO_FINANCE);
	}
	
	@Override
	public BigDecimal getFee(Transaction transaction) {
		MonetaryValue fee = MonetaryValue.emptyGBP();
		if(!FEE_FREE_TIME_INTERVAL.isInsideInterval(transaction.getTime().toLocalTime())) {
			fee = fee.add(transaction.getValue().percent(LATE_HOURS_FEE));
		}
		return fee.getAmount();
	}
	
}
