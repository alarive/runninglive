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
@RepositoryEventHandler(Participation.class)
public class ParticipationEventHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParticipationRepository participationRepository;

    @HandleBeforeCreate
    public void setUserBeforeCreate(Participation participation) {
        Long id = ((RunningLiveUserDetailsService.RunningLiveUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        participation.setUser(userRepository.findOne(id));
    }
}
