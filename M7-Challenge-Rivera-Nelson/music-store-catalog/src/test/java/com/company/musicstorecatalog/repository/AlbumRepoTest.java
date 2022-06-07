package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Album;
import com.company.musicstorecatalog.models.Artist;
import com.company.musicstorecatalog.models.Label;
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
public class AlbumRepoTest {

    private Artist artist;
    private Label label;
    private Album album;
    private Album album2;

    @Autowired
    ArtistRepo artistRepo;
    @Autowired
    LabelRepo labelRepo;
    @Autowired
    AlbumRepo albumRepo;

    @Before
    public void setUp() throws Exception {
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
        album.setTitle("This album");
        album.setArtistId(artist.getArtistId());
        album.setReleaseDate(LocalDate.of(1978, 11, 10));
        album.setLabelId(label.getLabelId());
        album.setListPrice(new BigDecimal("23.99"));
        albumRepo.save(album);

        album2 = new Album();
        album2.setTitle("That Album");
        album2.setArtistId(artist.getArtistId());
        album2.setReleaseDate(LocalDate.of(2020, 7, 16));
        album2.setLabelId(label.getLabelId());
        album2.setListPrice(new BigDecimal("5.99"));
        albumRepo.save(album2);
    }

    @Test
    public void shouldGetAllAlbums() throws Exception {
        List<Album> aList = albumRepo.findAll();
        assertEquals(2, aList.size());
    }

    @Test
    public void shouldGetOneAlbum() throws Exception {
        Optional<Album> album1 = albumRepo.findById(album.getAlbumId());
        assertEquals(album1.get(), album);
    }

    @Test
    public void shouldUpdateAnAlbum() throws Exception {
        Album oldAlbum = new Album();
        oldAlbum.setTitle("Old album");
        oldAlbum.setArtistId(artist.getArtistId());
        oldAlbum.setReleaseDate(LocalDate.of(2022, 6, 6));
        oldAlbum.setLabelId(label.getLabelId());
        oldAlbum.setListPrice(new BigDecimal("11.99"));
        albumRepo.save(oldAlbum);

        oldAlbum.setTitle("new album");
        oldAlbum.setArtistId(artist.getArtistId());
        oldAlbum.setReleaseDate(LocalDate.of(2022, 7, 7));
        oldAlbum.setLabelId(label.getLabelId());
        oldAlbum.setListPrice(new BigDecimal("12.99"));
        albumRepo.save(oldAlbum);

        Optional<Album> compare = albumRepo.findById(oldAlbum.getAlbumId());
        assertEquals(compare.get(), oldAlbum);
    }

    @Test
    public void shouldDeleteAnAlbum() throws Exception {
        Optional<Album> album1 = albumRepo.findById(album.getAlbumId());

        assertEquals(album1.get(), album);

        albumRepo.deleteById(album.getAlbumId());

        album1 = albumRepo.findById(album.getAlbumId());

        assertFalse(album1.isPresent());
    }
}