package com.example;

import com.example.model.City;
import com.example.repository.CitiesRepository;
import java.io.File;
import java.util.Comparator;
import java.util.List;
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

        citiesRepository.getCityList().sort(
                Comparator.comparing(a -> a.getName().toLowerCase())
        );
        System.out.println("Пример полученного результата для сортировки по наименованию:");
        for (City c : citiesRepository.getCityList()) {
            System.out.println(c);
        }

        citiesRepository.getCityList().sort(
                Comparator.comparing(City::getDistrict).thenComparing(City::getName)
        );
        System.out.println("Пример полученного результата для сортировки по двум полям справочника – федеральному округу и наименованию города:");
        for (City c : citiesRepository.getCityList()) {
            System.out.println(c);
        }
    }
}
