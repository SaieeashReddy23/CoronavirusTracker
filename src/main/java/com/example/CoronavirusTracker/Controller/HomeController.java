package com.example.CoronavirusTracker.Controller;

import com.example.CoronavirusTracker.models.LocationStats;
import com.example.CoronavirusTracker.services.dataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    dataService data;
    @RequestMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = data.getAllStats();
        int num = allStats.stream().mapToInt(stat ->Integer.parseInt( stat.getLatestStats())).sum();
        model.addAttribute("list",allStats);
        model.addAttribute("total",num);
        return "home";
    }
}
