package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.exception.BadLabelException;
import com.company.musicstorerecommendations.model.LabelRecommendation;
import com.company.musicstorerecommendations.repository.LabelRecommendationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labelRecommendation")
public class LabelRecommendationController {

    @Autowired
    LabelRecommendationRepo repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createALabelRecommendation(@RequestBody @Valid LabelRecommendation labelRec) {
        return repo.save(labelRec);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LabelRecommendation> getALabelRecommendation(@PathVariable("id") long labelRecId) {
        Optional<LabelRecommendation> labelRec = repo.findById(labelRecId);

        if (labelRec.isPresent() == false) {
            throw new BadLabelException("No label recommendation found using that id.");
        }
        return labelRec;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendations() {
        List<LabelRecommendation> labelRec = repo.findAll();

        return labelRec;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateALabelRecommendation(@RequestBody @Valid LabelRecommendation labelRec) {
        if (labelRec.getLabelRecommendationId() < 1) {
            throw new BadLabelException("Id given does not match any in our database.");
        }
        repo.save(labelRec);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteALabelRecommendation (@PathVariable("id") long labelRecId) {
        repo.deleteById(labelRecId);
    }
}
