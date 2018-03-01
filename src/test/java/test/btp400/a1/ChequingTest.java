package test.btp400.a1;


import java.math.BigDecimal;

import edu.btp400.w2017.accounts.Chequing;

import junit.framework.TestCase;

/**
 * Chequing Class Test
 *
 * @author Mykola Skuybeda
 */
public class ChequingTest extends TestCase {

    private Chequing c1, c2, c3;

    /**
     * Create Accounts for Testing.
     *
     * @throws Exception thrown in-case construction fails.
     */
    protected void setUp() throws Exception {
        super.setUp();
        c1 = new Chequing("Test1", "ACC1", 50.00, BigDecimal.valueOf(0.10), 10);
        c2 = new Chequing("Test2", "ACC2", 100.00, BigDecimal.valueOf(0.50), 2);
        c3 = new Chequing("Test1", "ACC1", 50.00, BigDecimal.valueOf(0.10), 10);
    }


    /**
     * Test Equals Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testEquals() throws Exception {
        assertEquals("c1.equals(c2) returned False", false, c1.equals(c2));
        assertEquals("c1.equals(c3) returned False", true, c1.equals(c3));
    }

    /**
     * Test Deposit Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testDeposit() throws Exception {
        assertEquals("c1 deposit UnSuccessful", true, c1.deposit(10.00));
        assertEquals("c2 deposit Successful", false, c2.deposit(-10.00));

    }

    /**
     * Test Withdraw Method
     *
     * @throws Exception thrown in-case result is other than expected.
     */
    public void testWithdraw() throws Exception {
        assertEquals("c1 withdraw Unsuccessful", true, c1.withdraw(15.00));
        assertEquals("c2 withdraw Successful", false, c2.withdraw(110.00));

    }

    public void testBalance() throws Exception {

        assertEquals("a1.withdraw(10) returned False", true, c1.withdraw(10));
        assertEquals("a1.deposit(30) returned False", true, c3.deposit(30));
    }

	public void testTransactionAllow() throws Exception {

		assertEquals("a1.deposit(30) returned False", true, c2.deposit(30));
		assertEquals("a1.deposit(30) returned False", true, c2.deposit(30));
		assertEquals("a1.deposit(30) returned True", false, c2.deposit(30));

	}


}