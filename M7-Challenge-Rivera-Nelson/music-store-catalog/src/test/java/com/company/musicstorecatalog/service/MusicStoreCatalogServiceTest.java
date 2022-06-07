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
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class MusicStoreCatalogServiceTest {

    AlbumRepo albumRepo;
    ArtistRepo artistRepo;
    LabelRepo labelRepo;
    TrackRepo trackRepo;
    MusicStoreCatalogService service;


    @Before
    public void setUp() throws Exception {
        setUpArtistRepoMock();
        setUpLabelRepoMock();
        setUpTrackRepoMock();
        setUpAlbumRepoMock();

        service = new MusicStoreCatalogService(albumRepo, artistRepo, labelRepo, trackRepo);
    }

    // ---------- Album Service Tests ----------
    @Test
    public void shouldCreateAlbumViewModel() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        AlbumViewModel avmInput = new AlbumViewModel();
        avmInput.setTitle("Jazz");
        avmInput.setArtist(artist);
        avmInput.setReleaseDate(LocalDate.of(1978, 11, 10));
        avmInput.setLabel(label);
        avmInput.setListPrice(new BigDecimal("23.99"));

        AlbumViewModel avmOutput = new AlbumViewModel();
        avmOutput.setId(10);
        avmOutput.setTitle("Jazz");
        avmOutput.setArtist(artist);
        avmOutput.setReleaseDate(LocalDate.of(1978, 11, 10));
        avmOutput.setLabel(label);
        avmOutput.setListPrice(new BigDecimal("23.99"));

        AlbumViewModel savedAvm = service.createAlbum(avmInput);
        assertEquals(avmInput, savedAvm);
    }

    @Test
    public void shouldReturnOneAlbum() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        AlbumViewModel expectedAvm = new AlbumViewModel();
        expectedAvm.setId(10);
        expectedAvm.setTitle("Jazz");
        expectedAvm.setArtist(artist);
        expectedAvm.setReleaseDate(LocalDate.of(1978, 11, 10));
        expectedAvm.setLabel(label);
        expectedAvm.setListPrice(new BigDecimal("23.99"));

        AlbumViewModel searchedAvm = service.getAlbum(10);
        assertEquals(expectedAvm, searchedAvm);
    }

    @Test
    public void shouldReturnAllAlbums() throws Exception {
        List<AlbumViewModel> foundAlbums = service.getAllAlbums();

        assertEquals(1, foundAlbums.size());
    }

    @Test
    public void shouldUpdateAnAlbum() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        AlbumViewModel avmInput = new AlbumViewModel();
        avmInput.setTitle("Jazz");
        avmInput.setArtist(artist);
        avmInput.setReleaseDate(LocalDate.of(1978, 11, 10));
        avmInput.setLabel(label);
        avmInput.setListPrice(new BigDecimal("23.99"));
        service.createAlbum(avmInput);

        avmInput.setId(10);
        avmInput.setTitle("A Night At The Opera");
        avmInput.setArtist(artist);
        avmInput.setReleaseDate(LocalDate.of(1975, 11, 21));
        avmInput.setLabel(label);
        avmInput.setListPrice(new BigDecimal("15.99"));
        service.updateAlbum(avmInput);

        AlbumViewModel avmOutput = new AlbumViewModel();
        avmOutput.setId(10);
        avmOutput.setTitle("A Night At The Opera");
        avmOutput.setArtist(artist);
        avmOutput.setReleaseDate(LocalDate.of(1975, 11, 21));
        avmOutput.setLabel(label);
        avmOutput.setListPrice(new BigDecimal("15.99"));

        assertEquals(avmInput, avmOutput);
    }

    // ---------- Artist Service Tests ----------
    @Test
    public void shouldCreateArtistViewModel() throws Exception {
        ArtistViewModel inputAvm = new ArtistViewModel();
        inputAvm.setName("Queen");
        inputAvm.setInstagram("@officialqueenmusic");
        inputAvm.setTwitter("@QueenWillRock");

        ArtistViewModel outputAvm = new ArtistViewModel();
        outputAvm.setId(20);
        outputAvm.setName("Queen");
        outputAvm.setInstagram("@officialqueenmusic");
        outputAvm.setTwitter("@QueenWillRock");

        ArtistViewModel savedAvm = service.createArtist(inputAvm);
        assertEquals(savedAvm, outputAvm);
    }

    @Test
    public void shouldReturnOneArtist() throws Exception {
        ArtistViewModel inputAvm = new ArtistViewModel();
        inputAvm.setName("Queen");
        inputAvm.setInstagram("@officialqueenmusic");
        inputAvm.setTwitter("@QueenWillRock");

        ArtistViewModel outputAvm = new ArtistViewModel();
        outputAvm.setId(20);
        outputAvm.setName("Queen");
        outputAvm.setInstagram("@officialqueenmusic");
        outputAvm.setTwitter("@QueenWillRock");

        ArtistViewModel searchedAvm = service.getArtist(20);
        assertEquals(searchedAvm, outputAvm);
    }

    @Test
    public void shouldReturnAllArtists() throws Exception {
        List<ArtistViewModel> foundArtists = service.getAllArtists();

        assertEquals(1, foundArtists.size());
    }

    @Test
    public void shouldUpdateAnArtist() throws Exception {
        ArtistViewModel inputAvm = new ArtistViewModel();
        inputAvm.setName("Queen");
        inputAvm.setInstagram("@officialqueenmusic");
        inputAvm.setTwitter("@QueenWillRock");
        service.createArtist(inputAvm);

        inputAvm.setId(20);
        inputAvm.setName("Metallica");
        inputAvm.setInstagram("@metallica");
        inputAvm.setTwitter("@Metallica");
        service.updateArtist(inputAvm);

        ArtistViewModel outputAvm = new ArtistViewModel();
        outputAvm.setId(20);
        outputAvm.setName("Metallica");
        outputAvm.setInstagram("@metallica");
        outputAvm.setTwitter("@Metallica");

        assertEquals(inputAvm, outputAvm);
    }

    // ---------- Label Service Tests ----------
    @Test
    public void shouldCreateLabelViewModel() throws Exception {
        LabelViewModel inputLvm = new LabelViewModel();
        inputLvm.setName("Hollywood Records");
        inputLvm.setWebsite("www.hollywoodrecords.com");

        LabelViewModel outputLvm = new LabelViewModel();
        outputLvm.setId(30);
        outputLvm.setName("Hollywood Records");
        outputLvm.setWebsite("www.hollywoodrecords.com");

        LabelViewModel savedLvm = service.createLabel(inputLvm);
        assertEquals(savedLvm, outputLvm);
    }

    @Test
    public void shouldReturnOneLabel () throws Exception {
        LabelViewModel inputLvm = new LabelViewModel();
        inputLvm.setName("Hollywood Records");
        inputLvm.setWebsite("www.hollywoodrecords.com");

        LabelViewModel outputLvm = new LabelViewModel();
        outputLvm.setId(30);
        outputLvm.setName("Hollywood Records");
        outputLvm.setWebsite("www.hollywoodrecords.com");

        LabelViewModel searchedLvm = service.getLabel(30);
        assertEquals(searchedLvm, outputLvm);
    }

    @Test
    public void shouldReturnAllLabels() throws Exception {
        List<LabelViewModel> foundLabels = service.getAllLabels();
        assertEquals(1, foundLabels.size());
    }
    @Test
    public void shouldUpdateALabel() throws Exception {
        LabelViewModel inputLvm = new LabelViewModel();
        inputLvm.setName("Hollywood Records");
        inputLvm.setWebsite("www.hollywoodrecords.com");
        service.createLabel(inputLvm);

        inputLvm.setId(30);
        inputLvm.setName("Universal Music Publishing Group");
        inputLvm.setWebsite("www.umusicpub.com");
        service.updateLabel(inputLvm);

        LabelViewModel outputLvm = new LabelViewModel();
        outputLvm.setId(30);
        outputLvm.setName("Universal Music Publishing Group");
        outputLvm.setWebsite("www.umusicpub.com");

        assertEquals(inputLvm, outputLvm);
    }

    // ---------- Track Service Tests ----------
    @Test
    public void shouldCreateTrackViewModel() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        Album album = new Album();
        album.setAlbumId(10);
        album.setTitle("Jazz");
        album.setArtistId(artist.getArtistId());
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(label.getLabelId());
        album.setListPrice(new BigDecimal("23.99"));

        TrackViewModel inputTvm = new TrackViewModel();
        inputTvm.setAlbum(album);
        inputTvm.setTitle("Don't Stop Me Now");
        inputTvm.setRunTime(4);

        TrackViewModel outputTvm = new TrackViewModel();
        outputTvm.setId(40);
        outputTvm.setAlbum(album);
        outputTvm.setTitle("Don't Stop Me Now");
        outputTvm.setRunTime(4);

        TrackViewModel savedTvm = service.createTrack(inputTvm);
        assertEquals(savedTvm, outputTvm);
    }

    @Test
    public void shouldReturnOneTrack() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        Album album = new Album();
        album.setAlbumId(10);
        album.setTitle("Jazz");
        album.setArtistId(artist.getArtistId());
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(label.getLabelId());
        album.setListPrice(new BigDecimal("23.99"));

        TrackViewModel inputTvm = new TrackViewModel();
        inputTvm.setAlbum(album);
        inputTvm.setTitle("Don't Stop Me Now");
        inputTvm.setRunTime(4);

        TrackViewModel outputTvm = new TrackViewModel();
        outputTvm.setId(40);
        outputTvm.setAlbum(album);
        outputTvm.setTitle("Don't Stop Me Now");
        outputTvm.setRunTime(4);

        TrackViewModel searchedTvm = service.getTrack(40);
        assertEquals(searchedTvm, outputTvm);
    }

    @Test
    public void shouldReturnAllTracks() throws Exception {
        List<TrackViewModel> foundTracks = service.getAllTracks();
        assertEquals(1, foundTracks.size());
    }

    @Test
    public void shouldUpdateATrack() throws Exception {
        Artist artist = new Artist();
        artist.setArtistId(20);
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Label label = new Label();
        label.setLabelId(30);
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        Album album = new Album();
        album.setAlbumId(10);
        album.setTitle("Jazz");
        album.setArtistId(artist.getArtistId());
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(label.getLabelId());
        album.setListPrice(new BigDecimal("23.99"));

        TrackViewModel inputTvm = new TrackViewModel();
        inputTvm.setAlbum(album);
        inputTvm.setTitle("Don't Stop Me Now");
        inputTvm.setRunTime(4);
        service.createTrack(inputTvm);

        inputTvm.setId(40);
        inputTvm.setAlbum(album);
        inputTvm.setTitle("Bicycle Race");
        inputTvm.setRunTime(3);
        service.updateTrack(inputTvm);

        TrackViewModel outputTvm = new TrackViewModel();
        outputTvm.setId(40);
        outputTvm.setAlbum(album);
        outputTvm.setTitle("Bicycle Race");
        outputTvm.setRunTime(3);

        assertEquals(inputTvm, outputTvm);
    }

    // ---------- Mock Set Up ----------
    private void setUpAlbumRepoMock() {
        albumRepo = mock(AlbumRepo.class);
        Album album = new Album();
        album.setTitle("Jazz");
        album.setArtistId(20);
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(30);
        album.setListPrice(new BigDecimal("23.99"));

        Album album2 = new Album();
        album2.setAlbumId(10);
        album2.setTitle("Jazz");
        album2.setArtistId(20);
        album2.setReleaseDate(LocalDate.of(1978, 11, 10));
        album2.setLabelId(30);
        album2.setListPrice(new BigDecimal("23.99"));

        List<Album> aList =new ArrayList<>();
        aList.add(album);

        doReturn(album2).when(albumRepo).save(album);
        doReturn(Optional.of(album2)).when(albumRepo).findById(10L);
        doReturn(aList).when(albumRepo).findAll();
    }

    private void setUpArtistRepoMock() {
        artistRepo = mock(ArtistRepo.class);
        Artist artist = new Artist();
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");

        Artist artist2 = new Artist();
        artist2.setArtistId(20);
        artist2.setName("Queen");
        artist2.setInstagram("@officialqueenmusic");
        artist2.setTwitter("@QueenWillRock");

        List<Artist> artList = new ArrayList<>();
        artList.add(artist);

        doReturn(artist2).when(artistRepo).save(artist);
        doReturn(Optional.of(artist2)).when(artistRepo).findById(20L);
        doReturn(artList).when(artistRepo).findAll();
    }

    private void setUpLabelRepoMock() {
        labelRepo = mock(LabelRepo.class);
        Label label = new Label();
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");

        Label label2 = new Label();
        label2.setLabelId(30);
        label2.setName("Hollywood Records");
        label2.setWebsite("www.hollywoodrecords.com");

        List<Label> lList = new ArrayList<>();
        lList.add(label);

        doReturn(label2).when(labelRepo).save(label);
        doReturn(Optional.of(label2)).when(labelRepo).findById(30L);
        doReturn(lList).when(labelRepo).findAll();
    }

    private void setUpTrackRepoMock() {
        trackRepo = mock(TrackRepo.class);
        Track track = new Track();
        track.setAlbumId(10);
        track.setTitle("Don't Stop Me Now");
        track.setRunTime(4);

        Track track2 = new Track();
        track2.setTrackId(40);
        track2.setAlbumId(10);
        track2.setTitle("Don't Stop Me Now");
        track2.setRunTime(4);

        List<Track> tList = new ArrayList<>();
        tList.add(track);

        doReturn(track2).when(trackRepo).save(track);
        doReturn(Optional.of(track2)).when(trackRepo).findById(40L);
        doReturn(tList).when(trackRepo).findAll();
    }
}