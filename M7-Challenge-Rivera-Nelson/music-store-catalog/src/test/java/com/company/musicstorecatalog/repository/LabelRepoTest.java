package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Label;
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
public class LabelRepoTest {

    private Label label;
    private Label label2;

    @Autowired
    LabelRepo labelRepo;
    @Autowired
    AlbumRepo albumRepo;

    @Before
    public void setUp() throws Exception {
        albumRepo.deleteAll();
        labelRepo.deleteAll();

        label = new Label();
        label.setName("Hollywood Records");
        label.setWebsite("www.hollywoodrecords.com");
        labelRepo.save(label);

        label2 = new Label();
        label2.setName("Sony Music Entertainment");
        label2.setWebsite("www.sonymusic.com");
        labelRepo.save(label2);
    }

    @Test
    public void shouldGetAllLabels() throws Exception {
        List<Label> lList = labelRepo.findAll();
        assertEquals(2, lList.size());
    }

    @Test
    public void shouldGetOneLabel() throws Exception {
        Optional<Label> label1 = labelRepo.findById(label.getLabelId());
        assertEquals(label1.get(), label);
    }

    @Test
    public void shouldUpdateALabel() throws Exception {
        Label oldLabel = new Label();
        oldLabel.setName("Universal Music Publishing Group");
        oldLabel.setWebsite("www.umusicpub.com");
        labelRepo.save(oldLabel);

        oldLabel.setName("Warner Music Group");
        oldLabel.setWebsite("www.wmg.com");
        labelRepo.save(oldLabel);

        Optional<Label> compare = labelRepo.findById(oldLabel.getLabelId());
        assertEquals(compare.get(), oldLabel);
    }

    @Test
    public void shouldDeleteALabel() throws Exception {
        Optional<Label> label1 = labelRepo.findById(label.getLabelId());

        assertEquals(label1.get(), label);

        labelRepo.deleteById(label.getLabelId());

        label1 = labelRepo.findById(label.getLabelId());

        assertFalse(label1.isPresent());
    }
}