/**
 * 
 */
package com.esspl.coronaTracker.controller;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.esspl.coronaTracker.CoronaVirusDataService;
import com.esspl.coronaTracker.model.DistrictWise;
import com.esspl.coronaTracker.model.LocationStat;
import com.esspl.coronaTracker.model.StateWise;

/**
 * @author Shubham
 *
 */

@Controller
public class HomeController {

	@Autowired
	private CoronaVirusDataService coronavirusdataservice ;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStat> allStats =coronavirusdataservice.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat ->stat.getLatestCaseCount()).sum();
		int totalNewCases =  allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("test", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "home";
	}
	
	@GetMapping("/recovered")
	public String recovered(Model model)
	{
		List<LocationStat> allStats =coronavirusdataservice.getAllRecoveredStats();
		int totalReportedCases = allStats.stream().mapToInt(stat ->stat.getLatestCaseCount()).sum();
		int totalNewCases =  allStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("test", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		return "recovered";
	}
	
	@GetMapping("/india")
	public String IndiaData(Model model)
	{
		List<StateWise> stateWise = coronavirusdataservice.getStateWiseStats();
		List<DistrictWise> districtWise = coronavirusdataservice.getDistrictWiseStats();
		int totalReportedCases = districtWise.stream().mapToInt(stat->stat.getConfirmed()).sum();
		model.addAttribute("stateWise",stateWise);
		model.addAttribute("districtWise",districtWise);
		model.addAttribute("totalReportedCases",totalReportedCases);
		return "india";
	}
}
