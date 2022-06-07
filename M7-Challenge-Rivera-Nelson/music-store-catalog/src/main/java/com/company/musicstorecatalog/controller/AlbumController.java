package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.exception.BadAlbumException;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    MusicStoreCatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumViewModel createAnAlbum(@RequestBody @Valid AlbumViewModel albumViewModel) {
        albumViewModel = service.createAlbum(albumViewModel);

        return albumViewModel;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumViewModel getAlbum(@PathVariable("id") long albumId) {
        AlbumViewModel albumViewModel = service.getAlbum(albumId);

        if (albumViewModel == null) {
            throw new BadAlbumException("Id given does not match any within our database.");
        }
        return albumViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumViewModel> getAllAlbums() {
        List<AlbumViewModel> albumViewModelList = service.getAllAlbums();

        return albumViewModelList;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnAlbum(@RequestBody @Valid AlbumViewModel albumViewModel) {
        if (albumViewModel.getId() < 1) {
            throw new BadAlbumException("Id given does not match any in our database.");
        }
        service.updateAlbum(albumViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnAlbum(@PathVariable("id") long albumId) {
        service.deleteAlbum(albumId);
    }
}
