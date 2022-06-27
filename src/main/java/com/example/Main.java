package com.example;

import com.example.model.City;
import com.example.repository.CitiesRepository;
import java.io.File;
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
        for (City c : citiesRepository.getCityList()) {
            System.out.println(c);
        }
    }
}
