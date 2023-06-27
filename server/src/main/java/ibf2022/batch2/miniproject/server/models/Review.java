package ibf2022.batch2.miniproject.server.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
   private String id;
   private String message;
   private String title;
   private Integer rating;
   private String movieId;
   private Long userId;
   private String username;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
   private Long createdBy;
   private Long updatedBy;

}
