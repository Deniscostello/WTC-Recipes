package ie.atu.scrapedfood;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapedFoodRepository extends MongoRepository<FoodData, ObjectId> {
    FoodData findByTitle(String title);

    List<FoodData> findByPrepTime(String prep_time);


    List<FoodData> findByIngredients(String ingredient);

    List<FoodData> findByCookTime(String cookingTime);

    FoodData findByRecipeId(int id);
}
