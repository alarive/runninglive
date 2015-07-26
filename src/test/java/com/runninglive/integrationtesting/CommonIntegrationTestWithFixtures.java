package com.runninglive.integrationtesting;

import com.jayway.restassured.RestAssured;
import com.runninglive.RunningLiveApplication;
import com.runninglive.competition.Competition;
import com.runninglive.competition.CompetitionRepository;
import com.runninglive.user.RoleRepository;
import com.runninglive.user.User;
import com.runninglive.user.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * User: alarive
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RunningLiveApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:import-test-data.sql")
public class CommonIntegrationTestWithFixtures {
    @Autowired
    protected CompetitionRepository competitionRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    protected User organizerJessica;
    protected User runnerSahbi;
    protected Competition marathon;
    protected Competition frappadingue;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup() {
        organizerJessica = userRepository.findByUsername("jessica");
        runnerSahbi = userRepository.findByUsername("sahbi");
        marathon = competitionRepository.findOne(1L);
        frappadingue = competitionRepository.findOne(2L);
        RestAssured.port = port;
    }

}
