package ibf2022.batch2.miniproject.server.controllers;

import ibf2022.batch2.miniproject.server.models.WatchList;
import ibf2022.batch2.miniproject.server.services.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "*")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @PostMapping("/")
    public ResponseEntity<WatchList> saveWatchList(@RequestBody WatchList watchList) {
        WatchList savedWatchList = watchListService.saveWatchList(watchList);
        return new ResponseEntity<>(savedWatchList, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchList>> getWatchListsByUser(@PathVariable Long userId) {
        List<WatchList> watchLists = watchListService.getWatchListsByUser(userId);
        return new ResponseEntity<>(watchLists, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchList(@PathVariable String id) {
        watchListService.deleteWatchList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchList> findById(@PathVariable String id) {
        Optional<WatchList> watchList = watchListService.findById(id);
        return watchList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchList> updateWatchList(@PathVariable String id, @RequestBody WatchList updatedWatchList) {
        updatedWatchList.setId(id);
        Optional<WatchList> watchList = watchListService.updateWatchList(updatedWatchList);
        return watchList.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
