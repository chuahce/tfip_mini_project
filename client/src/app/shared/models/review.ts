export interface Review {
  id: string;
  message: string;
  title: string;
  rating: number;
  movieId: string;
  userId: number;
  username: string;
  createdAt: Date;
}

export interface ReviewResponse {
  page: number;
  results: Review[];
  total_results: number;
  total_pages: number;
}