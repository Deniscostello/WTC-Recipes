package ie.atu.scrapedfood;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/findRecipes")
    public List<FoodData> recipe( @RequestBody List<String> usersFood){
        System.out.println(usersFood);
        return searchRepo.findByText(usersFood);
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

    @PostMapping("/findRecipeById")
    public List<String> getRecipeById(@RequestBody List<String> ids){
        System.out.println(ids);

        return ids;
    }
}
