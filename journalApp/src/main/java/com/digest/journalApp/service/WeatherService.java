package com.digest.journalApp.service;

import com.digest.journalApp.cache.AppCache;
import com.digest.journalApp.constants.Placeholder;
import com.digest.journalApp.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {
 @Value("${weather.api.key}")
    private String keyapi;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;
    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }
        else {

            String finalapi = appCache.appCache.get(AppCache.keys.weather_api.toString()).replace(Placeholder.API_KEY, keyapi).replace(Placeholder.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResponse.class);
            if(response.getBody()!=null){
                redisService.set(city,response.getBody(),300l);
            }
            return response.getBody();
        }
    }


}
