package edu.btp400.w2017.client;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;
import java.util.Scanner;

import edu.btp400.w2017.accounts.*;
import edu.btp400.w2017.common.RemoteBank;

/**
 * Financial App - App that allows you to process a variety of functions on the Account Objects. Includes Main Method
 *
 * @author Mykola Skuybeda
 */

public class FinancialApp {
    /**
     * @param name Name of Bank
     */
	private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

//	private static final Bank bank = new Bank();


	private static void displayMenu() {
		System.out.println("\n\nWelcome to Seneca@York Bank!\n" +
				"1. Open an account.\n" +
				"2. Close an account.\n" +
				"3. Deposit money.\n" +
				"4. Withdraw money.\n" +
				"5. Display accounts. \n" +
				"6. Display a tax statement.\n" +
				"7. Exit\n");
	}
    /**
     * creates account data
     */
	private static void loadBank(RemoteBank bank) throws RemoteException {
		Account sav1 = new Savings("Doe, John", "s1", 100, 0.15);
		Account sav2 = new Savings("Ryan, Mary", "s2", 100, 0.25);
		Account gic1 = new GIC("Doe, John", "g1", 6000.00, 2, 1.50);
		Account gic2 = new GIC("Ryan, Mary", "g2", 15000.00, 4, 2.50);
		Account ch1 = new Chequing("Doe, John", "c1", 213.12, BigDecimal.valueOf(0.10), 100);
		Account ch2 = new Chequing("Ryan, Mary", "c2", 100.00, BigDecimal.valueOf(0.25), 50);
		boolean a1 = bank.addAccount(sav1);
		boolean a2 = bank.addAccount(sav2);
		boolean a3 = bank.addAccount(gic1);
		boolean a4 = bank.addAccount(gic2);
		boolean a5 = bank.addAccount(ch1);
		boolean a6 = bank.addAccount(ch2);

		if (a1 && a2 && a3 && a4 && a5 && a6) {
			System.out.println("\n******** Load Bank Executed. Accounts Added Successfully! ********\n");
		} else {
			System.out.println("\n******** Failed to load bank. Accounts Could not be Added Successfully ********\n");
		}

	}

	private static void displayAccount(Account account) {
//		System.out.println("Account number: " + account.getAccountNumber());
//		System.out.println("Account name: " + account.getFullName());
		System.out.println(account.toString());
		System.out.println();
	}


	private static int menuChoice() {
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			scanner.nextLine();
			return scanner.nextInt();
		}
	}
