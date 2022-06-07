package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistRepoTest {

    private Artist artist;
    private Artist artist2;

    @Autowired
    ArtistRepo artistRepo;
    @Autowired
    AlbumRepo albumRepo;

    @Before
    public void setUp() throws Exception {
        albumRepo.deleteAll();
        artistRepo.deleteAll();

        artist = new Artist();
        artist.setName("Queen");
        artist.setInstagram("@officialqueenmusic");
        artist.setTwitter("@QueenWillRock");
        artistRepo.save(artist);

        artist2 = new Artist();
        artist2.setName("Metallica");
        artist2.setInstagram("@metallica");
        artist2.setTwitter("@Metallica");
        artistRepo.save(artist2);
    }

    @Test
    public void shouldGetAllArtists() throws Exception {
        List<Artist> aList = artistRepo.findAll();
        assertEquals(2, aList.size());
    }

    @Test
    public void shouldGetOneArtist() throws Exception {
        Optional<Artist> artist1 = artistRepo.findById(artist.getArtistId());
        assertEquals(artist1.get(), artist);
    }

    @Test
    public void shouldUpdateAnArtist() throws Exception {
        Artist oldArtist = new Artist();
        oldArtist.setName("Eminem");
        oldArtist.setInstagram("@eminem");
        oldArtist.setTwitter("@Eminem");
        artistRepo.save(oldArtist);

        oldArtist.setName("Daft Punk");
        oldArtist.setInstagram("@daftpunk");
        oldArtist.setTwitter("@daftpunk");
        artistRepo.save(oldArtist);

        Optional<Artist> compare = artistRepo.findById(oldArtist.getArtistId());
        assertEquals(compare.get(), oldArtist);
    }

    @Test
    public void shouldDeleteAnArtist() throws Exception {
        Optional<Artist> artist1 = artistRepo.findById(artist.getArtistId());

        assertEquals(artist1.get(), artist);

        artistRepo.deleteById(artist.getArtistId());

        artist1 = artistRepo.findById(artist.getArtistId());

        assertFalse(artist1.isPresent());
    }
}