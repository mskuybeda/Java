package edu.btp400.w2017.accounts;

import java.math.BigDecimal;

import edu.btp400.w2017.helpers.NumberFormatHelper;

/**
 * This is the GIC class that defines a GIC Account. It inherits from the Account Class.
 *
 * @author Mykola Skuybeda
 */

public class GIC extends Account implements Taxable {
	private int periodOfInvestment;
	private double interestRate;
	private double taxRate = 0.15;
	private BigDecimal taxAmount = BigDecimal.valueOf(0.0);
    /**
     * 2 Argument Constructor. Sets values of parameters for a taxable account with a set period
     */
	public GIC() {
		this.periodOfInvestment = 1;
		this.interestRate = 1.25;
		calculateTax(taxRate);
	}
   /**
     * 5 argument constructor. Sets values to ones passed as parameters.
     *
     * @param fullName   Account Name
     * @param accountNumber  Account Number
     * @param balance Balance
	 * @param periodOfInvestment Investment period
     * @param interestRate Interest rate
     */
	public GIC(String fullName, String accountNumber, double balance,
			   int periodOfInvestment, double interestRate) {

		super(fullName, accountNumber, balance);
		this.periodOfInvestment = periodOfInvestment;
		this.interestRate = interestRate;
		calculateTax(taxRate);
	}
    /**
     * 2 Argument Constructor. Sets values of parameters
     *
	 * @return check for GIC period of investment
     */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GIC)) return false;

		GIC gic = (GIC) o;

		if (periodOfInvestment != gic.periodOfInvestment) return false;
		return Double.compare(gic.interestRate, interestRate) == 0;

	}
/**
 *@return absolutely unique number for an GIC
 */
	@Override
	public int hashCode() {
		int result;
		long temp;
		result = periodOfInvestment;
		temp = Double.doubleToLongBits(interestRate);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
/**
 *@return boolean value for deposit in GIC
 */
	@Override
	public boolean deposit(double amount) {
		return false;
	}
/**
 *@return boolean value for withdrawal in GIC
 */
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
/**
 *@return balance with account of interest at Maturity value
 */

	@Override
	public double getBalance() {
		return BigDecimal.valueOf(super.getBalance()).add(BigDecimal.valueOf(getInterestIncomeAtMaturity())).doubleValue();
	}

	public double getCurrentBalance() {
		return super.getBalance();
	}

/**
 *@return interest at Maturity value with an account ot taxes
 */
	public double getInterestIncomeAtMaturity() {
		return BigDecimal.valueOf(getBalanceAtMaturity()).subtract(BigDecimal.valueOf(super.getBalance())).doubleValue(); // TODO
	}
/**
 *@return taxation at maturity
 */
	public double getBalanceAtMaturity() {
		return BigDecimal.valueOf(super.getBalance()).multiply(BigDecimal.valueOf(1 + interestRate/100).pow(periodOfInvestment)).doubleValue();
	}
    /**
     * Returns GIC information to be output on console.
     *
     * @return String buffer containing information about the account.
     */
	@Override
	public String toString() {
		return new StringBuffer(super.toString())
				.append("\nType: GIC")
				.append("\nPeriod of Investment: ").append(periodOfInvestment).append(" years")
				.append("\nInterest Income at Maturity: ").append(NumberFormatHelper.formatter.format(getInterestIncomeAtMaturity()))
				.append("\nBalance at Maturity: ").append(NumberFormatHelper.formatter.format(getBalanceAtMaturity())).toString();
	}

	@Override
	public void calculateTax(double taxRate) {
		taxAmount = BigDecimal.valueOf(getInterestIncomeAtMaturity()).multiply(BigDecimal.valueOf(taxRate / 100));
	}
/**
 *@return amount of tax
 */
	@Override
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
    /**
     * Returns GIC TAX information to be output on console.
     *
     * @return String buffer containing information about the account.
     */
	@Override
	public String createTaxStatement() {
		return new StringBuffer("Tax rate: ").append(NumberFormatHelper.precent.format(taxRate))
				.append("\nAccount Number: ").append(getAccountNumber())
				.append("\nInterest income at maturity: ").append(NumberFormatHelper.formatter.format(getInterestIncomeAtMaturity()))
				.append("\nAmount of tax: ").append(NumberFormatHelper.formatter.format(getTaxAmount())).toString();
	}
}
