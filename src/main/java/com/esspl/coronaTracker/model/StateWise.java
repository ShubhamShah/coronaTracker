/**
 * 
 */
package com.esspl.coronaTracker.model;

import java.util.List;

/**
 * @author Shubham
 *
 */
public class StateWise {

	private String StateName;
	private List<DistrictWise> dw;
	private int stateCount;
	
	/**
	 * @return the stateCount
	 */
	public int getStateCount() {
		return stateCount;
	}


	/**
	 * @param stateCount the stateCount to set
	 */
	public void setStateCount(int stateCount) {
		this.stateCount = stateCount;
	}


	public StateWise()
	{
		
	}
	
	
	/**
	 * @return the dw
	 */
	public List<DistrictWise> getDw() {
		return dw;
	}


	/**
	 * @param dw the dw to set
	 */
	public void setDw(List<DistrictWise> dw) {
		this.dw = dw;
	}


	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return StateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		StateName = stateName;
	}
	/**
	 * @return the dw
	 */
	
	
	
	
	
}
