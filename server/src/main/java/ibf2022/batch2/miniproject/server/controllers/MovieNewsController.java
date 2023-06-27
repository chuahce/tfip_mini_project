package ibf2022.batch2.miniproject.server.controllers;

import ibf2022.batch2.miniproject.server.services.MovieNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class MovieNewsController {

  @Autowired
  private MovieNewsService movieNewsService;

  @GetMapping
  public ResponseEntity<Object> getNewsByCategory(
      @RequestParam("q") String category,
      @RequestParam(defaultValue = "20") int pageSize,
      @RequestParam(defaultValue = "1") int page) {
    Object news = movieNewsService.getNewsByCategory(category, pageSize, page);
    return new ResponseEntity<>(news, HttpStatus.OK);
  }
}
