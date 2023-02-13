package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.HttpClient;
import exercise.dto.Weather;
import org.springframework.stereotype.Service;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    private static final String HTTP_WEATHER_API_V_2_CITIES = "http://weather/api/v2/cities/";

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Weather getWeather(String city) {
        String response = client.get(HTTP_WEATHER_API_V_2_CITIES + city);
        Weather weather;
        try {
            weather = new ObjectMapper().readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weather;
    }
    // END
}
