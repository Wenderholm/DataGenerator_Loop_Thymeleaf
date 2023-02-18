package com.example.loopthymeleaftask;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class DataGeneratorService {
    Map<String, List<String>> generateFakeData(
            @RequestParam int size,
            @RequestParam String language,
            @RequestParam(required = false) boolean firstname,
            @RequestParam(required = false) boolean lastname,
            @RequestParam(required = false) boolean university,
            @RequestParam(required = false) boolean country
    ){
        Faker faker = new Faker(new Locale(language));
        Map<String,List<String>> data = new LinkedHashMap<>();
        if(firstname){
            data.put((faker.equals("pl-PL"))?"Imię:":"Name:" , generateData(size, () -> faker.name().firstName()));
        }
        if(lastname){
            data.put((faker.equals("pl-PL"))?"Nazwisko:":"Surname:"  , generateData(size, () -> faker.name().lastName()));
        }
        if(university){
            data.put((faker.equals("pl-PL"))?"Uczelnia:":"University:"  , generateData(size, () -> faker.university().name()));
        }
        if(country){
            data.put((faker.equals("pl-PL"))?"Kraj pochodzenia:":"Country:"  , generateData(size, () -> faker.country().name()));
        }
        return data;
    }

    private List<String> generateData(int size, Supplier<String> supplier){
        return Stream.generate(supplier)
                .limit(size)
                .toList();
    }
}
