package test.btp400.a1;


import edu.btp400.w2017.accounts.Savings;

import junit.framework.TestCase;

/**
 * Account Class Test
 *
 * @author Mykola Skuybeda
 */
public class SavingsTest extends TestCase {

    private Savings s1, s2, s3, s4;

    /**
     * Create Accounts for Testing.
     *
     * @throws Exception thrown in-case construction fails.
     */
    protected void setUp() throws Exception {
        super.setUp();
        s1 = new Savings("Test1", "c1", 51.00, 3.5);
        s2 = new Savings("Test2", "c2", 0.00, 0);
        s3 = new Savings("Test1", "c1", 51.00, 3.5);
        s4 = new Savings("Test3", "c3", 100, 10);
    }

    /**
     * Test Equals Method for Account Class.
     *
     * @throws Exception if results other than expected.
     */
    public void testEquals() throws Exception {
        assertEquals("s1.equals(s2) returned False", false, s1.equals(s2));
        assertEquals("s1.equals(s3) returned False", true, s1.equals(s3));
        assertEquals("s1.equals(s4) returned True", false, s1.equals(s4));

    }

    public void testGetInterestIncome() {
        assertEquals(true, Math.abs(s4.getInterestIncome() - 10.0) < 0.0001);
        assertEquals(false, Math.abs(s4.getInterestIncome() - 11.0) < 0.0001);
    }

    public void testGetBalance() {
        assertEquals(true, Math.abs(s4.getBalance() - 110.0) < 0.0001);
        assertEquals(false, Math.abs(s4.getBalance() - 111.0) < 0.0001);
    }

    public void testGetTaxAmount() {
        s4.calculateTax(25.0);
        assertEquals(true, s4.getTaxAmount().doubleValue() == 0.0);
    }

    public void testBalance() throws Exception {

        assertEquals("a1.withdraw(100) returned True", false, s1.withdraw(100));
        assertEquals("a1.deposit(100) returned False", true, s2.deposit(100));
    }

    public void testNegativeDeposit() throws Exception {

        assertEquals(false, s1.deposit(-100));
        assertEquals(true, s2.deposit(100));
    }

    public void testNegativeWithDraw() throws Exception {

        assertEquals(true, s1.withdraw(10));
        assertEquals(false, s2.withdraw(-900));
    }
}