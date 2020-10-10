/**
 * 
 */
package com.esspl.coronaTracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.test.web.client.TestRestTemplate.HttpClientOption;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.esspl.coronaTracker.model.Country;
import com.esspl.coronaTracker.model.Delta;
import com.esspl.coronaTracker.model.DistrictWise;
import com.esspl.coronaTracker.model.LocationStat;
import com.esspl.coronaTracker.model.StateWise;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

/**
 * @author Shubham
 *
 */

@Service
public class CoronaVirusDataService {

	/**
	 * @return the allStats
	 */
	public List<LocationStat> getAllStats() {
		return allStats;
	}
	/**
	 * @param allStats the allStats to set
	 */
	public void setAllStats(List<LocationStat> allStats) {
		this.allStats = allStats;
	}	
	/**
	 * @return the allRecoveredStats
	 */
	public List<LocationStat> getAllRecoveredStats() {
		return allRecoveredStats;
	}
	
	/**
	 * @param allRecoveredStats the allRecoveredStats to set
	 */
	public void setAllRecoveredStats(List<LocationStat> allRecoveredStats) {
		this.allRecoveredStats = allRecoveredStats;
	}



	private static final String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static final String VIRUS_RECOVERED_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	private static final String VIRUS_STATEWISE_URL="https://api.covid19india.org/v2/state_district_wise.json";
	
	private List<LocationStat> allStats = new ArrayList();
	private List<LocationStat> allRecoveredStats = new ArrayList();
	private List<StateWise> stateWiseStats = new ArrayList();
	private List<DistrictWise> districtWiseStats = new ArrayList();

	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchVirusData()
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		List<LocationStat> newStats = new ArrayList();
		try
		{
			HttpGet getRequest = new HttpGet(VIRUS_DATA_URL);
	         
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String apiOutput = EntityUtils.toString(httpEntity);
	        //System.out.println("Output is:"+apiOutput);
	        Reader r = new StringReader(apiOutput);
	        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(r);
	        for (CSVRecord record : records) {
	            LocationStat stat = new LocationStat();
	            stat.setState(record.get("Province/State"));
	        	stat.setCountry(record.get("Country/Region"));
	        	stat.setLatestCaseCount(Integer.parseInt(record.get(record.size()-1)));
	        	int todayCases = Integer.parseInt(record.get(record.size()-1));
	        	int previousDayCases = Integer.parseInt(record.get(record.size()-2));
	        	stat.setDiffFromPrevDay(todayCases-previousDayCases);
	        	//System.out.println(stat);
	        	newStats.add(stat);
	            /*String Country_Region = record.get("Country/Region");
	            String Lat = record.get("Lat");
	            String name = record.get("Lat");
	            */
	        }
	        
	        this.allStats = newStats;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchRecoveredData()
	{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		List<LocationStat> newStats = new ArrayList();
		try
		{
			HttpGet getRequest = new HttpGet(VIRUS_RECOVERED_URL);
	         
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String apiOutput = EntityUtils.toString(httpEntity);
	        //System.out.println("Output is:"+apiOutput);
	        Reader r = new StringReader(apiOutput);
	        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(r);
	        for (CSVRecord record : records) {
	            LocationStat stat = new LocationStat();
	            stat.setState(record.get("Province/State"));
	        	stat.setCountry(record.get("Country/Region"));
	        	stat.setLatestCaseCount(Integer.parseInt(record.get(record.size()-1)));
	        	int todayCases = Integer.parseInt(record.get(record.size()-1));
	        	int previousDayCases = Integer.parseInt(record.get(record.size()-2));
	        	stat.setDiffFromPrevDay(todayCases-previousDayCases);
	        	//System.out.println(stat);
	        	newStats.add(stat);
	            /*String Country_Region = record.get("Country/Region");
	            String Lat = record.get("Lat");
	            String name = record.get("Lat");
	            */
	        }
	        
	        this.allRecoveredStats = newStats;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchStateWiseData()
	{
		try
		{
			URL url = new URL(VIRUS_STATEWISE_URL);
			// read from the URL
		    Scanner scan = new Scanner(url.openStream());
		    String str = new String();
		    while (scan.hasNext())
		        str += scan.nextLine();
		    scan.close();
		    //System.out.println("Received String:"+str);
		    StringBuilder strBuilder = new StringBuilder(str);
		    String preData = "{ "+"\"Country\"".concat(":");
		    System.out.println(preData);
		    strBuilder.insert(0,preData);
		    strBuilder.append("}");
		    //System.out.println("String Prepend:"+strBuilder);
		    StringReader stringReaderobj = new StringReader(strBuilder.toString());
		   // build a JSON object
		    JSONObject obj = new JSONObject(strBuilder.toString());
		    JSONArray jsonArray = obj.getJSONArray("Country"); 
		    
		    List<StateWise> sdw = new ArrayList();
		    List<DistrictWise> ldw = new ArrayList();
		    for (int i = 0; i < jsonArray.length(); i++) 
		    { 
		    	JSONObject explrObject = jsonArray.getJSONObject(i);
		    	System.out.println("explrObject : "+explrObject );
		    	//System.out.println("State is :" +explrObject.get("state"));
		    	StateWise sw = new StateWise();
		    	JSONArray internaljsonArray = explrObject.getJSONArray("districtData");		    	
		    	int count =0;
		    	for(int j=0;j<internaljsonArray.length();j++)
		    	{
		    		DistrictWise dw = new DistrictWise();
		    		JSONObject internalexplrObject = internaljsonArray.getJSONObject(j);
		    		dw.setDistrictName(internalexplrObject.get("district").toString());
		    		dw.setConfirmed( internalexplrObject.getInt("confirmed"));
		    		count = count +  internalexplrObject.getInt("confirmed");
		    		ldw.add(dw);
		    	}
		    	
		    	sw.setStateName(explrObject.getString("state"));
		    	sw.setDw(ldw);
		    	sw.setStateCount(count);
		    	sdw.add(sw);
		    }
		    System.out.println("Final Total :"+sdw.size());
		    this.stateWiseStats =sdw;
		    this.districtWiseStats = ldw;
		    /*Gson gson = new Gson();
		    Country countryobject = gson.fromJson(stringReaderobj, Country.class);	
		    List<StateWise> stateWiseObject = countryobject.getStatewise();
		    System.out.println("Size of Data Recevied:"+stateWiseObject.size());
		    */
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @return the districtWiseStats
	 */
	public List<DistrictWise> getDistrictWiseStats() {
		return districtWiseStats;
	}
	/**
	 * @param districtWiseStats the districtWiseStats to set
	 */
	public void setDistrictWiseStats(List<DistrictWise> districtWiseStats) {
		this.districtWiseStats = districtWiseStats;
	}
	/**
	 * @return the stateWiseStats
	 */
	public List<StateWise> getStateWiseStats() {
		return stateWiseStats;
	}
	/**
	 * @param stateWiseStats the stateWiseStats to set
	 */
	public void setStateWiseStats(List<StateWise> stateWiseStats) {
		this.stateWiseStats = stateWiseStats;
	}
}
