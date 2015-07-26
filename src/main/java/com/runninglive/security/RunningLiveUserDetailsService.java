package com.runninglive.security;

import com.runninglive.user.User;
import com.runninglive.user.UserRepository;
import com.runninglive.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: alarive
 */
@Service
public class RunningLiveUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return new RunningLiveUserDetails(user, authorities);
    }

    public class RunningLiveUserDetails extends org.springframework.security.core.userdetails.User {
        private Long id;

        public RunningLiveUserDetails(User user, List<GrantedAuthority> authorities) {
            super(user.getUsername(), user.getPassword(), authorities);
            this.id = user.getId();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
        }
        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
