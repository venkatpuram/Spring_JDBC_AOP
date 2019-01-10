package com.cg.mmbank.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mmbank.account.SavingsAccount;
import com.cg.mmbank.account.dao.SavingsAccountDAO;
import com.cg.mmbank.account.dao.SavingsAccountDAOImpl;
import com.cg.mmbank.account.factory.AccountFactory;
import com.cg.mmbank.account.util.DBUtil;
import com.cg.mmbank.exception.AccountNotFoundException;
import com.cg.mmbank.exception.InsufficientFundsException;
import com.cg.mmbank.exception.InvalidInputException;

@Service
@Transactional
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		/* savingsAccountDAO = new SavingsAccountDAOImpl(); */
	}

	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			// savingsAccountDAO.commit();
		
	}

	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			// savingsAccountDAO.commit();
		
	}

	@Transactional(rollbackForClassName = { "Throwable" })
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		withdraw(sender, amount);
		deposit(receiver, amount);

		/*
		 * try {
		 * 
		 * DBUtil.commit(); } catch (InvalidInputException | InsufficientFundsException
		 * e) { e.printStackTrace(); DBUtil.rollback(); } catch (Exception e) {
		 * e.printStackTrace(); DBUtil.rollback(); }
		 */
	}

	public SavingsAccount updateAccount(SavingsAccount savingsaccount) throws SQLException, ClassNotFoundException {

		return savingsAccountDAO.updateAccount(savingsaccount);
	}

	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	public void deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		savingsAccountDAO.deleteAccount(accountNumber);
	}

	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.checkBalance(accountNumber);
	}

	public List<SavingsAccount> searchAccountByHolderName(String holderName)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {

		return savingsAccountDAO.searchAccountByHolderName(holderName);
	}

	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderName();
	}

	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderNameInDescendingOrder();
	}

	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountBalance();
	}

	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRange(minimumBalance, maximumBalance);
	}

	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minBalance, int maxBalance)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRangeInDescendingOrder(minBalance, maxBalance);
	}

	public List<SavingsAccount> sortByAccountBalanceInDescendingOrder() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountBalanceInDescendingOrder();
	}

}
