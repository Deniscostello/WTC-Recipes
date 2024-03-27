package ie.atu.scrapedfood;

import java.util.List;

public interface SearchRepository {

    List<FoodData> findByText(String text);
}
