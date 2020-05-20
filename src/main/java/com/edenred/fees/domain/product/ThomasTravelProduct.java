package com.edenred.fees.domain.product;

import com.edenred.fees.domain.MonetaryValue;
import com.edenred.fees.domain.transaction.Transaction;
import com.edenred.fees.domain.transaction.TransactionType;
import java.math.BigDecimal;
import lombok.Builder;

/**
 *
 * @author Mihai
 */

public class ThomasTravelProduct extends ProductDefinition {

	private static final double FIXED_REDEMPTION_FEE = 1;
	private static final double EXCHANGE_PERCENTAGE_FEE = 1.5;
	
	@Builder(builderMethodName = "productBuilder")
	public ThomasTravelProduct(String name) {
		super(name, ProductType.THOMAS_TRAVEL);
	}
	
	@Override
	public BigDecimal getFee(Transaction transaction) {
		MonetaryValue fee = MonetaryValue.emptyGBP();
		if(transaction.getType() == TransactionType.REDEMPTION) {
			fee = fee.add(FIXED_REDEMPTION_FEE);
		}
		if(transaction.isForeignExchange()) {
			fee = fee.add(transaction.getValue().percent(EXCHANGE_PERCENTAGE_FEE));
		}
		return fee.getAmount();
	}
	
}
