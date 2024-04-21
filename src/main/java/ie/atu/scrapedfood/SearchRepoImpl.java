package ie.atu.scrapedfood;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class SearchRepoImpl implements SearchRepository {


    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<FoodData> findByText(List<String> text) {
        List<FoodData> recipes = new ArrayList<>();

        MongoDatabase database = client.getDatabase("ScrapedRecipes");
        MongoCollection<Document> collection = database.getCollection("recipes");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default")
                                .append("text",
                                        new Document("query", text)
                                                .append("path", "ingredients"))),
                new Document("$project",
                        new Document("score",
                                new Document("$meta", "searchScore"))
                                .append("title", 1L)
                                .append("url", 1L)
                                .append("description", 1L)
                                .append("prepTime", 1L)
                                .append("cookTime", 1L)
                                .append("ingredients", 1L)
                                .append("recipeId", 1L)
                                .append("image", 1L)
                                .append("steps", 1L)),
                new Document("$sort",
                        new Document("score",
                                new Document("$meta", "textScore")))));

        //result.forEach(doc -> recipes.add(converter.read(FoodData.class, doc)));

        //only add the first (highest textScore) to recipes
        Iterator<Document> iterator = result.iterator();
        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
            recipes.add(converter.read(FoodData.class, iterator.next()));
        }

        return recipes;
    }
}


