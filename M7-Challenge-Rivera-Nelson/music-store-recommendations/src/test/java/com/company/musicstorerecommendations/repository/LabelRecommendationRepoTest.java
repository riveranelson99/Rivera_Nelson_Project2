package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.LabelRecommendation;
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
public class LabelRecommendationRepoTest {

    private LabelRecommendation labelReco;

    @Autowired
    LabelRecommendationRepo repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();

        labelReco = new LabelRecommendation();
        labelReco.setLabelId(1);
        labelReco.setUserId(2);
        labelReco.setLiked(false);
        repo.save(labelReco);
    }

    @Test
    public void shouldGetAllLabelRecommendations() throws Exception {
        List<LabelRecommendation> lList = repo.findAll();
        assertEquals(1, lList.size());
    }

    @Test
    public void shouldGetOneLabelRecommendation() throws Exception {
        Optional<LabelRecommendation> labelReco1 = repo.findById(labelReco.getLabelRecommendationId());
        assertEquals(labelReco1.get(), labelReco);
    }

    @Test
    public void shouldUpdateLabelRecommendation() throws Exception {
        LabelRecommendation oldReco = new LabelRecommendation();
        oldReco.setLabelId(1);
        oldReco.setUserId(2);
        oldReco.setLiked(true);
        repo.save(oldReco);

        oldReco.setLiked(false);
        repo.save(oldReco);

        Optional<LabelRecommendation> compare = repo.findById(oldReco.getLabelRecommendationId());
        assertEquals(compare.get(), oldReco);
    }

    @Test
    public void shouldDeleteLabelRecommendation() throws Exception {
        Optional<LabelRecommendation> labelReco1 = repo.findById(labelReco.getLabelRecommendationId());

        assertEquals(labelReco1.get(), labelReco);

        repo.deleteById(labelReco.getLabelRecommendationId());

        labelReco1 = repo.findById(labelReco.getLabelRecommendationId());

        assertFalse(labelReco1.isPresent());
    }
}