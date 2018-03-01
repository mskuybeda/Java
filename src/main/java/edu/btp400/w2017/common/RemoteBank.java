package edu.btp400.w2017.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.btp400.w2017.accounts.Account;

public interface RemoteBank extends Remote{
	boolean addAccount(Account acc) throws RemoteException;
	Account removeAccount(String accNum) throws RemoteException;
	Account[] searchByBalance(double blcAcc) throws RemoteException;
	Account[] searchByAccountName(String accountName) throws RemoteException;
}
