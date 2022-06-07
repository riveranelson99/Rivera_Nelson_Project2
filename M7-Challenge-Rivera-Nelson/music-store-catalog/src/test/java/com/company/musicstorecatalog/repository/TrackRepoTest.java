package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Album;
import com.company.musicstorecatalog.models.Artist;
import com.company.musicstorecatalog.models.Label;
import com.company.musicstorecatalog.models.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepoTest {

    private Track track;
    private Track track2;
    private Album album;
    private Artist artist;
    private Label label;

    @Autowired
    TrackRepo trackRepo;
    @Autowired
    ArtistRepo artistRepo;
    @Autowired
    LabelRepo labelRepo;
    @Autowired
    AlbumRepo albumRepo;

    @Before
    public void setUp() throws Exception {
        trackRepo.deleteAll();
        albumRepo.deleteAll();
        artistRepo.deleteAll();
        labelRepo.deleteAll();

        artist = new Artist();
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");
        artistRepo.save(artist);

        label = new Label();
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");
        labelRepo.save(label);

        album = new Album();
        album.setTitle("Jazz");
        album.setArtistId(artist.getArtistId());
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(label.getLabelId());
        album.setListPrice(new BigDecimal("23.99"));
        albumRepo.save(album);

        track = new Track();
        track.setAlbumId(album.getAlbumId());
        track.setTitle("Don't Stop Me Now.");
        track.setRunTime(4);
        trackRepo.save(track);

        track2 = new Track();
        track2.setAlbumId(album.getAlbumId());
        track2.setTitle("Bicycle Race");
        track2.setRunTime(3);
        trackRepo.save(track2);
    }

    @Test
    public void shouldGetAllTracks() throws Exception {
        List<Track> tList = trackRepo.findAll();
        assertEquals(2, tList.size());
    }

    @Test
    public void shouldGetOneTrack() throws Exception {
        Optional<Track> track1 = trackRepo.findById(track.getTrackId());
        assertEquals(track1.get(), track);
    }

    @Test
    public void shouldUpdateATrack() throws Exception {
        Track oldTrack = new Track();
        oldTrack.setAlbumId(album.getAlbumId());
        oldTrack.setTitle("Jealousy");
        oldTrack.setRunTime(3);
        trackRepo.save(oldTrack);

        oldTrack.setAlbumId(album.getAlbumId());
        oldTrack.setTitle("Let Me Entertain You");
        oldTrack.setRunTime(4);
        trackRepo.save(oldTrack);

        Optional<Track> compare = trackRepo.findById(oldTrack.getTrackId());
        assertEquals(compare.get(), oldTrack );
    }

    @Test
    public void shouldDeleteATrack() throws Exception {
        Optional<Track> track1 = trackRepo.findById(track.getTrackId());

        assertEquals(track1.get(), track);

        trackRepo.deleteById(track.getTrackId());

        track1 = trackRepo.findById(track.getTrackId());

        assertFalse(track1.isPresent());
    }
}