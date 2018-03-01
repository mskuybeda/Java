package edu.btp400.w2017.accounts;

import java.math.BigDecimal;

/**
 * Calculates taxes. It inherits from the Account Class.
 * @author by Mykola Skuybeda
 */
public interface Taxable {

    void calculateTax(double taxRate);

    BigDecimal getTaxAmount();

    String createTaxStatement();

}
