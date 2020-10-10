/**
 * 
 */
package com.esspl.coronaTracker.model;

/**
 * @author Shubham
 *
 */
public class LocationStat {
	
	private String state;
	private String country;
	private int latestCaseCount;
	private int diffFromPrevDay;
	/**
	 * @return the diffFromPrevDay
	 */
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	/**
	 * @param diffFromPrevDay the diffFromPrevDay to set
	 */
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the latestCaseCount
	 */
	public int getLatestCaseCount() {
		return latestCaseCount;
	}
	/**
	 * @param latestCaseCount the latestCaseCount to set
	 */
	public void setLatestCaseCount(int latestCaseCount) {
		this.latestCaseCount = latestCaseCount;
	}
	
	/**
	 * @param state
	 * @param country
	 * @param latestCaseCount
	 */
	public LocationStat(String state, String country, int latestCaseCount) {
		super();
		this.state = state;
		this.country = country;
		this.latestCaseCount = latestCaseCount;
	}
	public LocationStat() {
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationStat [state=" + state + ", country=" + country + ", latestCaseCount=" + latestCaseCount + "]";
	}
	

}
