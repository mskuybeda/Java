package test.btp400.a1;

import edu.btp400.w2017.accounts.Account;

import junit.framework.TestCase;

/**
 * Account Class Test
 *
 * @author Mykola Skuybeda
 */
public class AccountTest extends TestCase {

    private Account a1, a2, a3;

    /**
     * Create Accounts for Testing.
     *
     * @throws Exception thrown in-case construction fails.
     */
    protected void setUp() throws Exception {
        super.setUp();
        a1 = new Account("Test1", "ACC1", 50.00);
        a2 = new Account("Test2", "ACC2", 10.00);
        a3 = new Account("Test1", "ACC1", 50.00);
    }

    /**
     * Test Equals Method for Account Class.
     *
     * @throws Exception if results other than expected.
     */

    public void testEquals() throws Exception {

        assertEquals("a1.equals(a2) returned True", false, a1.equals(a2));
        assertEquals("a1.equals(a3) returned False", true, a1.equals(a3));
    }

    public void testBalance() throws Exception {

        assertEquals("a1.withdraw(100) returned True", false, a1.withdraw(100));
        assertEquals("a1.deposit(100) returned False", true, a1.deposit(100));
    }

	public void testNegativeDeposit() throws Exception {

		assertEquals(false, a2.deposit(-100));
		assertEquals(true, a1.deposit(100));
	}

	public void testNegativeWithDraw() throws Exception {

		assertEquals(true, a2.withdraw(10));
		assertEquals(false, a1.withdraw(-900));
	}

	public void testGetBalance() throws Exception {
		assertEquals(true, a1.getBalance() == 50.0);
	}
}
