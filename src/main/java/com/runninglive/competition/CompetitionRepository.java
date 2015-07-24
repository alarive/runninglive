package com.runninglive.competition;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * User: alarive
 */
@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> { }
