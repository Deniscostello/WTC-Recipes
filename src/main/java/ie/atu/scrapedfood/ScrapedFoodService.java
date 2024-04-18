package ie.atu.scrapedfood;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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

    public List<FoodData> findRecipeById(List<Integer> recipeIds){
        List<FoodData> allRecipes = new ArrayList<>();
        for(int i=0; i < recipeIds.size(); i++){
            allRecipes.add(scrapedFoodRepository.findByRecipeId(recipeIds.get(i)));
        }

        return allRecipes;
    }
}
