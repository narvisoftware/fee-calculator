package com.edenred.fees.persistence;

import com.edenred.fees.domain.transaction.Transaction;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mihai
 */
@Repository
public class TransactionsDAO {
	
	private final Map<String, Transaction> transactions = new HashMap<>();
	
	public Transaction getTransactionById(String id) {
		return transactions.get(id);
	}
	
	public void saveTransaction(Transaction transaction) {
		transactions.put(transaction.getId(), transaction);
	}
	
}
