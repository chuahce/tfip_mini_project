import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from 'src/app/shared/services/review.service';
import { StorageService } from 'src/app/shared/services/storage.service';


@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent {
  @Input() movieId: string | null = '';

  addReviewFormVisible = true;

  reviewForm = this.fb.group({
    title: ['', Validators.required],
    username: ['', Validators.required],
    rating: ['', Validators.required],
    message: ['', Validators.required]
  });

  constructor(private fb: FormBuilder,
    private reviewService: ReviewService,
    private router: Router,
    private route: ActivatedRoute,
    private storage: StorageService
  ) {
  }

  submitReview() {
    if (this.reviewForm.valid) {
      // Submit the review to the server
      const reviewData = this.reviewForm.value;

      const review = {
        ...reviewData,
        movieId: this.movieId,
        userId: this.storage.getUser().id
      }
      this.reviewService.addReviews(review).subscribe({
        next: (value) => {
          this.navigateToSamePage()
        },
        error(err) {
          console.error(err);
        },
      });
      this.reviewForm.reset();
    }
  }

  get f() {
    return this.reviewForm.controls;
  }

  navigateToSamePage(): void {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigateByUrl(currentUrl);
    });
  }

}