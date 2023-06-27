package ibf2022.batch2.miniproject.server.services;

import ibf2022.batch2.miniproject.server.models.MovieScores;
import ibf2022.batch2.miniproject.server.models.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieScoresService {

    @Value("${omdbapi.baseurl}")
    private String baseurl;

    @Value("${omdbapi.apikey}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    public List<MovieScores> getMoviesByIDs(List<String> IDs) {
        return IDs.stream()
                .map(this::getMovieScores)
                .sorted(Comparator.comparing(MovieScores::getReleased).reversed())
                .collect(Collectors.toList());
    }

    public MovieSearchResponse searchMovies(String searchValue) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseurl)
                .queryParam("apikey", apikey)
                .queryParam("s", searchValue)
                .build()
                .toUri();

        ResponseEntity<MovieSearchResponse> response = restTemplate.exchange(uri, HttpMethod.GET, null, MovieSearchResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return new MovieSearchResponse();
        }
    }

    public MovieScores getMovieScores(String imdbID) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseurl)
                .queryParam("apikey", apikey)
                .queryParam("i", imdbID)
                .build()
                .toUri();

        return restTemplate.exchange(uri, HttpMethod.GET, null, MovieScores.class).getBody();
    }

    public List<Object> searchMovieOnTMDb(String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format("%s/search/movie", baseurl))
                .queryParam("api_key", apikey)
                .queryParam("query", title)
                .build()
                .toUri();

        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>(){});
        List<Object> response = responseEntity.getBody();

        return response;
    }
}
