package edu.btp400.w2017.helpers;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Helper to validate Currancy and Percentages for rates
 * @author Mykola Skuybeda
 */

public class NumberFormatHelper {

	public static NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
	public static NumberFormat precent = NumberFormat.getPercentInstance(Locale.CANADA);

	static {
		formatter.setMinimumFractionDigits(2);
		precent.setMinimumFractionDigits(2);
	}

}
