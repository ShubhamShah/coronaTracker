/**
 * 
 */
package com.esspl.coronaTracker.model;

/**
 * @author Shubham
 *
 */
public class DistrictWise {

	private String districtName;
	private int confirmed;
	
	public DistrictWise()
	{
		
	}
	
	
	/**
	 * @param districtName
	 * @param confirmed
	 */
	public DistrictWise(String districtName, int confirmed) {
		super();
		this.districtName = districtName;
		this.confirmed = confirmed;
	}


	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * @return the confirmed
	 */
	public int getConfirmed() {
		return confirmed;
	}
	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
//	
}
