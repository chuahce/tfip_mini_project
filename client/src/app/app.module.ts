import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { PaginatorModule } from 'primeng/paginator';
import { TabViewModule } from 'primeng/tabview';
import { ImageModule } from 'primeng/image';
import { CarouselModule } from 'primeng/carousel';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CardModule } from 'primeng/card';
import { SidebarModule } from 'primeng/sidebar';
import { MenuModule } from 'primeng/menu';
import { PanelModule } from 'primeng/panel';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { RatingModule } from 'primeng/rating';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { ReactiveFormsModule } from '@angular/forms';
import { DividerModule } from 'primeng/divider';
import { SplitterModule } from 'primeng/splitter'

import { AppComponent } from './app.component';
import { HeaderComponent } from './core/header/header.component';
import { FooterComponent } from './core/footer/footer.component';
import { HomeComponent } from './features/home/home.component';
import { MoviesComponent } from './features/movies/movies.component';
import { SliderComponent } from './shared/components/slider/slider.component';
import { ItemsBannerComponent } from './shared/components/items-banner/items-banner.component';
import { ItemComponent } from './shared/components/item/item.component';
import { MovieDetailsComponent } from './features/movie-details/movie-details.component';
import { TrailerPlayerComponent } from './shared/components/trailer-player/trailer-player.component';
import { GenresComponent } from './features/genres/genres.component';
import { MovieNewsComponent } from './features/movie-news/movie-news.component';
import { ArticleComponent } from './shared/components/article/article.component';
import { MovieScoresComponent } from './features/movie-scores/movie-scores.component';
import { MovieScoresCardComponent } from './shared/components/movie-scores-card/movie-scores-card.component';

import { AuthComponent } from './features/auth/auth.component';
import { ReviewsComponent } from './features/reviews/reviews.component';
import { AddReviewComponent } from './features/reviews/add-review/add-review.component';
import { WatchlistComponent } from './features/watchlist/watchlist.component';
import { WatchListMoviesComponent } from './features/watchlist/watchlist-movies/watchlist-movies.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    MoviesComponent,
    SliderComponent,
    ItemsBannerComponent,
    ItemComponent,
    MovieDetailsComponent,
    TrailerPlayerComponent,
    GenresComponent,
    MovieNewsComponent,
    ArticleComponent,
    MovieScoresComponent,
    MovieScoresCardComponent,
    ReviewsComponent,
    AddReviewComponent,
    AuthComponent,
    WatchlistComponent,
    WatchListMoviesComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    AppRoutingModule,
    HttpClientModule,
    PaginatorModule,
    TabViewModule,
    ImageModule,
    CarouselModule,
    InputTextModule,
    FontAwesomeModule,
    DialogModule,
    CardModule,
    SidebarModule,
    MenuModule,
    SplitterModule,
    ReactiveFormsModule,
    PanelModule,
    RatingModule,
    DividerModule,
    InputTextareaModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }






