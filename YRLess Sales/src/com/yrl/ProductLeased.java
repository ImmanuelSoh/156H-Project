package com.yrl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *  Author: Immanuel Soh
 *  		isoh2@huskers.unl.edu
 *  
 *  Date: 	2024/03/08
 *  
 *  Purpose: This is a Product Class that represents products for the purposes
 *   		 of converting csv data to xml and json data and printing reports
 */

public class ProductLeased extends Item {
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	public ProductLeased(String code, String name, String cost, LocalDate startDate, LocalDate endDate) {
		super(code, name, cost);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Double getSubtotal() {
		return this.getCost() * 1.5;
	}
	
	public Double getTax() {
		return this.roundToTwoDecimals(getSubtotal() * 0);
	}
	
	public Integer getMonths() {
		int months = (int) this.startDate.until(this.endDate, ChronoUnit.MONTHS);
		return months;
	}
	
	public Double getTotal() {
		return this.roundToTwoDecimals(getSubtotal()) / getMonths();
	}
}