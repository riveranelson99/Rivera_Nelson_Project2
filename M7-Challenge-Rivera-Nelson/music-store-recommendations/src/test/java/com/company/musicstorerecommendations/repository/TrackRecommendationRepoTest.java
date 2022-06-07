package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRecommendationRepoTest {

    private TrackRecommendation trackReco;

    @Autowired
    TrackRecommendationRepo repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        trackReco = new TrackRecommendation();
        trackReco.setTrackId(1);
        trackReco.setUserId(2);
        trackReco.setLiked(false);
        repo.save(trackReco);
    }

    @Test
    public void shouldGetAllTrackRecommendations() throws Exception {
        List<TrackRecommendation> tList = repo.findAll();
        assertEquals(1, tList.size());
    }

    @Test
    public void shouldGetOneTrackRecommendation() throws Exception {
        Optional<TrackRecommendation> trackReco1 = repo.findById(trackReco.getTrackRecommendationId());
        assertEquals(trackReco1.get(), trackReco);
    }

    @Test
    public void shouldUpdateTrackRecommendation() throws Exception {
        TrackRecommendation oldReco = new TrackRecommendation();
        oldReco.setTrackId(1);
        oldReco.setUserId(2);
        oldReco.setLiked(true);
        repo.save(oldReco);

        oldReco.setLiked(false);
        repo.save(oldReco);

        Optional<TrackRecommendation> compare = repo.findById(oldReco.getTrackRecommendationId());
        assertEquals(compare.get(), oldReco);
    }

    @Test
    public void shouldDeleteTrackRecommendation() throws Exception {
        Optional<TrackRecommendation> trackReco1 = repo.findById(trackReco.getTrackRecommendationId());

        assertEquals(trackReco1.get(), trackReco);

        repo.deleteById(trackReco.getTrackRecommendationId());

        trackReco1 = repo.findById(trackReco.getTrackRecommendationId());

        assertFalse(trackReco1.isPresent());
    }
}