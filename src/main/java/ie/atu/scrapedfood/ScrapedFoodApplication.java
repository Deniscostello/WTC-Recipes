package ie.atu.scrapedfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScrapedFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapedFoodApplication.class, args);
    }

}
