package com.company.musicstorecatalog.repository;

import com.company.musicstorecatalog.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepo extends JpaRepository<Label, Long> {
}
