package test.btp400.a1;


import edu.btp400.w2017.accounts.GIC;

import junit.framework.TestCase;

/**
 * GIC Class Test
 *
 * @author Mykola Skuybeda
 */
public class GICTest extends TestCase {

    private GIC g1, g2, g3, g4;

    /**
     * Create Accounts for Testing.
     *
     * @throws Exception thrown in-case construction fails.
     */
    protected void setUp() throws Exception {
        super.setUp();
        g1 = new GIC("Test1", "a1", 51.00, 142, 12.5);
        g2 = new GIC("Test2", "a2", 0.00, 24, 1.51);
        g3 = new GIC("Test1", "a1", 51.00, 142, 12.5);
        g4 = new GIC("Test3", "a3", 100.00, 1, 10);
    }

    /**
     * Test Equals Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testEquals() throws Exception {
        assertEquals("g1.equals(g2) returned False", false, g1.equals(g2));
        assertEquals("g1.equals(g3) returned False", true, g1.equals(g3));
    }

    /**
     * Test Deposit Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testDeposit() throws Exception {
        assertEquals("c1 deposit Successful", false, g1.deposit(10.00));
        assertEquals("c2 deposit Successful", false, g2.deposit(-10.00));
    }

    /**
     * Test Withdraw Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testWithdraw() throws Exception {
        assertEquals("c1 withdraw Successful", false, g1.withdraw(15.00));
        assertEquals("c2 withdraw Successful", false, g2.withdraw(120.00));
    }

    public void testGetBalanceAtMaturity() {
        assertEquals(true, Math.abs(g4.getBalanceAtMaturity() - 110.0) < 0.0001);
    }

	public void testGetInterestIncomeAtMaturity() {
		assertEquals(true, Math.abs(g4.getInterestIncomeAtMaturity() - 10.0) < 0.001);
	}

}