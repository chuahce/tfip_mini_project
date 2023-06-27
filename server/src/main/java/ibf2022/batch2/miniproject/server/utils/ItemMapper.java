package ibf2022.batch2.miniproject.server.utils;

import ibf2022.batch2.miniproject.server.models.Item;
import ibf2022.batch2.miniproject.server.models.Movie;

public class ItemMapper {

  public static Item mapMovieToItem(Movie movie) {
    Item item = new Item();

    item.setId(movie.getId());
    item.setTitle(movie.getTitle());
    item.setPoster_path(movie.getPoster_path());
    item.setVote_average(movie.getVote_average());
    item.setBackdrop_path(movie.getBackdrop_path());
    item.setVote_count(movie.getVote_count());
    item.setRelease_date(movie.getRelease_date());
    item.setOverview(movie.getOverview());
    item.setRoutePath("/movie/" + movie.getId());

    return item;
  }
}
