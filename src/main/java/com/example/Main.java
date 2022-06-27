package com.example;

import com.example.model.City;
import com.example.repository.CitiesRepository;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CitiesRepository citiesRepository = new CitiesRepository();
        File cities;
        try {
            cities = new File(Main.class.getClassLoader().getResource("cities.csv").toURI());
            Scanner scanner = new Scanner(cities);
            while (scanner.hasNext()) {
                String [] cityParse = scanner.nextLine().split(";", -5);
                City city = new City(cityParse[1], cityParse[2], cityParse[3], Integer.parseInt(cityParse[4]), cityParse[5]);
                citiesRepository.addCity(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Integer> filterMap = new HashMap<>();
        citiesRepository.getCityList()
                .forEach(s -> {
                    if (!filterMap.containsKey(s.getRegion())) {
                        filterMap.put(s.getRegion(), 1);
                    } else {
                        filterMap.put(s.getRegion(), filterMap.get(s.getRegion()) + 1);
                    }
                });
        for (Map.Entry<String, Integer> s : filterMap.entrySet()) {
            System.out.println(s.getKey() + " - " + s.getValue());
        }
    }
}
