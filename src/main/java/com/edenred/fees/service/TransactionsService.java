package com.edenred.fees.service;

import com.edenred.fees.domain.transaction.Transaction;
import com.edenred.fees.persistence.TransactionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mihai
 */
@Service
public class TransactionsService {
	
	@Autowired
	TransactionsDAO transactionsDAO;
	
	public Transaction getTransactionById(String id) {
		return transactionsDAO.getTransactionById(id);
	}
	
	public void saveTransaction(Transaction transaction) {
		transactionsDAO.saveTransaction(transaction);
	}
	
}
