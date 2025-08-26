import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class RecommenderExample {
    public static void main(String[] args) throws Exception {
        // Step 1: Load data
        DataModel model = new FileDataModel(new File("data.csv"));

        // Step 2: Compute similarity
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // Step 3: Find neighborhood
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        // Step 4: Build recommender
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        // Step 5: Get recommendations for user 1
        List<RecommendedItem> recommendations = recommender.recommend(1, 3);

        for (RecommendedItem recommendation : recommendations) {
            System.out.println("User 1 might like item " +
                    recommendation.getItemID() + " (score: " + recommendation.getValue() + ")");
        }
    }
}