//Add account

	public static void main(String[] args) throws RemoteException, NotBoundException {

		Registry registry = LocateRegistry.getRegistry("localhost", 5678);
		RemoteBank bankService = (RemoteBank) registry.lookup("FinancialApp");

		loadBank(bankService);


		displayMenu();
		System.out.println("Please enter your choice:\n");

		int choise;

		while ((choise = menuChoice()) != 7) {
			switch (choise) {
				case 1:
					openAccountAction(bankService);
					break;
				case 2:
					closeAccountAction(bankService);
					break;
				case 3:
					depositMoneyAction(bankService);
					break;
				case 4:
					withdrawMoneyAction(bankService);
					break;
				case 5:
					displayAccountsAction(bankService);
					break;
				case 6:
					displayTaxStatementAction(bankService);
					break;
				case -1:
					System.out.println("Incorrect data type");
					break;
			}
			displayMenu();
		}

		System.out.println("Thank you!");

	}

    /**
     * Accont search with validations
	 *@return accounts lists
     */
	private static Account[] findAccbySmth(RemoteBank bankService) throws Exception {
		Account[] accounts = new Account[0];
		System.out.println("I want find Accounts by\n");
		System.out.println("1 - account name\n");
		System.out.println("2 - balance\n");
		int c = -1;
		try {
			c = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			throw e;
		}
		switch (c) {
			case 1:
				System.out.println("Please enter account name at one line\n");
				scanner.nextLine();
				String accountName = scanner.nextLine();
				accounts = bankService.searchByAccountName(accountName);
				break;
			case 2:
				System.out.println("Please enter account balance at one line\n");
				double balance = 0;
				try {
					balance = scanner.nextDouble();
				} catch (Exception e) {
					System.out.println("Incorrect data type");
				}
				accounts = bankService.searchByBalance(balance);
				break;
			default:
				System.out.println("Incorrect choice");
				break;
		}
		return accounts;
	}

	private static void openAccountAction(RemoteBank bankService) throws RemoteException {
		String line;
		String[] data;
		String fullName;
		String accountNumber;
		double balance;
		double interestRate;
		BigDecimal serviceChargePerTran;
		int tranAllowCount;
		int periodOfInvestment;
		String accountName;

		System.out.println("Please enter the account type (SAV/CHQ/GIC): ");
		scanner.nextLine();
		String accountType = scanner.nextLine();
		System.out.println("\n");
		System.out.println("Please use the following format! \n");
		System.out.println("*** For a Savings Account - Name; Account# ; Balance; Interest Rate ***");
		System.out.println("Example: First, Last; Account123; 99.99; 1.23 \n");
		System.out.println("*** For a GIC Account - Name, Account#; Balance; Interest Rate; Investment Period in Months ***");
		System.out.println("Example: First, Last; Account123; 99.99; 12; 1.23 \n");
		System.out.println("*** For a Chequing Account - Name; Account#; Balance; Service Charge; Transactions Allowed ***");
		System.out.println("Example: First, Last; Account123; 99.99; 0.25; 50 \n");
		System.out.println("Please Enter the Account Details: ");
		switch (accountType) {
			case "SAV":
				System.out.println("Please enter account information at one line\n");
				line = scanner.nextLine();
				line = line.replace(" ", "");
				data = line.split(";");
				fullName = data[0];
				accountNumber = data[1];
				balance = Double.parseDouble(data[2]);
				interestRate = Double.parseDouble(data[3]);
				boolean result = bankService.addAccount(new Savings(fullName, accountNumber, balance, interestRate));
				if (result) {
					System.out.println("Account added");
				} else {
					System.out.println("Something wrong!");
				}
				break;
			case "CHQ":
				System.out.println("Please enter account information at one line\n");
				line = scanner.nextLine();
				line = line.replace(" ", "");
				data = line.split(";");
				fullName = data[0];
				accountNumber = data[1];
				balance = Double.parseDouble(data[2]);
				serviceChargePerTran = BigDecimal.valueOf(Double.parseDouble(data[3]));
				tranAllowCount = Integer.parseInt(data[4]);
				Account account = new Chequing(fullName, accountNumber, balance,
						serviceChargePerTran, tranAllowCount);
				result = bankService.addAccount(account);
				if (result) {
					System.out.println("Account added");
				} else {
					System.out.println("Something wrong!");
				}
				break;
			case "GIC":
				System.out.println("Please enter account information at one line\n");
				line = scanner.nextLine();
				line = line.replace(" ", "");
				data = line.split(";");
				fullName = data[0];
				accountNumber = data[1];
				balance = Double.parseDouble(data[2]);
				periodOfInvestment = Integer.parseInt(data[3]);
				interestRate = Double.parseDouble(data[4]);
				account = new GIC(fullName, accountNumber, balance,
						periodOfInvestment, interestRate);
				result = bankService.addAccount(account);
				if (result) {
					System.out.println("Account added");
				} else {
					System.out.println("Something wrong!");
				}
				break;
		}
	}

	private static void closeAccountAction(RemoteBank bankService) throws RemoteException {
		Account[] accounts = new Account[0];

		try {
			accounts = findAccbySmth(bankService);
		} catch (Exception e) {
			return;
		}
		if (accounts.length == 0) {
			System.out.println("Accounts not found");
			return;
		}
		int count = 0;
		for (Account account : accounts) {
			count++;
			System.out.println("# " + count);
			displayAccount(account);
		}
		System.out.println();
		System.out.println("Choose # account for close");
		int number = 0;
		try {
			number = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			return;
		}
		Account account = bankService.removeAccount(accounts[number - 1].getAccountNumber());
		if (account != null) {
			System.out.println("Account deleted");
		} else {
			System.out.println("Something wrong!");
		}
	}

	private static void depositMoneyAction(RemoteBank bankService) {
		Account[] accounts = new Account[0];
		try {
			accounts = findAccbySmth(bankService);
		} catch (Exception e) {
			return;
		}
		if (accounts.length == 0) {
			System.out.println("Accounts not found");
			return;
		}
		int count = 0;
		Account[] accountsNew = new Account[accounts.length];
		int j = 0;
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i].getClass() != GIC.class) {
				accountsNew[j] = accounts[i];
				j++;
			}
		}

		int ind = 0;
		while ((accountsNew[ind] != null) && (ind < accountsNew.length)){
			count++;
			System.out.println("# " + count);
			displayAccount(accountsNew[ind]);
			ind++;
		}
		System.out.println();
		System.out.println("Choose # account for deposit");
		int number = 0;
		try {
			number = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			return;
		}
		System.out.println("Insert sum for deposit");
		double sum = 0;
		try {
			sum = scanner.nextDouble();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			return;
		}
		boolean result = accountsNew[number - 1].deposit(sum);
		if (result) {
			System.out.println("Deposit done");
		} else {
			System.out.println("Something wrong!");
		}
	}

	private static void withdrawMoneyAction(RemoteBank bankService) {
		Account[] accounts = new Account[0];
		try {
			accounts = findAccbySmth(bankService);
		} catch (Exception e) {
			return;
		}
		if (accounts.length == 0) {
			System.out.println("Accounts not found");
			return;
		}
		int count = 0;
		Account[] accountsNew = new Account[accounts.length];
		int j = 0;
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i].getClass() != GIC.class) {
				accountsNew[j] = accounts[i];
				j++;
			}
		}

		int ind = 0;
		while ((accountsNew[ind] != null) && (ind < accountsNew.length)){
			count++;
			System.out.println("# " + count);
			displayAccount(accountsNew[ind]);
			ind++;
		}
		System.out.println();
		System.out.println("Choose # account for withdraw");
		int number = 0;
		try {
			number = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			return;
		}
		System.out.println("Insert sum for withdraw");
		double sum = 0.0;
		try {
			sum = scanner.nextDouble();
		} catch (Exception e) {
			System.out.println("Incorrect data type");
			return;
		}
		boolean result = accountsNew[number - 1].withdraw(sum);
		if (result) {
			System.out.println("Withdraw done");
		} else {
			System.out.println("Something wrong!");
		}
	}

	private static void displayAccountsAction(RemoteBank bankService) {
		Account[] accounts = new Account[0];
		try {
			accounts = findAccbySmth(bankService);
		} catch (Exception e) {
			return;
		}
		if (accounts.length == 0) {
			System.out.println("Accounts not found");
			return;
		}
		for (int i = 0; i < accounts.length; i++) {
			System.out.print(i + 1 + ".\n");
			displayAccount(accounts[i]);
		}
	}

	private static void displayTaxStatementAction(RemoteBank bankService) {
		Account[] accounts = new Account[0];
		try {
			accounts = findAccbySmth(bankService);
		} catch (Exception e) {
			return;
		}
		if (accounts.length == 0) {
			System.out.println("Accounts not found");
			return;
		}
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] instanceof Taxable) {
				System.out.println(i + 1 + ".\n");
				System.out.println(((Taxable) accounts[i]).createTaxStatement());
			}
		}
	}
}
