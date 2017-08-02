package com.davisuhlig.gotothemovies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by duhlig on 8/2/17.
 */
@Controller
public class MovieController {
    String route = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping("/now-playing")
    public String nowPlaying(Model model) {
        List<Movie> moviesNowPlaying = getMovies(route);
        model.addAttribute("moviesNowPlaying", moviesNowPlaying);
        return "now-playing";
    }

    @RequestMapping("/medium-popular-long-name")
    public String mediumPopularLongName(Model model) {
        List<Movie> mediumPopularityLongNameMovies = getMovies(route).stream()
                .filter(e -> e.getPopularity() >= 30 && e.getPopularity() <= 80)
                .filter(e -> e.getTitle().length() >= 10)
                .collect(Collectors.toList());
        model.addAttribute("mediumPopularityLongNameMovies", mediumPopularityLongNameMovies);
        return "medium-popular-long-name";
    }

    public static List<Movie> getMovies(String route){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(route, ResultsPage.class).getResults();
    }
}
