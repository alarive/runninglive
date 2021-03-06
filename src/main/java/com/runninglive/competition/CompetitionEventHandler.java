package com.runninglive.competition;

import com.runninglive.security.RunningLiveUserDetailsService;
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
    private UserRepository userRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @HandleBeforeCreate
    public void setOrganizerBeforeCreate(Competition competition) {
        Long id = ((RunningLiveUserDetailsService.RunningLiveUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        competition.setOrganizer(userRepository.findOne(id));
    }
}
