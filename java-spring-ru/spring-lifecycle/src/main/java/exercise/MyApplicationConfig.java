package exercise;

import exercise.daytimes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        LocalTime now = LocalTime.now();

        if (now.isAfter(LocalTime.of(0, 0))
                && now.isBefore(LocalTime.of(6, 0))) {
            return new Night();
        } else if (now.isAfter(LocalTime.of(6, 0))
                && now.isBefore(LocalTime.of(12, 0))) {
            return new Morning();
        } else if (now.isAfter(LocalTime.of(12, 0))
                && now.isBefore(LocalTime.of(18, 0))) {
            return new Day();
        } else if (now.isAfter(LocalTime.of(18, 0))
                && now.isBefore(LocalTime.of(23, 0))) {
            return new Evening();
        } else if (now.isAfter(LocalTime.of(23, 0))
            && now.isBefore(LocalTime.MAX)) {
            return new Night();
        }
        return null;
    }
}
// END
