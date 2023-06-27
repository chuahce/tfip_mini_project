package ibf2022.batch2.miniproject.server.controllers;

import ibf2022.batch2.miniproject.server.models.Review;
import ibf2022.batch2.miniproject.server.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody Review review) {
        return ResponseEntity.ok()
                .body(reviewService.saveReview(review));
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<?> getCommentsByMovie(@PathVariable String movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id, @RequestBody Review review) {
        return ResponseEntity.ok()
                .body(reviewService.updateReview(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}