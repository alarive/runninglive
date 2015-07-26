package com.runninglive.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Override
    @PreAuthorize("#competition.organizer.id == authentication.principal.id")
    <C extends Competition> C save(@Param("competition") C competition);

}
