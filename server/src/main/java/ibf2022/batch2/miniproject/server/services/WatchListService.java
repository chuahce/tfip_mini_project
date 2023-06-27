package ibf2022.batch2.miniproject.server.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.miniproject.server.models.WatchList;
import ibf2022.batch2.miniproject.server.repositories.WatchListRepository;

@Service
public class WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;

    public WatchList saveWatchList(WatchList watchList) {
        watchList.setCreatedAt(LocalDateTime.now());
        watchList.setUpdatedAt(LocalDateTime.now());
        watchList.setCreatedBy(watchList.getUserId());
        return watchListRepository.save(watchList);
    }

    public List<WatchList> getWatchListsByUser(Long userId) {
        return watchListRepository.findByUserId(userId);
    }

    public void deleteWatchList(String id) {
        watchListRepository.delete(id);
    }

    public Optional<WatchList> findById(String id) {
        WatchList watchList = watchListRepository.findById(id);
        return Optional.ofNullable(watchList);
    }

    public Optional<WatchList> updateWatchList(WatchList updatedWatchList) {
        WatchList existingWatchList = watchListRepository.findById(updatedWatchList.getId());
        if (existingWatchList != null) {
            updatedWatchList.setId(existingWatchList.getId());
            updatedWatchList.setUpdatedAt(LocalDateTime.now());
            return Optional.of(watchListRepository.update(updatedWatchList));
        }
        return Optional.empty();
    }
}


