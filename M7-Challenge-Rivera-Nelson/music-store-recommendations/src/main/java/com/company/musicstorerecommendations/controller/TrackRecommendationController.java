package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.exception.BadTrackException;
import com.company.musicstorerecommendations.model.TrackRecommendation;
import com.company.musicstorerecommendations.repository.TrackRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trackRecommendation")
public class TrackRecommendationController {

    @Autowired
    TrackRecommendationRepo repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createATrackRecommendation(@RequestBody @Valid TrackRecommendation trackRec) {
        return repo.save(trackRec);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TrackRecommendation> getATrackRecommendation(@PathVariable("id") long trackRecId) {
        Optional<TrackRecommendation> trackRec = repo.findById(trackRecId);

        if (trackRec.isPresent() == false) {
            throw new BadTrackException("No track recommendation found using that id.");
        }
        return trackRec;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendations() {
        List<TrackRecommendation> trackRec = repo.findAll();

        return trackRec;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateATrackRecommendation(@RequestBody @Valid TrackRecommendation trackRec) {
        if (trackRec.getTrackRecommendationId() < 1) {
            throw new BadTrackException("Id given does not match any in our database.");
        }
        repo.save(trackRec);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteATrackRecommendation (@PathVariable("id") long trackRecId) {
        repo.deleteById(trackRecId);
    }
}
