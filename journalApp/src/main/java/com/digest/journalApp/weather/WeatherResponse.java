package com.digest.journalApp.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WeatherResponse {

    private Current current;

    @Data
    public class Current{
        @JsonProperty("last_updated_epoch")
        private int lastUpdatedEpoch;
        private String last_updated;
        private double temp_c;
        private double temp_f;
        private int is_day;
        private double wind_mph;
        private double wind_kph;
        private int wind_degree;
        private String wind_dir;
        private int pressure_mb;
        private double pressure_in;
        private double precip_mm;
        private double precip_in;
        private int humidity;
        private int cloud;
        private double feelslike_c;
        private double feelslike_f;
        private double windchill_c;
        private double windchill_f;
        private double heatindex_c;
        private double heatindex_f;
        private double dewpoint_c;
        private int dewpoint_f;
        private int vis_km;
        private int vis_miles;
        private int uv;
        private double gust_mph;
        private double gust_kph;
    }


}



