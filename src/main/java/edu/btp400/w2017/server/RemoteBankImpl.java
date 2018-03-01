package edu.btp400.w2017.server;

import java.rmi.RemoteException;

import edu.btp400.w2017.common.RemoteBank;
import edu.btp400.w2017.accounts.Account;
import edu.btp400.w2017.business.Bank;

public class RemoteBankImpl implements RemoteBank {

	private static final Bank bank = new Bank();

	@Override
	public boolean addAccount(Account acc) throws RemoteException {
		return bank.addAccount(acc);
	}

	@Override
	public Account removeAccount(String accNum) throws RemoteException {
		return bank.removeAccount(accNum);
	}

	@Override
	public Account[] searchByBalance(double blcAcc) throws RemoteException {
		return bank.searchByBalance(blcAcc);
	}

	@Override
	public Account[] searchByAccountName(String accountName) throws RemoteException {
		return bank.searchByAccountName(accountName);
	}
}
