package edu.btp400.w2017.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import edu.btp400.w2017.common.RemoteBank;

public class RemoteBankServer {

	public static final String BINDING_NAME = "FinancialApp";

	public static void main(String... args) throws Exception {
		System.out.print("Starting registry...");
		final Registry registry = LocateRegistry.createRegistry(5678);
		System.out.println("OK");

		final RemoteBank service = new RemoteBankImpl();
		Remote stub = UnicastRemoteObject.exportObject(service, 0);

		System.out.print("Binding service...");
		registry.bind(BINDING_NAME, stub);
		System.out.println(" OK");

		while (true) {
			Thread.sleep(Integer.MAX_VALUE);
		}
	}
}
