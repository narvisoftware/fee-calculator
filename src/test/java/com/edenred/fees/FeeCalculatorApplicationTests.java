package com.edenred.fees;

import com.edenred.fees.domain.product.ThomasTravelProduct;
import com.edenred.fees.domain.product.TiscoFinanceProduct;
import com.edenred.fees.domain.transaction.Transaction;
import com.edenred.fees.domain.transaction.TransactionType;
import com.edenred.fees.service.TransactionsService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeeCalculatorApplicationTests {

	@Autowired
	TransactionsService transactionsService;

	Transaction thomasTransaction_allFees = Transaction.builder()
			.productDefinition(ThomasTravelProduct.productBuilder().name("A Thomas Travel Product").build())
			.isForeignExchange(true)
			.time(LocalDateTime.now())
			.type(TransactionType.REDEMPTION)
			.value(100)
			.build();
	
	Transaction thomasTransaction_redemptionFee = Transaction.builder()
			.productDefinition(ThomasTravelProduct.productBuilder().name("A Thomas Travel Product").build())
			.isForeignExchange(false)
			.time(LocalDateTime.now())
			.type(TransactionType.REDEMPTION)
			.value(100)
			.build();
	
	Transaction thomasTransaction_foreignExchangeFee = Transaction.builder()
			.productDefinition(ThomasTravelProduct.productBuilder().name("A Thomas Travel Product").build())
			.isForeignExchange(true)
			.time(LocalDateTime.now())
			.type(TransactionType.TOP_UP)
			.value(100)
			.build();
	
	Transaction thomasTransaction_noFee = Transaction.builder()
			.productDefinition(ThomasTravelProduct.productBuilder().name("A Thomas Travel Product").build())
			.isForeignExchange(false)
			.time(LocalDateTime.now())
			.type(TransactionType.BALANCE_ENQUIRY)
			.value(100)
			.build();
	
	Transaction tiscoTransaction_withoutFee = Transaction.builder()
			.productDefinition(TiscoFinanceProduct.productBuilder().name("A Tisco Finance Product").build())
			.isForeignExchange(false)
			.time(LocalDateTime.parse("2011-12-03T14:00:00", DateTimeFormatter.ISO_DATE_TIME))
			.type(TransactionType.BALANCE_ENQUIRY)
			.value(100)
			.build();
	
	Transaction tiscoTransaction_withFee = Transaction.builder()
			.productDefinition(TiscoFinanceProduct.productBuilder().name("A Tisco Finance Product").build())
			.isForeignExchange(false)
			.time(LocalDateTime.parse("2011-12-03T04:00:00", DateTimeFormatter.ISO_DATE_TIME))
			.type(TransactionType.BALANCE_ENQUIRY)
			.value(100)
			.build();

	@BeforeEach
	public void setup() {
		transactionsService.saveTransaction(thomasTransaction_allFees);
		transactionsService.saveTransaction(thomasTransaction_redemptionFee);
		transactionsService.saveTransaction(thomasTransaction_foreignExchangeFee);
		transactionsService.saveTransaction(thomasTransaction_noFee);
		transactionsService.saveTransaction(tiscoTransaction_withoutFee);
		transactionsService.saveTransaction(tiscoTransaction_withFee);
	}

	@Test
	void testThomasTravelFees() {
		Assertions.assertEquals(2.5, transactionsService.getTransactionById(thomasTransaction_allFees.getId()).getFee().doubleValue());
		Assertions.assertEquals(1, transactionsService.getTransactionById(thomasTransaction_redemptionFee.getId()).getFee().doubleValue());
		Assertions.assertEquals(1.5, transactionsService.getTransactionById(thomasTransaction_foreignExchangeFee.getId()).getFee().doubleValue());
		Assertions.assertEquals(0, transactionsService.getTransactionById(thomasTransaction_noFee.getId()).getFee().doubleValue());
	}
	
	@Test
	void testTiscoFinanceFees() {
		Assertions.assertEquals(0, transactionsService.getTransactionById(tiscoTransaction_withoutFee.getId()).getFee().doubleValue());
		Assertions.assertEquals(5, transactionsService.getTransactionById(tiscoTransaction_withFee.getId()).getFee().doubleValue());
	}

}
