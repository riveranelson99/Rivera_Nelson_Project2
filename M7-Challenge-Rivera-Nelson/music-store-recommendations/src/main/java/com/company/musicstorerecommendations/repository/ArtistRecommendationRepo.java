package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRecommendationRepo extends JpaRepository<ArtistRecommendation, Long> {
}
