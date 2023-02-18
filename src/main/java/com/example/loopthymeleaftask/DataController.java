package com.example.loopthymeleaftask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class DataController {
    private final DataGeneratorService dataGeneratorService;

    public DataController(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;
    }

    @PostMapping("/generate-data")
    String generateData(
            @RequestParam int size,
            @RequestParam String language,
            @RequestParam(required = false) boolean firstName,
            @RequestParam(required = false) boolean lastName,
            @RequestParam(required = false) boolean university,
            @RequestParam(required = false) boolean country,
            Model model
    ){
        Map<String, List<String>> data = dataGeneratorService
                .generateFakeData(size,language,firstName,lastName,university,country);
        model.addAttribute("data", data);
        model.addAttribute("size", size);
        return "data-table";
    }
}
