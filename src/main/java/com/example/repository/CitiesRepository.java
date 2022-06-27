package com.example.repository;

import com.example.model.City;
import java.util.ArrayList;
import java.util.List;

public class CitiesRepository {
    private final List<City> cityList = new ArrayList<>();

    public List<City> getCityList() {
        return cityList;
    }

    public void addCity(City city) {
        cityList.add(city);
    }
}
