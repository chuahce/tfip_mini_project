package ibf2022.batch2.miniproject.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.miniproject.server.models.Review;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReviewRepository {

    private static final String COLLECTION_NAME = "reviews";

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review save(Review review) {
        return mongoTemplate.save(review, COLLECTION_NAME);
    }

    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Review.class, COLLECTION_NAME);
    }

    public Review findById(String id) {
        return mongoTemplate.findById(id, Review.class, COLLECTION_NAME);
    }

    public Review update(Review updatedReview) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updatedReview.getId()));
        Update update = new Update();
        update.set("message", updatedReview.getMessage());
        update.set("updatedAt", LocalDateTime.now());
        return mongoTemplate.findAndModify(query, update, Review.class, COLLECTION_NAME);
    }

    public List<Review> findByMovieId(String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        return mongoTemplate.find(query, Review.class, COLLECTION_NAME);
    }
}


