/**
 * 
 */
package com.aag.sss.utils.impl;

import java.util.Date;

import com.aag.sss.utils.DateUtils;

/**
 * @author Alberto Alonso
 *
 */
public class DateUtilsImpl implements DateUtils{

	
	public Date getNow() {
		return new Date(System.currentTimeMillis());
	}

	
	public Date getBefore(int minutes) {
		return new Date(System.currentTimeMillis()-(minutes*60*1000));
	}

}
