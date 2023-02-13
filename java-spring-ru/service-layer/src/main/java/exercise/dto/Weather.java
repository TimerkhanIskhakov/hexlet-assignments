package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    private String name;

    private int temperature;

    private String cloudy;

    private int wind;

    private int humidity;
}
