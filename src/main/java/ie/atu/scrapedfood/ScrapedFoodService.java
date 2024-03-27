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
        List<FoodData> foodFound = scrapedFoodRepository.findByIngredients(userIngredient);
        return foodFound;
    }

    public List<FoodData> getByPrepTime(String prepTime){
        List<FoodData> food = scrapedFoodRepository.findByPrepTime(prepTime);
        return food;
    }

    public List<FoodData> getByAllFood(){
        List<FoodData> food = scrapedFoodRepository.findAll();
        return food;
    }


    public FoodData getByTitleFood(String usersTitle){
        FoodData food = scrapedFoodRepository.findByTitle(usersTitle);
        return food;
    }
    public List<FoodData> getWantedTime (String cookingTime) {
        List<FoodData> foodByTime = scrapedFoodRepository.findByCookTime(cookingTime);
        return foodByTime;
    }
}
