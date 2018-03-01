package edu.btp400.w2017.accounts;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import edu.btp400.w2017.helpers.NumberFormatHelper;

/**
 * This is the Chequing class that defines a Chequing Account. It inherits from the Account Class.
 *
 * @author Mykola Skuybeda
 */

public class Chequing extends Account {
	/**
     * Charge per transaction.
     */
	private BigDecimal serviceChargePerTran = BigDecimal.valueOf(0.25);
	/**
     * Maximum number of Transactions.
     */
	private int transactionAllowCount = 3;
	/**
     * History of Transactions.
     */
	private BigDecimal[] transactionHistory = new BigDecimal[0];
	/**
     * Transaction number currently on.
     */
	private int currentTrNum = 0;
    /**
     * Default Constructor - Creates superclass in default state.
     */
	public Chequing() {
		super();
	}
    /**
     * 5 argument constructor. Sets values to ones passed as parameters.
     *  @param fullName   Account Name
     * @param accountNumber  Account Number
	 * @param balance Balance
	 * @param serviceChargePerTran Transaction Charge
	 * @param transactionAllowCount    Max Number of Transactions
	 */
	public Chequing(String fullName, String accountNumber, double balance,
					BigDecimal serviceChargePerTran, int transactionAllowCount) {

		super(fullName, accountNumber, balance);
		this.serviceChargePerTran = serviceChargePerTran;
		this.transactionAllowCount = transactionAllowCount;
		this.transactionHistory = new BigDecimal[transactionAllowCount];
	}
    /**
     * Deposit amount into the Chequing Account.
     *
     * @param amount Amount to be deposited into account. Must be greater than 0. Uses up one transaction.
     * @return Returns a boolean. True if it was successful store or False otherwise.
     */
	@Override
	public boolean deposit(double amount) {
		if ((currentTrNum >= transactionAllowCount) || (amount < 0)) {
			return false;
		}
    /**
     * @return the balance with the account of transaction charges
     */
		setBalance(BigDecimal.valueOf(getBalance()).add((BigDecimal.valueOf(amount).subtract(serviceChargePerTran))).doubleValue());
		transactionHistory[currentTrNum] = BigDecimal.valueOf(amount);
		currentTrNum++;
		return true;

	}
    /**
     * Withdraw amount from the Chequing Account.
     *
     * @param amount Amount to be withdrawn. Must be greater than 0 and not result in negative balance. Uses up one transaction.
     * @return Returns a boolean. True if it was successful store or False otherwise.
     */

	@Override
	public boolean withdraw(double amount) {
		if ((currentTrNum >= transactionAllowCount) ||
				(amount < 0) || (getBalance() < (BigDecimal.valueOf(amount).add(serviceChargePerTran)).doubleValue())) {
			return false;
		}
    /**
	 * @return the balance with the account of transaction charges
     */
		setBalance(BigDecimal.valueOf(getBalance()).subtract((BigDecimal.valueOf(amount).add(serviceChargePerTran))).doubleValue());
		transactionHistory[currentTrNum] = BigDecimal.valueOf(-amount);
		currentTrNum++;
		return true;
	}
    /**
	 @return a string where all transactions are listed separated by coma
     */
	private String getListOfTransaction() {
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < transactionHistory.length; i++) {
			if (transactionHistory[i] == null) {
				return result.toString();
			}
			if (transactionHistory[i].doubleValue() >= 0) {
				result.append("+");
			}
			result.append(transactionHistory[i].doubleValue());
			if (i != transactionHistory.length) {
				result.append(", ");
			}
		}
		return result.toString();
	}
    /**
	@return total charge for transactions
	*/
	private double getTotalCharge() {
		return serviceChargePerTran.multiply(BigDecimal.valueOf(currentTrNum)).doubleValue();
	}
    /**
	@return balance without charge for transactions
	*/
	@Override
	public double getBalance() {
		return super.getBalance() - getTotalCharge();
	}
    /**
     * Returns Chequing information to be output on console.
     *
     * @return String containing account information.
     */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(super.toString())
				.append("\nType: CHQ")
				.append("\nService Charge: ").append(NumberFormatHelper.formatter.format(serviceChargePerTran))
				.append("\nTotal Service Charges:").append(NumberFormatHelper.formatter.format(getTotalCharge()))
				.append("\nNumber of Transactions Allowed: ").append(transactionAllowCount);
		if (!getListOfTransaction().isEmpty()) {
			result.append("\nList of Transactions: ").append(getListOfTransaction());
		}
		result.append("\nFinal Balance: ").append(NumberFormatHelper.formatter.format(getBalance()));
		return result.toString();
	}
   /**
    *Compares two chq accounts
	*
	@return information to be shown in a string with account information
	*/

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Chequing)) return false;
		if (!super.equals(o)) return false;
		Chequing chequing = (Chequing) o;
		return transactionAllowCount == chequing.transactionAllowCount &&
				currentTrNum == chequing.currentTrNum &&
				Objects.equals(serviceChargePerTran, chequing.serviceChargePerTran) &&
				Arrays.equals(transactionHistory, chequing.transactionHistory);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), serviceChargePerTran, transactionAllowCount, transactionHistory, currentTrNum);
	}
}
