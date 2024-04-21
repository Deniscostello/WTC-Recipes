package ie.atu.scrapedfood;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

//    @PostMapping("/findRecipes")
//    public List<FoodData> recipe( @RequestBody List<String> usersFood){
//        System.out.println(usersFood);
//        return searchRepo.findByText(usersFood);
//    }


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

//    @PostMapping("/findRecipeById")
//    public List<FoodData> getRecipeById(@RequestBody List<Integer> ids){
//        long startTime = System.currentTimeMillis();
//        List<FoodData> recipes = new ArrayList<>();
//        for(int i=0; i < ids.size(); i++){
//            recipes.add(scrapedFoodService.findRecipeById(ids.get(i)));
//
//        }
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("Total time = " + (endTime - startTime) + " ms" );
////        Map<String, List<FoodData>> response = new HashMap<>();
////        response.put("recipes", recipes);
//        return recipes;
//    }


    @PostMapping("/findRecipes")
    public List<FoodData> recipe( @RequestBody List<String> usersFood){
        return searchRepo.findByText(usersFood);
    }


    @PostMapping("/findRecipeById")
    public List<FoodData> getRecipeById (@RequestBody List<Integer> ids) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<FoodData>> futures = new ArrayList<>();
        for(int i=0; i < ids.size(); i++){
            final int index = i;
            CompletableFuture<FoodData> future = CompletableFuture.supplyAsync(() -> scrapedFoodService.findRecipeById(ids.get(index)));
           // fu.add(scrapedFoodService.findRecipeById(ids.get(i)));
            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allOf.get();

        List<FoodData> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();
        System.out.println("Total time = " + (endTime - startTime) + " ms" );
        return results;
    }

}
