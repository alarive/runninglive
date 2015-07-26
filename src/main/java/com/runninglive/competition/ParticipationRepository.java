package com.runninglive.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

/**
 * User: alarive
 */
@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Override
    <P extends Participation> P save(P participation);

}
