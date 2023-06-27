package ibf2022.batch2.miniproject.server.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.miniproject.server.models.Review;
import ibf2022.batch2.miniproject.server.repositories.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        review.setCreatedBy(review.getUserId());
        return reviewRepository.save(review);
    }

    public Optional<List<Review>> getReviewsByMovie(String movieId) {
        List<Review> results = reviewRepository.findByMovieId(movieId);
        if (results.isEmpty())
            return Optional.empty();
        return Optional.of(results);
    }

    public void deleteReview(String id) {
        reviewRepository.delete(id);
    }

    public Optional<Review> updateReview(String id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id);
        if (existingReview != null) {
            updatedReview.setId(existingReview.getId());
            updatedReview.setUpdatedAt(LocalDateTime.now());
            return Optional.of(reviewRepository.update(updatedReview));
        }
        return Optional.empty();
    }
}

