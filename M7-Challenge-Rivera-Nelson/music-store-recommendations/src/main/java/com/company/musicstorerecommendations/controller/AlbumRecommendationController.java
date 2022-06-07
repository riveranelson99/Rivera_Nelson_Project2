package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.exception.BadAlbumException;
import com.company.musicstorerecommendations.model.AlbumRecommendation;
import com.company.musicstorerecommendations.repository.AlbumRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albumRecommendation")
public class AlbumRecommendationController {

    @Autowired
    AlbumRecommendationRepo repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAnAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRec) {
        return repo.save(albumRec);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AlbumRecommendation> getAnAlbumRecommendation(@PathVariable("id") long albumRecId) {
        Optional<AlbumRecommendation> albumRec = repo.findById(albumRecId);

        if (albumRec.isPresent() == false) {
            throw new BadAlbumException("No album recommendation found using that id.");
        }
        return albumRec;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() {
        List<AlbumRecommendation> albumRec = repo.findAll();

        return albumRec;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRec) {
        if (albumRec.getAlbumRecommendationId() < 1) {
            throw new BadAlbumException("Id given does not match any in our database.");
        }
        repo.save(albumRec);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnAlbumRecommendation (@PathVariable("id") long albumRecId) {
        repo.deleteById(albumRecId);
    }
}
