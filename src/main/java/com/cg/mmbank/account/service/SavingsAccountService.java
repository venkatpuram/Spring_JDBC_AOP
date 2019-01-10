package com.cg.mmbank.account.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.mmbank.account.SavingsAccount;
import com.cg.mmbank.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) throws ClassNotFoundException, SQLException;

	SavingsAccount updateAccount(SavingsAccount account) throws SQLException, ClassNotFoundException;

	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	void deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) throws ClassNotFoundException, SQLException;
	void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;

	

	List<SavingsAccount> searchAccountByHolderName(String holderName) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException;

	List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException;

	List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException;

	List<SavingsAccount> sortByBalanceRange(int minimumBalance,int maximumBalance) throws ClassNotFoundException, SQLException;

	List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minBalance, int maxBalance) throws ClassNotFoundException, SQLException;

	List<SavingsAccount> sortByAccountBalanceInDescendingOrder() throws ClassNotFoundException, SQLException;	
}











