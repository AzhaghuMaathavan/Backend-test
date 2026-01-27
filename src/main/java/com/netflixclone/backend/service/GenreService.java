package com.netflixclone.backend.service;

import com.netflixclone.backend.model.Genre;
import com.netflixclone.backend.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Create a new genre
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Get all genres
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Get genre by ID
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

    // Get genre by name
    public Genre getGenreByName(String name) {
        return genreRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Genre not found with name: " + name));
    }

    // Update a genre
    public Genre updateGenre(Long id, Genre genreDetails) {
        Genre genre = getGenreById(id);
        genre.setName(genreDetails.getName());
        genre.setDescription(genreDetails.getDescription());
        return genreRepository.save(genre);
    }

    // Delete a genre
    public void deleteGenre(Long id) {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }
}
