package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.exception.BadLabelException;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.LabelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    MusicStoreCatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelViewModel createALabel(@RequestBody @Valid LabelViewModel labelViewModel) {
        labelViewModel = service.createLabel(labelViewModel);

        return labelViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelViewModel getLabel(@PathVariable("id") long labelId) {
        LabelViewModel labelViewModel = service.getLabel(labelId);

        if (labelViewModel == null) {
            throw new BadLabelException("Id given does not match any within our database.");
        }
        return labelViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelViewModel> getAllLabels() {
        List<LabelViewModel> labelViewModelList = service.getAllLabels();

        return labelViewModelList;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateALabel(@RequestBody @Valid LabelViewModel labelViewModel) {
        if (labelViewModel.getId() < 1) {
            throw new BadLabelException("Id given does not match any in our database.");
        }

        service.updateLabel(labelViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteALabel(@PathVariable("id") long labelId) {
        service.deleteLabel(labelId);
    }
}
