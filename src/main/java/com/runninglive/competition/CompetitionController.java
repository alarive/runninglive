package com.runninglive.competition;

import com.runninglive.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: alarive
 */
@RestController
public class CompetitionController {
    private final CompetitionRepository repository;

    @Autowired
    public CompetitionController(CompetitionRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/competitions")
    public Iterable<Competition> getCompetitions() {
        return repository.findAll();
    }
}
