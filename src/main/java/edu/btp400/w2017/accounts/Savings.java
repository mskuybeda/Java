package edu.btp400.w2017.accounts;

import java.math.BigDecimal;

import edu.btp400.w2017.helpers.NumberFormatHelper;

/**
     * Returns Savings information to be output on console.
     *
     * @return String containing account information.
     */

public class Savings extends Account implements Taxable {

    private double interestRate;
	private BigDecimal taxAmount = BigDecimal.valueOf(0.0);
	private double taxRate = 0.15;

    /**
     * 1 Argument Constructor. Sets values of parameters for a taxable account
     */
    public Savings() {
       super();
       this.interestRate = 0.10;
    }
   /**
     * 4 argument constructor. Sets values to ones passed as parameters.
     *
     * @param fullName   Account Name
     * @param accountNumber  Account Number
     * @param startingBalance Balance at the start
     * @param interestRate Interest rate
     */
    public Savings(String fullName, String accountNumber, double startingBalance, double interestRate){
        super(fullName, accountNumber, startingBalance);
        this.interestRate = interestRate;
    }

    public void calculateTax(double taxRate) {
		if (getInterestIncome() > 50) {
			taxAmount = BigDecimal.valueOf(getInterestIncome()).multiply(BigDecimal.valueOf(taxRate / 100));
		}
    }
    /**
     * @return tax calculation on deposited amount
     */
    @Override
    public boolean deposit(double amount) {
		boolean result = super.deposit(amount);
        calculateTax(taxRate);
        return result;
    }
    /**
     * @return tax calculation on withdraw amount
     */
    @Override
    public boolean withdraw(double amount) {
		boolean result = super.withdraw(amount);
        calculateTax(taxRate);
        return result;
    }
    /**
     * @return tax amount
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }
    /**
     * Returns Savings information to be output on console.
     *
     * @return String buffer containing information about the account.
     */
    public String createTaxStatement() {
		return new StringBuffer("Tax rate: ").append(NumberFormatHelper.precent.format(taxRate))
				.append("\nAccount Number: ").append(getAccountNumber())
				.append("\nInterest income: ").append(NumberFormatHelper.formatter.format(getInterestIncome()))
				.append("\nAmount of tax: ").append(NumberFormatHelper.formatter.format(taxAmount)).toString();
    }
   /**
     * @return interest rate calcualtions
     */
    public double getInterestIncome() {
        return BigDecimal.valueOf(super.getBalance()).multiply(BigDecimal.valueOf(interestRate / 100)).doubleValue();
    }
   /**
     * @return interst
     */
	public double getInterestRate() {
		return interestRate;
	}
   /**
     * @return balance with interest rate
     */
	@Override
	public double getBalance() {
		return super.getBalance() + getInterestIncome();
	}

    /**
     * Returns Savings TAX information to be output on console.
     *
     * @return String buffer containing information about the account.
     */
    @Override
    public String toString() {
        return new StringBuffer(super.toString())
                .append("\nType: SAV")
				.append("\nInterest Rate: ").append(NumberFormatHelper.precent.format(interestRate))
				.append("\nInterest Income: ").append(NumberFormatHelper.formatter.format(getInterestIncome()))
				.append("\nFinal Balance: ").append(NumberFormatHelper.formatter.format(getBalance())).toString();
    }
}
