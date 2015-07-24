package com.runninglive.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> { }
