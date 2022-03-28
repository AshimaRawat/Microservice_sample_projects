package WebApplication.covid_tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home_Controller {

	@Autowired
	service serv;
	@GetMapping("/")
	public String home(Model model)
	{
		List<model_location_stats> allStats= serv.getAllStats();
		int totalReportedCases= allStats.stream().mapToInt(stat->stat.getLatest_reported()).sum();
		model.addAttribute("model_location_stats", serv.getAllStats());
		model.addAttribute("totalReportedCases", totalReportedCases);
		return "home";
	}
}
