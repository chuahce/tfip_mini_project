package ibf2022.batch2.miniproject.server.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieSearchResponse implements Serializable {
    @JsonProperty("Search")
    private List<MovieScores> Search;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String Response;
}