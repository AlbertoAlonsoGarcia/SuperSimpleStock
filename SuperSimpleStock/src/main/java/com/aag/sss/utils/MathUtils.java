/**
 * 
 */
package com.aag.sss.utils;


import org.apache.commons.math3.stat.StatUtils;
/**
 * @author Alberto Alonso
 *
 */
public class MathUtils {

	public static double geometricMean(double[] stockPricesArray) {

		return StatUtils.geometricMean(stockPricesArray);
	}

}
