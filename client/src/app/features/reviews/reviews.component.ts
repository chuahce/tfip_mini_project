import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/shared/models/review';
import { ReviewService } from 'src/app/shared/services/review.service';


@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  @Input() movieTitle: string = '';
  @Input() isLoggedIn?: boolean;

  reviews: Review[] = [];
  movieId: string | null = null;
  searchValue: string | null = null;

  addReviewFormVisible = false;

  constructor(private reviewService: ReviewService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.movieId = this.route.snapshot.paramMap.get('id');
    this.getReviews(this.movieId!);
  }

  getReviews(page: string) {
    this.reviewService.getReviews(page).subscribe((review) => {
      this.reviews = review;
    });
  }

  addReviewOnClick() {
    if (this.isLoggedIn == true) {
      this.addReviewFormVisible = true
    } else {
      this.router.navigate(['../../auth/signin'], { relativeTo: this.route });
    }
  }
}