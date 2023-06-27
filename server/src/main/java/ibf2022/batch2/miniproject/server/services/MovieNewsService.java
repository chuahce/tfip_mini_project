package ibf2022.batch2.miniproject.server.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MovieNewsService {
    private final String baseUrl;
    private final String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    
    private Map<String, String> categories = new HashMap<>();

    public MovieNewsService(@Value("${tmdb.newsapi.baseurl}") String baseUrl, @Value("${tmdb.newsapi.key}") String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        setupCategories();
    }

    private void setupCategories() {
        categories.put("boxOffice", "movies AND (worldwide box office OR Hollywood box office OR blockbuster)");
        categories.put("hollywoodHighlights", "movies AND (Hollywood OR celebrity news OR castings OR director news)");
        categories.put("asianSpotlight", "movies AND (Asian OR Chinese OR Korean OR Japanese OR Bollywood or Hong Kong OR HongKong OR China)");
        categories.put("indieCorner", "movies AND (indie OR independent OR film festival OR Sundance OR Cannes)");
        categories.put("imdb", "movies AND IMDb");
        categories.put("rottenTomatoes", "movies AND Rotten Tomatoes");
        categories.put("metacritic", "movies AND (Metacritic score OR metascore )");
        categories.put("netflix", "movies AND Netflix");
        categories.put("disneyPlus", "movies AND Disney+");
        categories.put("appleTVPlus", "movies AND AppleTV+");
        categories.put("starWars", "movies AND (Star Wars OR Lucasfilm)");
        categories.put("mcu", "movies AND (MCU OR Marvel Cinematic Universe OR Marvel Universe OR Avengers)");
        categories.put("dceu", "movies AND (DCEU OR DC Extended Universe OR Justice League OR DC Comics)");
        categories.put("pixar", "movies AND (Pixar)");
        categories.put("clasicCinema", "movies AND (classic OR golden age OR retro OR nostalgia)");
        categories.put("tvTalk", "movies AND (TV series OR season premiere OR season finale)");
        categories.put("directorsCut", "movies AND (director news OR filmmaker OR behind the scenes)");
        categories.put("sciFiAndFantasy", "movies AND (sci-fi OR fantasy)");
        categories.put("documentaryDigest", "movies AND (documentaries OR true story OR non-fiction)");
        categories.put("horrorHub", "movies AND (horror OR scary)");
        categories.put("animationStation", "movies AND (animated OR animation OR CGI OR anime OR 3D animation)");
    }

    public Object getNewsByCategory(String category, int pageSize, int page) {
        String q = categories.get(category);
        return getNews(q, pageSize, page);
    }

    private Object getNews(String q, int pageSize, int page) {
        String fromDate = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment("everything")
                .queryParam("q", q)
                .queryParam("from", fromDate)
                .queryParam("language", "en")
                .queryParam("sortBy", "relevancy")
                .queryParam("pageSize", pageSize)
                .queryParam("page", page)
                .queryParam("apiKey", apiKey)
                .toUriString();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();


        HttpResponse<String> response = null;
       try {
           response =
                   client.send(request, HttpResponse.BodyHandlers.ofString());
       } catch (Exception e){
           log.error(e.getMessage());
       }

        // Check if response is valid and contains body
        if (response.statusCode() == 200 && response.body() != null) {
            return response.body();
        }
        
        // In case of an error, return an empty
        return new Object();
    }
}
