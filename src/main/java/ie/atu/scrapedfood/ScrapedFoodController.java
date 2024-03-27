package ie.atu.scrapedfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ScrapedFoodController {

    private ScrapedFoodService scrapedFoodService;
    @Autowired
    private SearchRepoImpl searchRepo;

    public ScrapedFoodController(ScrapedFoodService scrapedFoodService) {
        this.scrapedFoodService = scrapedFoodService;
    }

    @GetMapping("/findAll")
    public List<FoodData> findAllFood(){
        List<FoodData> foodByAll = scrapedFoodService.getByAllFood();
        return foodByAll;
    }

    @GetMapping("/findRecipesByText")
    public List<FoodData> getFoodText(@RequestParam String text){
        List<FoodData> foodByText = searchRepo.findByText(text);
        return foodByText;
    }
    @GetMapping("/findRecipesByTitle")
    public FoodData getFoodTitle(@RequestParam String title){
        FoodData foodByTitle = scrapedFoodService.getByTitleFood(title);
        return foodByTitle;
    }

    @GetMapping("/findRecipesByIngredient")
    public List<FoodData> getFoodUserIngredient(@RequestParam String ingredient){
        List<FoodData> foodByIngredient = scrapedFoodService.getByIngredient(ingredient);
        return foodByIngredient;
    }

    @GetMapping("/findRecipesByPrepTime")
    public List<FoodData> getFoodPrepTime(@RequestParam String prep_time){
        List<FoodData> prepTime = scrapedFoodService.getByPrepTime(prep_time);
        return prepTime;
    }
    @GetMapping("/findRecipesCookByTime")
    public List<FoodData> getFoodByCookTime(@RequestParam String cookTime){
        List<FoodData> foodByTime = scrapedFoodService.getWantedTime(cookTime);
        return foodByTime;
    }
}
