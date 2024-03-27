package ie.atu.scrapedfood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ScrapedFoodController {

    private final ScrapedFoodService scrapedFoodService;

    private final SearchRepoImpl searchRepo;

    public ScrapedFoodController(ScrapedFoodService scrapedFoodService, SearchRepoImpl searchRepo) {
        this.scrapedFoodService = scrapedFoodService;
        this.searchRepo = searchRepo;
    }

    @GetMapping("/findAll")
    public List<FoodData> findAllFood(){
        return scrapedFoodService.getByAllFood();
    }

    @GetMapping("/findRecipesByText")
    public List<FoodData> getFoodText(@RequestParam String text){
        return searchRepo.findByText(text);
    }
    @GetMapping("/findRecipesByTitle")
    public FoodData getFoodTitle(@RequestParam String title){
        return  scrapedFoodService.getByTitleFood(title);
    }

    @GetMapping("/findRecipesByIngredient")
    public List<FoodData> getFoodUserIngredient(@RequestParam String ingredient){
        return  scrapedFoodService.getByIngredient(ingredient);
    }

    @GetMapping("/findRecipesByPrepTime")
    public List<FoodData> getFoodPrepTime(@RequestParam String prep_time){
        return scrapedFoodService.getByPrepTime(prep_time);
    }
    @GetMapping("/findRecipesCookByTime")
    public List<FoodData> getFoodByCookTime(@RequestParam String cookTime){
        return scrapedFoodService.getWantedTime(cookTime);
    }
}
