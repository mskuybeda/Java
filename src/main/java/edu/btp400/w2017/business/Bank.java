package edu.btp400.w2017.business;

import java.util.ArrayList;
import java.util.Objects;

import edu.btp400.w2017.accounts.Account;
import edu.btp400.w2017.accounts.GIC;

/**
 * Description of com.java.accounts.Bank class
 *
 * @author Nick Skuybeda
 *         <p>
 *         The com.java.accounts.Bank class contains methods of adding Accounts, remove Account, searchByBalance and getAllAccounts.
 *         The types of com.java.accounts.Bank in the system includes Account and the name of the Account.
 */
public class Bank {

	private String nameBank;
	private ArrayList<Account> arrayAccounts;

	/**
	 * The Constructor default value will be "Seneca@York"
	 */
	public Bank() {
		this("Seneca@York");
	}

	/**
	 * Constructor for the com.java.accounts.Bank class
	 *
	 * @param nameBank one String argument constructor for the name of com.java.accounts.Bank
	 */
	public Bank(String nameBank) {
		this.nameBank = nameBank;
		this.arrayAccounts = new ArrayList<Account>();
	}

	/**
	 * @return Returns a String of the com.java.accounts.Bank Information menu.
	 */
	public String toString() {

		String s;
		s = "*****************************************\n" +
				"*           Bank Information        *\n" +
				"*****************************************\n" +
				"Bank Name: " + getBankName() + "\n" +
				"Number of Account:" + getAccountNum() + "\n\n";

		return s;
	}

	/**
	 * @return Returns true if the Account obj is the same, false otherwise.
	 */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Bank)) return false;
		Bank bank = (Bank) o;
		return Objects.equals(nameBank, bank.nameBank) &&
				Objects.equals(arrayAccounts, bank.arrayAccounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nameBank, arrayAccounts);
	}

	/**
	 * @param acc Account obj
	 * @return boolean - True if the account was added successful, false - otherwise
	 */
	public boolean addAccount(Account acc) {
		if (acc != null) {
			if (!arrayAccounts.contains(acc)) {
				arrayAccounts.add(acc);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param accNum String obj of Account number
	 * @return Account Returns the Account obj that was successfully remove, null otherwise.
	 */
	public Account removeAccount(String accNum) {
		Account temp;

		if (accNum != null || !accNum.trim().equals("")) {
			if (!this.arrayAccounts.isEmpty()) {
				for (int i = 0; i < arrayAccounts.size(); i++) {
					if (arrayAccounts.get(i).getAccountNumber().equals(accNum)) {
						temp = arrayAccounts.get(i);
						arrayAccounts.remove(i);
						return temp;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Search Accounts by Balance
	 *
	 * @param blcAcc Balance to be searched for.
	 * @return Returns an array of Accounts that match the condition. Null otherwise.
	 */
	public Account[] searchByBalance(double blcAcc) {
		if (arrayAccounts.size() > 0) {
			ArrayList<Account> accFound = new ArrayList<>();
			for (int i = 0; i < arrayAccounts.size(); i++) {
				double balance;
				if (arrayAccounts.get(i) instanceof GIC) {
					GIC gicAcc = (GIC) arrayAccounts.get(i);
					balance = gicAcc.getCurrentBalance();
				} else {
					balance = arrayAccounts.get(i).getBalance();
				}
				if (balance == blcAcc) {
					accFound.add(arrayAccounts.get(i));
				}
			}
			if (accFound.size() > 0) {
				Account[] ret = new Account[accFound.size()];
				ret = accFound.toArray(ret);
				return ret;
			} else {
				return new Account[0];
			}
		}
		return null;
	}

	/**
	 * @return account
	 */
	public Account[] searchByAccountName(String accountName) {
		boolean byFullName = accountName.contains(",");
		if (!byFullName) {
			accountName = accountName.replace(" ", "");
			if (accountName == null || accountName.isEmpty()) {
				return new Account[0];
			}
		}
		ArrayList<Account> accFound = new ArrayList<Account>();
		accountName = accountName.replace(",", "");
		for (int i = 0; i < arrayAccounts.size(); i++) {
			if (byFullName) {
				if (arrayAccounts.get(i).getFullName().equals(accountName)) {
					accFound.add(arrayAccounts.get(i));
				}
			} else {
				if (arrayAccounts.get(i).getFirstName().equals(accountName) || arrayAccounts.get(i).getLastName().equals(accountName)) {
					accFound.add(arrayAccounts.get(i));
				}
			}
		}
		if (accFound.size() > 0) {
			Account[] ret = new Account[accFound.size()];
			ret = accFound.toArray(ret);
			return ret;
		} else {
			return new Account[0];
		}
	}

	/**
	 * @return Returns an Array of total Accounts.
	 */
	public Account[] getAllAccounts() {
		if (arrayAccounts.size() > 0) {
			Account[] allAccounts = new Account[arrayAccounts.size()];
			return arrayAccounts.toArray(allAccounts);
		} else
			return null;
	}

	/**
	 * @return Returns the name of the com.java.accounts.Bank as String
	 */
	public String getBankName() {
		return nameBank;
	}

	/**
	 * @return Returns the size of the Account Array.
	 */
	public int getAccountNum() {
		return arrayAccounts.size();
	}

	/**
	 * @param name Sets the name of the com.java.accounts.Bank.
	 */
	public void setBankName(String name) {
		this.nameBank = name;
	}
}
