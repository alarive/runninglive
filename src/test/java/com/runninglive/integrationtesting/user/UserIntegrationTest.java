package com.runninglive.integrationtesting.user;

import com.jayway.restassured.RestAssured;
import com.runninglive.RunningLiveApplication;
import com.runninglive.competition.CompetitionRepository;
import com.runninglive.user.Role;
import com.runninglive.user.RoleRepository;
import com.runninglive.user.User;
import com.runninglive.user.UserRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.junit.Assert.fail;

/**
 * User: alarive
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RunningLiveApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserIntegrationTest {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${local.server.port}")
    int port;

    private User antonin;
    private User louise;

    @Before
    public void setup() {
        final Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        final Role roleOrganizer = roleRepository.findByRoleName("ROLE_ORGANIZER");
        final Role rolerunner = roleRepository.findByRoleName("ROLE_RUNNER");
        antonin = new User("antonin", "4nt0n1n", 180, new HashSet<Role>(){{
            add(roleUser);
            add(rolerunner);
        }});
        louise = new User("louise", "l0u1s3", 170, new HashSet<Role>(){{
            add(roleUser);
            add(roleOrganizer);
        }});
        competitionRepository.deleteAll();
        userRepository.deleteAll();
        userRepository.save(antonin);
        userRepository.save(louise);

        RestAssured.port = port;
    }

    @Test
    public void testRunnerCanConnect() {
        given().auth().basic(antonin.getUsername(), antonin.getPassword()).
        when().
                get("/users/{id}", antonin.getId()).
        then().
                statusCode(HttpStatus.SC_OK).
                body("username", Matchers.is(antonin.getUsername()));
    }

    @Test
    public void testOrganizerCanConnect() {
        given().auth().basic(louise.getUsername(), louise.getPassword()).
        when().
                get("/users/{id}", louise.getId()).
                then().
                statusCode(HttpStatus.SC_OK).
                body("username", Matchers.is(louise.getUsername()));
    }

    @Test
    public void testRolesAreNotExported() {
        given().auth().basic(antonin.getUsername(), antonin.getPassword()).
        when().
                get("/roles").
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
