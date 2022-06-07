package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepoTest {

    private AlbumRecommendation albumReco;

    @Autowired
    AlbumRecommendationRepo repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        albumReco = new AlbumRecommendation();
        albumReco.setAlbumId(1);
        albumReco.setUserId(2);
        albumReco.setLiked(false);
        repo.save(albumReco);
    }

    @Test
    public void shouldGetAllAlbumRecommendations() throws Exception {
        List<AlbumRecommendation> aList = repo.findAll();
        assertEquals(1, aList.size());
    }

    @Test
    public void shouldGetOneAlbumRecommendation() throws Exception {
        Optional<AlbumRecommendation> albumReco1 = repo.findById(albumReco.getAlbumRecommendationId());
        assertEquals(albumReco1.get(), albumReco);
    }

    @Test
    public void shouldUpdateAlbumRecommendation() throws Exception {
        AlbumRecommendation oldReco = new AlbumRecommendation();
        oldReco.setAlbumId(1);
        oldReco.setUserId(2);
        oldReco.setLiked(true);
        repo.save(oldReco);

        oldReco.setLiked(false);
        repo.save(oldReco);

        Optional<AlbumRecommendation> compare = repo.findById(oldReco.getAlbumRecommendationId());
        assertEquals(compare.get(), oldReco);
    }

    @Test
    public void shouldDeleteAlbumRecommendation() throws Exception {
        Optional<AlbumRecommendation> albumReco1 = repo.findById(albumReco.getAlbumRecommendationId());

        assertEquals(albumReco1.get(), albumReco);

        repo.deleteById(albumReco.getAlbumRecommendationId());

        albumReco1 = repo.findById(albumReco.getAlbumRecommendationId());

        assertFalse(albumReco1.isPresent());
    }
}