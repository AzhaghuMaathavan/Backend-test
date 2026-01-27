package com.netflixclone.backend.util;

import com.netflixclone.backend.model.Genre;
import com.netflixclone.backend.model.Movie;
import com.netflixclone.backend.repository.GenreRepository;
import com.netflixclone.backend.repository.MovieRepository;
import com.netflixclone.backend.service.YouTubeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final YouTubeService youTubeService;

    public DataInitializer(MovieRepository movieRepository, GenreRepository genreRepository, YouTubeService youTubeService) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.youTubeService = youTubeService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicates
        if (movieRepository.count() > 0 || genreRepository.count() > 0) {
            return;
        }

        // Create Genres
        Genre sciFi = new Genre();
        sciFi.setName("Science Fiction");
        sciFi.setDescription("Movies about futuristic technology and space exploration");
        genreRepository.save(sciFi);

        Genre thriller = new Genre();
        thriller.setName("Thriller");
        thriller.setDescription("Suspenseful and thrilling movies");
        genreRepository.save(thriller);

        Genre action = new Genre();
        action.setName("Action");
        action.setDescription("High-octane action-packed movies");
        genreRepository.save(action);

        Genre drama = new Genre();
        drama.setName("Drama");
        drama.setDescription("Emotional and compelling dramas");
        genreRepository.save(drama);

        Genre animation = new Genre();
        animation.setName("Animation");
        animation.setDescription("Animated movies for all ages");
        genreRepository.save(animation);

        // Create Movies with Dummy Data
        
        // Movie 1: Jana Nayagan (Tamil)
        Movie movie1 = new Movie();
        movie1.setTitle("Jana Nayagan");
        movie1.setDescription("An official Tamil trailer for the upcoming film Jana Nayagan.");
        movie1.setDuration(180);
        movie1.setRating(8.0);
        movie1.setReleaseDate("2024-01-12");
        String video1Url = youTubeService.getVideoUrlByTitle("Jana Nayagan");
        movie1.setVideoUrl(video1Url);
        movie1.setThumbnailUrl(youTubeService.getThumbnailUrl(video1Url));
        movie1.setGenre(action);
        movieRepository.save(movie1);

        // Movie 2: KINGDOM (Tamil)
        Movie movie2 = new Movie();
        movie2.setTitle("KINGDOM");
        movie2.setDescription("Tamil trailer for the epic fantasy adventure KINGDOM.");
        movie2.setDuration(165);
        movie2.setRating(8.3);
        movie2.setReleaseDate("2024-02-15");
        String video2Url = youTubeService.getVideoUrlByTitle("KINGDOM");
        movie2.setVideoUrl(video2Url);
        movie2.setThumbnailUrl(youTubeService.getThumbnailUrl(video2Url));
        movie2.setGenre(drama);
        movieRepository.save(movie2);

        // Movie 3: Mark (Tamil)
        Movie movie3 = new Movie();
        movie3.setTitle("Mark");
        movie3.setDescription("Official Tamil trailer for the thrilling film Mark.");
        movie3.setDuration(155);
        movie3.setRating(8.1);
        movie3.setReleaseDate("2024-03-22");
        String video3Url = youTubeService.getVideoUrlByTitle("Mark");
        movie3.setVideoUrl(video3Url);
        movie3.setThumbnailUrl(youTubeService.getThumbnailUrl(video3Url));
        movie3.setGenre(thriller);
        movieRepository.save(movie3);

        // Movie 4: Parasakthi (Tamil)
        Movie movie4 = new Movie();
        movie4.setTitle("Parasakthi");
        movie4.setDescription("Official trailer for the classic Tamil film Parasakthi.");
        movie4.setDuration(160);
        movie4.setRating(8.2);
        movie4.setReleaseDate("2024-04-10");
        String video4Url = youTubeService.getVideoUrlByTitle("Parasakthi");
        movie4.setVideoUrl(video4Url);
        movie4.setThumbnailUrl(youTubeService.getThumbnailUrl(video4Url));
        movie4.setGenre(drama);
        movieRepository.save(movie4);

        // Movie 5: Diesel (Tamil)
        Movie movie5 = new Movie();
        movie5.setTitle("Diesel");
        movie5.setDescription("Official Tamil trailer for the action-packed film Diesel.");
        movie5.setDuration(168);
        movie5.setRating(8.0);
        movie5.setReleaseDate("2024-05-18");
        String video5Url = youTubeService.getVideoUrlByTitle("Diesel");
        movie5.setVideoUrl(video5Url);
        movie5.setThumbnailUrl(youTubeService.getThumbnailUrl(video5Url));
        movie5.setGenre(action);
        movieRepository.save(movie5);

        // Movie 6: Bigil (Tamil)
        Movie movie6 = new Movie();
        movie6.setTitle("Bigil");
        movie6.setDescription("A coach forms an underdog women's football team to challenge the established patriarchal system and win glory.");
        movie6.setDuration(170);
        movie6.setRating(7.8);
        movie6.setReleaseDate("2019-10-25");
        String video6Url = youTubeService.getVideoUrlByTitle("Bigil");
        movie6.setVideoUrl(video6Url);
        movie6.setThumbnailUrl(youTubeService.getThumbnailUrl(video6Url));
        movie6.setGenre(drama);
        movieRepository.save(movie6);

        // Movie 7: Kabali (Tamil)
        Movie movie7 = new Movie();
        movie7.setTitle("Kabali");
        movie7.setDescription("A seasoned gangster escapes from prison after 25 years and seeks redemption while taking revenge on his enemies.");
        movie7.setDuration(155);
        movie7.setRating(7.5);
        movie7.setReleaseDate("2016-07-22");
        String video7Url = youTubeService.getVideoUrlByTitle("Kabali");
        movie7.setVideoUrl(video7Url);
        movie7.setThumbnailUrl(youTubeService.getThumbnailUrl(video7Url));
        movie7.setGenre(action);
        movieRepository.save(movie7);

        // Movie 8: Sarkar (Tamil)
        Movie movie8 = new Movie();
        movie8.setTitle("Sarkar");
        movie8.setDescription("A visionary entrepreneur takes on the establishment and corporate magnates to expose their corruption and greed.");
        movie8.setDuration(150);
        movie8.setRating(7.7);
        movie8.setReleaseDate("2018-11-06");
        String video8Url = youTubeService.getVideoUrlByTitle("Sarkar");
        movie8.setVideoUrl(video8Url);
        movie8.setThumbnailUrl(youTubeService.getThumbnailUrl(video8Url));
        movie8.setGenre(thriller);
        movieRepository.save(movie8);

        // Movie 9: Kaavalan (Tamil)
        Movie movie9 = new Movie();
        movie9.setTitle("Kaavalan");
        movie9.setDescription("A reformed gangster returns home after 7 years in prison and tries to live an honest life while fighting his past.");
        movie9.setDuration(158);
        movie9.setRating(7.6);
        movie9.setReleaseDate("2011-12-16");
        String video9Url = youTubeService.getVideoUrlByTitle("Kaavalan");
        movie9.setVideoUrl(video9Url);
        movie9.setThumbnailUrl(youTubeService.getThumbnailUrl(video9Url));
        movie9.setGenre(action);
        movieRepository.save(movie9);

        // Movie 10: Leo (Tamil)
        Movie movie10 = new Movie();
        movie10.setTitle("Leo");
        movie10.setDescription("A skilled assassin with a mysterious past navigates through a web of crime, revenge, and unexpected alliances.");
        movie10.setDuration(158);
        movie10.setRating(8.3);
        movie10.setReleaseDate("2023-10-19");
        String video10Url = youTubeService.getVideoUrlByTitle("Leo");
        movie10.setVideoUrl(video10Url);
        movie10.setThumbnailUrl(youTubeService.getThumbnailUrl(video10Url));
        movie10.setGenre(action);
        movieRepository.save(movie10);
        movie10.setGenre(action);
        movieRepository.save(movie10);

        System.out.println("✅ Dummy data loaded successfully!");
        System.out.println("📊 Total Movies: " + movieRepository.count());
        System.out.println("🎭 Total Genres: " + genreRepository.count());
    }
}
