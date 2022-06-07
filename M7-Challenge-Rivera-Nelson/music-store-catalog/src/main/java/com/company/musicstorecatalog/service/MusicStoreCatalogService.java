package com.company.musicstorecatalog.service;

import com.company.musicstorecatalog.models.Album;
import com.company.musicstorecatalog.models.Artist;
import com.company.musicstorecatalog.models.Label;
import com.company.musicstorecatalog.models.Track;
import com.company.musicstorecatalog.repository.AlbumRepo;
import com.company.musicstorecatalog.repository.ArtistRepo;
import com.company.musicstorecatalog.repository.LabelRepo;
import com.company.musicstorecatalog.repository.TrackRepo;
import com.company.musicstorecatalog.viewModel.AlbumViewModel;
import com.company.musicstorecatalog.viewModel.ArtistViewModel;
import com.company.musicstorecatalog.viewModel.LabelViewModel;
import com.company.musicstorecatalog.viewModel.TrackViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MusicStoreCatalogService {

    AlbumRepo albumRepo;
    ArtistRepo artistRepo;
    LabelRepo labelRepo;
    TrackRepo trackRepo;

    @Autowired
    public MusicStoreCatalogService(AlbumRepo albumRepo, ArtistRepo artistRepo,
                                    LabelRepo labelRepo, TrackRepo trackRepo) {
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
        this.labelRepo = labelRepo;
        this.trackRepo = trackRepo;
    }

    // ---------- Albums ----------
    public AlbumViewModel createAlbum(AlbumViewModel albumViewModel) {
        Album album = new Album();
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId(albumViewModel.getArtist().getArtistId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setLabelId(albumViewModel.getLabel().getLabelId());
        album.setListPrice(albumViewModel.getListPrice());
        album = albumRepo.save(album);

        albumViewModel.setId(album.getAlbumId());

        return albumViewModel;
    }

    public AlbumViewModel getAlbum(long id) {
        Optional<Album> album = albumRepo.findById(id);

        return buildAlbumViewModel(album.get());
    }

    public List<AlbumViewModel> getAllAlbums() {
        List<Album> albumList = albumRepo.findAll();
        List<AlbumViewModel> albumViewModelList = new ArrayList<>();

        albumList.stream().forEach(a -> {
            albumViewModelList.add(buildAlbumViewModel(a));
        });

        return albumViewModelList;
    }

    public void updateAlbum(AlbumViewModel albumViewModel) {
        Album album = new Album();
        album.setAlbumId(albumViewModel.getId());
        album.setTitle(albumViewModel.getTitle());
        album.setArtistId(albumViewModel.getArtist().getArtistId());
        album.setReleaseDate(albumViewModel.getReleaseDate());
        album.setLabelId(albumViewModel.getLabel().getLabelId());
        album.setListPrice(albumViewModel.getListPrice());

        albumRepo.save(album);
    }

    public void deleteAlbum(long id) {
        albumRepo.deleteById(id);
    }

    // ---------- Artists ----------
    public ArtistViewModel createArtist(ArtistViewModel artistViewModel) {
        Artist artist = new Artist();
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());
        artist = artistRepo.save(artist);

        artistViewModel.setId(artist.getArtistId());

        return artistViewModel;
    }

    public ArtistViewModel getArtist(long id) {
        Optional<Artist> artist = artistRepo.findById(id);

        return buildArtistViewModel(artist.get());
    }

    public List<ArtistViewModel> getAllArtists() {
        List<Artist> artistList = artistRepo.findAll();
        List<ArtistViewModel> artistViewModelList = new ArrayList<>();

        artistList.stream().forEach(a -> {
            artistViewModelList.add(buildArtistViewModel(a));
        });

        return artistViewModelList;
    }

    public void updateArtist(ArtistViewModel artistViewModel) {
        Artist artist = new Artist();
        artist.setArtistId(artistViewModel.getId());
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        artistRepo.save(artist);
    }

    public void deleteArtist(long id) {
        artistRepo.deleteById(id);
    }

    // ---------- Labels ----------
    public LabelViewModel createLabel(LabelViewModel labelViewModel) {
        Label label = new Label();
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());
        label = labelRepo.save(label);

        labelViewModel.setId(label.getLabelId());

        return labelViewModel;
    }

    public LabelViewModel getLabel(long id) {
        Optional<Label> label = labelRepo.findById(id);

        return buildLabelViewModel(label.get());
    }

    public List<LabelViewModel> getAllLabels() {
        List<Label> labelList = labelRepo.findAll();
        List<LabelViewModel> labelViewModelList = new ArrayList<>();

        labelList.stream().forEach(l -> {
            labelViewModelList.add(buildLabelViewModel(l));
        });

        return labelViewModelList;
    }

    public void updateLabel(LabelViewModel labelViewModel) {
        Label label = new Label();
        label.setLabelId(labelViewModel.getId());
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());

        labelRepo.save(label);
    }

    public void deleteLabel(long id) {
        labelRepo.deleteById(id);
    }

    // ---------- Tracks ----------
    public TrackViewModel createTrack(TrackViewModel trackViewModel) {
        Track track = new Track();
        track.setAlbumId(trackViewModel.getAlbum().getAlbumId());
        track.setTitle(trackViewModel.getTitle());
        track.setRunTime(trackViewModel.getRunTime());
        track = trackRepo.save(track);

        trackViewModel.setId(track.getTrackId());

        return trackViewModel;
    }

    public TrackViewModel getTrack(long id) {
        Optional<Track> track = trackRepo.findById(id);

        return buildTrackViewModel(track.get());
    }

    public List<TrackViewModel> getAllTracks() {
        List<Track> trackList = trackRepo.findAll();
        List<TrackViewModel> trackViewModelList = new ArrayList<>();

        trackList.stream().forEach(t -> {
            trackViewModelList.add(buildTrackViewModel(t));
        });

        return trackViewModelList;
    }

    public void updateTrack(TrackViewModel trackViewModel) {
        Track track = new Track();
        track.setTrackId(trackViewModel.getId());
        track.setAlbumId(trackViewModel.getAlbum().getAlbumId());
        track.setTitle(trackViewModel.getTitle());
        track.setRunTime(trackViewModel.getRunTime());

        trackRepo.save(track);
    }

    public void deleteTrack(long id) {
        trackRepo.deleteById(id);
    }

    // ---------- Build view models ----------
    public AlbumViewModel buildAlbumViewModel(Album album) {
        Optional<Artist> artist = artistRepo.findById(album.getArtistId());
        Optional<Label> label = labelRepo.findById(album.getLabelId());

        AlbumViewModel albumViewModel = new AlbumViewModel();
        albumViewModel.setId(album.getAlbumId());
        albumViewModel.setTitle(album.getTitle());
        albumViewModel.setArtist(artist.get());
        albumViewModel.setReleaseDate(album.getReleaseDate());
        albumViewModel.setLabel(label.get());
        albumViewModel.setListPrice(album.getListPrice());

        return albumViewModel;
    }

    public ArtistViewModel buildArtistViewModel(Artist artist) {
        ArtistViewModel artistViewModel = new ArtistViewModel();
        artistViewModel.setId(artist.getArtistId());
        artistViewModel.setName(artist.getName());
        artistViewModel.setInstagram(artist.getInstagram());
        artistViewModel.setTwitter(artist.getTwitter());

        return artistViewModel;
    }

    public LabelViewModel buildLabelViewModel(Label label) {
        LabelViewModel labelViewModel = new LabelViewModel();
        labelViewModel.setId(label.getLabelId());
        labelViewModel.setName(label.getName());
        labelViewModel.setWebsite(label.getWebsite());

        return labelViewModel;
    }

    public TrackViewModel buildTrackViewModel(Track track) {
        Optional<Album> album = albumRepo.findById(track.getAlbumId());

        TrackViewModel trackViewModel = new TrackViewModel();
        trackViewModel.setId(track.getTrackId());
        trackViewModel.setAlbum(album.get());
        trackViewModel.setTitle(track.getTitle());
        trackViewModel.setRunTime(track.getRunTime());

        return trackViewModel;
    }
}
