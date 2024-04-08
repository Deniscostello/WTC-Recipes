package ie.atu.scrapedfood;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recipes")
public class FoodData {
    private String url;
    private String title;
    private String description;
    private int recipeId;
    private String prepTime;
    private String cookTime;
    private String image;
    private List<String> ingredients;
    private float score;
    private List<String> steps;

}
