package WebApplication.covid_tracker;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class service 
{
private static String data_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
private List<model_location_stats> allStats =new ArrayList<>();


	public List<model_location_stats> getAllStats() {
	return allStats;
}

	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException
	{
		List<model_location_stats> newStats =new ArrayList<>();
		HttpClient client= HttpClient.newHttpClient();
		HttpRequest request= HttpRequest.newBuilder().uri(URI.create(data_url)).build();
		HttpResponse<String> httpResponse= client.send(request, HttpResponse.BodyHandlers.ofString());
		
		StringReader csv_reader= new StringReader(httpResponse.body());
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv_reader);
		for (CSVRecord record : records)
			{
			model_location_stats locStats= new model_location_stats();
			locStats.setState(record.get("Province/State"));
			locStats.setCountry(record.get("Country/Region"));
			int latestCases= Integer.parseInt(record.get(record.size()-1));
			int prevCases= Integer.parseInt(record.get(record.size()-2));
			locStats.setLatest_reported(latestCases);
			locStats.setDiff_prev(latestCases-prevCases);
			newStats.add(locStats);
			}
		this.allStats= newStats;
	}
	
}
