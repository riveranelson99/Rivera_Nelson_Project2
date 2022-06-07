package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.exception.BadTrackException;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.TrackViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    MusicStoreCatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackViewModel createTrack(@RequestBody @Valid TrackViewModel trackViewModel) {
        trackViewModel = service.createTrack(trackViewModel);

        return trackViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackViewModel getTrack(@PathVariable("id") long trackId) {
        TrackViewModel trackViewModel = service.getTrack(trackId);

        if (trackViewModel == null) {
            throw new BadTrackException("Id given does not match any within our database.");
        }
        return trackViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackViewModel> getAllTracks() {
        List<TrackViewModel> trackViewModelList = service.getAllTracks();

        return trackViewModelList;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateATrack(@RequestBody @Valid TrackViewModel trackViewModel){
        if (trackViewModel.getId() < 1) {
            throw new BadTrackException("Id given does not match any in our database.");
        }

        service.updateTrack(trackViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteATrack(@PathVariable("id") long trackId) {
        service.deleteTrack(trackId);
    }
}
