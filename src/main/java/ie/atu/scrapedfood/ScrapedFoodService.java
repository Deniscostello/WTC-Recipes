package ie.atu.scrapedfood;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class ScrapedFoodService {
    private final ScrapedFoodRepository scrapedFoodRepository;

    public ScrapedFoodService(ScrapedFoodRepository scrapedFoodRepository) {
        this.scrapedFoodRepository = scrapedFoodRepository;
    }


    public List<FoodData> getByIngredient(String userIngredient){
        return scrapedFoodRepository.findByIngredients(userIngredient);
    }

    public List<FoodData> getByPrepTime(String prepTime){
        return scrapedFoodRepository.findByPrepTime(prepTime);
    }

    public List<FoodData> getByAllFood(){
        return scrapedFoodRepository.findAll();
    }


    public FoodData getByTitleFood(String usersTitle){
        return scrapedFoodRepository.findByTitle(usersTitle);
    }
    public List<FoodData> getWantedTime (String cookingTime) {
        return scrapedFoodRepository.findByCookTime(cookingTime);
    }
}
