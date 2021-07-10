package com.example.CoronavirusTracker.services;

import com.example.CoronavirusTracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class dataService {

    private static String data_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<LocationStats>();




    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * * 1 * *")
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> list = new ArrayList<LocationStats>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(data_URL)).build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records) {
            LocationStats loc = new LocationStats();

            loc.setCountry(record.get("Country/Region"));
            loc.setState(record.get("Province/State"));
            loc.setLatestStats(record.get(record.size()-1));
            loc.setDelta(Integer.parseInt(loc.getLatestStats())-Integer.parseInt(record.get(record.size()-2)));
            list.add(loc);

        }

        this.allStats = list;
    }
}
