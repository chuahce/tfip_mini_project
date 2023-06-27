package ibf2022.batch2.miniproject.server.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WatchList {
    private String id;
    private String movieId;
    private Long userId;
    private String title;
    private String posterPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
}

