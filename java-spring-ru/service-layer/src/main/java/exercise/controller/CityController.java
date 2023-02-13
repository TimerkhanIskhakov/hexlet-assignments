package exercise.controller;

import exercise.CityNotFoundException;
import exercise.dto.CityWeather;
import exercise.dto.Weather;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public Weather getCityInfo(@PathVariable long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        return weatherService.getWeather(city.getName());
    }

    @GetMapping("/search")
    public List<CityWeather> getCities(@RequestParam(required = false) String name) {
        List<City> cities;

        if (Objects.nonNull(name) && !name.isEmpty()) {
            cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        } else {
            cities = cityRepository.findByOrderByName();
        }
        return cities.stream()
                .map(c -> {
                    Weather weather = weatherService.getWeather(c.getName());
                    CityWeather cityWeather = new CityWeather();
                    cityWeather.setName(weather.getName());
                    cityWeather.setTemperature(weather.getTemperature());
                    return cityWeather;
                }).toList();
    }
    // END
}

