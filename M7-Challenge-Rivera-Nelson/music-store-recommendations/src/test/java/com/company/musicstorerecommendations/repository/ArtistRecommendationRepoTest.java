package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.ArtistRecommendation;
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
public class ArtistRecommendationRepoTest {

    private ArtistRecommendation artistReco;

    @Autowired
    ArtistRecommendationRepo repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        artistReco = new ArtistRecommendation();
        artistReco.setArtistId(1);
        artistReco.setUserId(2);
        artistReco.setLiked(false);
        repo.save(artistReco);
    }

    @Test
    public void shouldGetAllArtistRecommendations() throws Exception {
        List<ArtistRecommendation> aList = repo.findAll();
        assertEquals(1, aList.size());
    }

    @Test
    public void shouldGetOneArtistRecommendation() throws Exception {
        Optional<ArtistRecommendation> artistReco1 = repo.findById(artistReco.getArtistRecommendationId());
        assertEquals(artistReco1.get(), artistReco);
    }

    @Test
    public void shouldUpdateArtistRecommendation() throws Exception {
        ArtistRecommendation oldReco = new ArtistRecommendation();
        oldReco.setArtistId(1);
        oldReco.setUserId(2);
        oldReco.setLiked(true);
        repo.save(oldReco);

        oldReco.setLiked(false);
        repo.save(oldReco);

        Optional<ArtistRecommendation> compare = repo.findById(oldReco.getArtistRecommendationId());
        assertEquals(compare.get(), oldReco);
    }

    @Test
    public void shouldDeleteArtistRecommendation() throws Exception {
        Optional<ArtistRecommendation> artistReco1 = repo.findById(artistReco.getArtistRecommendationId());

        assertEquals(artistReco1.get(), artistReco);

        repo.deleteById(artistReco.getArtistRecommendationId());

        artistReco1 = repo.findById(artistReco.getArtistRecommendationId());

        assertFalse(artistReco1.isPresent());
    }
}