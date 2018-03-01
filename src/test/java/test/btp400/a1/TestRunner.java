package test.btp400.a1;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner Class - Automatically Runs all the Tests for every class. Displays failure/success messages.
 *
 * @author Mykola Skuybeda
 */
public class TestRunner {
    public static void main(String[] args) {

        System.out.println("\n******** Starting Testing for Account, Chequing, Savings and GIC Classes!! ***********\n");

        int failed = 0;

        Result result = JUnitCore.runClasses(AccountTest.class);
        for (Failure failure : result.getFailures()) {
            failed++;
            System.out.println(failure.toString());
        }
        if (result.getFailureCount() == 0) {
            System.out.println("Account Test Passed!");
        }

        Result result2 = JUnitCore.runClasses(ChequingTest.class);
        for (Failure failure : result2.getFailures()) {
            failed++;
            System.out.println(failure.toString());
        }
        if (result2.getFailureCount() == 0) {
            System.out.println("Chequing Test Passed!");
        }

        Result result3 = JUnitCore.runClasses(SavingsTest.class);
        for (Failure failure : result3.getFailures()) {
            failed++;
            System.out.println(failure.toString());
        }
        if (result3.getFailureCount() == 0) {
            System.out.println("Savings Test Passed!");
        }

        Result result4 = JUnitCore.runClasses(GICTest.class);
        for (Failure failure : result4.getFailures()) {
            failed++;
            System.out.println(failure.toString());
        }
        if (result4.getFailureCount() == 0) {
            System.out.println("GIC Test Passed!");
        }

        if (failed == 0) {
            System.out.println("\n********* All tests passed successfully!!! ***********\n");
        } else {
            System.out.println("\n********* Some tests failed!!! ***********\n");
        }
    }
}