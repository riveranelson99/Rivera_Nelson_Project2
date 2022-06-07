package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends JpaRepository<Track, Long> {
}
