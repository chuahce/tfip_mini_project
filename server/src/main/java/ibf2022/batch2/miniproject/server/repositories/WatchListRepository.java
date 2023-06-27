package ibf2022.batch2.miniproject.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.miniproject.server.models.WatchList;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WatchListRepository {

    private static final String COLLECTION_NAME = "watch_list";

    @Autowired
    private MongoTemplate mongoTemplate;

    public WatchList save(WatchList watchList) {
        watchList.setCreatedAt(LocalDateTime.now());
        watchList.setCreatedBy(watchList.getUserId());
        return mongoTemplate.save(watchList, COLLECTION_NAME);
    }

    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(id));
        mongoTemplate.remove(query, WatchList.class, COLLECTION_NAME);
    }

    public WatchList findById(String id) {
        return mongoTemplate.findById(id, WatchList.class, COLLECTION_NAME);
    }

    public WatchList update(WatchList updatedWatchList) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updatedWatchList.getId()));
        Update update = new Update();
        update.set("title", updatedWatchList.getTitle());
        update.set("posterPath", updatedWatchList.getPosterPath());
        update.set("updatedAt", LocalDateTime.now());
        return mongoTemplate.findAndModify(query, update, WatchList.class, COLLECTION_NAME);
    }

    public List<WatchList> findByUserId(Long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, WatchList.class, COLLECTION_NAME);
    }
}
