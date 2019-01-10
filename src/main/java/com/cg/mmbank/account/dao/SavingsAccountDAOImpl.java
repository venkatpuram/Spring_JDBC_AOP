package com.cg.mmbank.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.mmbank.account.SavingsAccount;
import com.cg.mmbank.account.util.DBUtil;
import com.cg.mmbank.exception.AccountNotFoundException;
@Repository
public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	@Autowired
	private JdbcTemplate template;
	
	
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		
		template.update("INSERT INTO ACCOUNT(account_hn,account_bal,salary,od_limit,account_type) VALUES (?,?,?,?,?)",
				new Object[] {
						account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountBalance(),
						account.isSalary(),
						null,
						"SA"}
				);
		
		return account;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		
		return template.query("SELECT * FROM ACCOUNT", new SavingsAccountDAOMapper() );
	}

	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException{
		
		template.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",new Object[] {currentBalance,accountNumber});
	}

	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		
		
		return template.queryForObject("SELECT * FROM account where account_id=?", new Object[] {accountNumber},new SavingsAccountDAOMapper());
		
	}

	public SavingsAccount updateAccount(SavingsAccount savingsaccount) throws SQLException, ClassNotFoundException {
		
		template.update("UPDATE ACCOUNT SET account_hn=?,salary=? where account_id=?",
				new Object[] {
						savingsaccount.getBankAccount().getAccountHolderName(),
						savingsaccount.isSalary(),
						savingsaccount.getBankAccount().getAccountNumber()
				});
		return savingsaccount;
	}

	
	  public void commit() throws SQLException { DBUtil.commit(); }
	 

	public void deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		template.update("DELETE FROM account where account_id=?", accountNumber);
		
	}

	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		double balance=template.queryForObject("SELECT account_bal FROM account where account_id=?" , new Object[] {accountNumber} , Double.class);
		return balance;

	}

	public List<SavingsAccount> searchAccountByHolderName(String holderName)
			throws SQLException, AccountNotFoundException, ClassNotFoundException {
		
		return template.query("SELECT * FROM account WHERE account_hn=?", new Object[] {holderName}, new SavingsAccountDAOMapper());
	}
	
	
	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		
		return template.query("SELECT * FROM account ORDER BY account_hn", new SavingsAccountDAOMapper());
	}

	
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException{
		
		return template.query("SELECT * FROM account ORDER BY account_hn DESC", new SavingsAccountDAOMapper());
	}

	
	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException{
		
		return template.query("SELECT * FROM account ORDER BY account_bal", new SavingsAccountDAOMapper());
	}

	
	public List<SavingsAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException {
		
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal",new Object[] {minimumBalance,maximumBalance},new SavingsAccountDAOMapper());
	}

	
	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(
			int minBalance, int maxBalance) throws ClassNotFoundException, SQLException{
		
		return template.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC",new Object[] {minBalance,maxBalance},new SavingsAccountDAOMapper());
	}
	
	public List<SavingsAccount> sortByAccountBalanceInDescendingOrder() throws SQLException, ClassNotFoundException {
		
		return template.query("SELECT * FROM account ORDER BY account_bal DESC", new SavingsAccountDAOMapper());
	}
}
	
