package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.exception.BadArtistException;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.ArtistViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    MusicStoreCatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistViewModel createAnArtist(@RequestBody @Valid ArtistViewModel artistViewModel) {
        artistViewModel = service.createArtist(artistViewModel);

        return artistViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistViewModel getArtist(@PathVariable("id") long artistId) {
        ArtistViewModel artistViewModel = service.getArtist(artistId);

        if (artistViewModel == null) {
            throw new BadArtistException("Id given does not match any within our database.");
        }
        return artistViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistViewModel> getAllArtists() {
        List<ArtistViewModel> artistViewModelList = service.getAllArtists();

        return artistViewModelList;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnArtist(@RequestBody @Valid ArtistViewModel artistViewModel) {
        if (artistViewModel.getId() < 1) {
            throw new BadArtistException("Id given does not match any in our database.");
        }
        service.updateArtist(artistViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnArtist(@PathVariable("id") long artistId) {
        service.deleteArtist(artistId);
    }
}
