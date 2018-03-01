package test.btp400.a1;

import java.util.Arrays;

import edu.btp400.w2017.accounts.Account;
import edu.btp400.w2017.business.Bank;

import junit.framework.TestCase;

/**
 * Created by Nick Skuybeda
 */
public class BankTester extends TestCase {

    Bank b1, b2, b3;
    Account a1, a2, a3, a4;

    protected void setUp() throws Exception {
        super.setUp();
		b1 = new Bank();
		b2 = new Bank();
		b3 = new Bank("Nick's Bank");

		a1 = new Account("Nick, Jhon", "027295146", 500);
		a2 = new Account("Skuybeda, Test", "132456", 200);
		a3 = new Account("Seneca, College","654321", 800);
		a4 = new Account("Seneca. College","654322", 800);
    }

	public void testEquals() throws Exception {

		assertEquals(false, b1.equals(b3));
		assertEquals(true, b1.equals(b2));
	}

	public void testAddAccount() throws Exception {

		assertEquals(true, b1.addAccount(a1));
		assertEquals(true, b2.addAccount(a2));
		assertEquals(false, b1.addAccount(a1));
	}

	public void testRemoveAccount() throws Exception {
		assertEquals(true, b2.addAccount(a2));
		assertEquals(true, b2.removeAccount("132456") != null);
	}

	public void testSearchByBalance() throws Exception {
		Account[] accounts = new Account[1];
		accounts[0] = a1;
		assertEquals(true, b1.addAccount(a1));
		assertEquals(true, Arrays.equals(b1.searchByBalance(500), accounts));
	}

	public void testSearchByName() throws Exception {
		Account[] accounts = new Account[1];
		accounts[0] = a1;
		assertEquals(true, b1.addAccount(a1));
		assertEquals(true, Arrays.equals(b1.searchByAccountName("Nick"), accounts));
	}

}
