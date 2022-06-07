package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.exception.BadArtistException;
import com.company.musicstorerecommendations.model.ArtistRecommendation;
import com.company.musicstorerecommendations.repository.ArtistRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artistRecommendation")
public class ArtistRecommendationController {

    @Autowired
    ArtistRecommendationRepo repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createAnArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRec) {
        return repo.save(artistRec);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ArtistRecommendation> getAnArtistRecommendation(@PathVariable("id") long artistRecId) {
        Optional<ArtistRecommendation> artistRec = repo.findById(artistRecId);

        if (artistRec.isPresent() == false) {
            throw new BadArtistException("No artist recommendation found using that id.");
        }
        return artistRec;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        List<ArtistRecommendation> artistRec = repo.findAll();

        return artistRec;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRec) {
        if (artistRec.getArtistRecommendationId() < 1) {
            throw new BadArtistException("Id given does not match any in our database.");
        }
        repo.save(artistRec);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnArtistRecommendation (@PathVariable("id") long artistRecId) {
        repo.deleteById(artistRecId);
    }
}
