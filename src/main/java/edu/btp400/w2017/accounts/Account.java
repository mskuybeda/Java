package edu.btp400.w2017.accounts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import edu.btp400.w2017.helpers.NumberFormatHelper;

/**
 * This class defines an Account object. The account object holds all the data members that make up an account.
 *
 * @author Mykola Skuybeda
 */

public class Account implements Serializable {
/**
 * Account First and Last names
 */	

    private String firstName = "";
    private String lastName = "";
/**
 * Balance in Account
 */
    private BigDecimal balance;
	
/**
 * Account Number of the Account
 */
    private String accountNumber;
/**
 * Full Name of Account Holder
 */
	private String fullName = "";
	
/**
 * Constructor for an Account class
 */
    public Account(){}
	
/**
 * Argument Constructor - Sets values to ones passed as parameters. Forms full name into form of"Last name, First name"
 * @param balance     Balance
 */

    public Account(String fullName, String accountNumber, double balance){
		fullName = fullName.replace(" ", "");
		if (fullName.contains(",")) {
			this.firstName = fullName.split(",")[1];
			this.lastName = fullName.split(",")[0];
			this.fullName = fullName;
		}
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.valueOf(balance);
        if (balance < 0) {
            this.balance = BigDecimal.ZERO;
        }
    }
/**
 *@return firstName
 */ 
    public String getFirstName() {
        return firstName;
    }
/**
 *@return lastName
 */ 
    public String getLastName() {
        return lastName;
    }
/**
 *@return balance
 */ 
    public double getBalance() {
        return balance.doubleValue();
    }

    public void setBalance(double balance) {
        this.balance = BigDecimal.valueOf(balance);
    }
/**
 *@return accountNumber
 */ 
    public String getAccountNumber() {
        return accountNumber;
    }
/**
 *Allows to deposit money on the account
 *
 *@return boolean. True if amount is > 0. False otherwise
 */
    public boolean deposit(double amount){
        if (amount < 0){
            return false;
        }
        balance = balance.add(BigDecimal.valueOf(amount));
        return true;
    }
/**
 *Allows to withdraw money from the account
 *
 *@return boolean. True if amount is > 0. False otherwise
 */
    public boolean withdraw(double amount){
        if (amount < 0 || (balance.doubleValue() < amount)){
            return false;
        }
        balance = balance.subtract(BigDecimal.valueOf(amount));
        return true;
    }

    public String getFullName(){
        return lastName + " " + firstName;
    }
/**
 *Compares two accounts
 *
 *@return boolean. True if information matches. False otherwise
 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Account)) return false;
		Account account = (Account) o;
		return Objects.equals(getFirstName(), account.getFirstName()) &&
				Objects.equals(getLastName(), account.getLastName()) &&
				Objects.equals(getBalance(), account.getBalance()) &&
				Objects.equals(getAccountNumber(), account.getAccountNumber()) &&
				Objects.equals(getFullName(), account.getFullName());
	}
/**
 *@return absolutely unique number for an account
 */
	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName(), getBalance(), getAccountNumber(), getFullName());
	}
/**
 * Returns account as a string formatted for console output.
 *
 * @return the Account Information menu.
 */
	public String toString(){
        return new StringBuffer("Name: ").append(fullName)
                .append("\nNumber: ").append(accountNumber)
                .append("\nCurrent Balance: ").append(NumberFormatHelper.formatter.format(balance)).toString();

    }




}
