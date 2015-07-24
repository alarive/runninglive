package com.runninglive.competition;

import com.runninglive.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * User: alarive
 */
@Component
@RepositoryEventHandler(Competition.class)
public class CompetitionEventHandler {

    @Autowired
    UserRepository userRepository;


    @HandleBeforeCreate
    public void setOrganizerBeforeCreate(Competition competition) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        competition.setOrganizer(userRepository.findByUsername(principal));
    }
}
