/**
 * 
 */
package com.esspl.coronaTracker.model;

import java.util.List;

/**
 * @author Shubham
 *
 */
public class Country {
	private List<StateWise> statewise;

	/**
	 * @return the statewise
	 */
	public List<StateWise> getStatewise() {
		return statewise;
	}

	/**
	 * @param statewise the statewise to set
	 */
	public void setStatewise(List<StateWise> statewise) {
		this.statewise = statewise;
	}

	
	
}
