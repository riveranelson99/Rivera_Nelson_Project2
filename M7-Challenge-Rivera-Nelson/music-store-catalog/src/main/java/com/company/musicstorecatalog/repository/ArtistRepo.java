package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
}
