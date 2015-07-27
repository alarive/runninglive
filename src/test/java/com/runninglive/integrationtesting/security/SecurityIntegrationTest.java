package com.runninglive.integrationtesting.security;

import com.runninglive.integrationtesting.CommonIntegrationTestWithFixtures;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: alarive
 */
public class SecurityIntegrationTest extends CommonIntegrationTestWithFixtures {

    @Test
    public void testMustLogin() {
        given().
                get("/users").
        then().
                statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void testUnauthorizedUserCannotGetHealthCheck() {
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
        when().
                get("/management/health").
        then().
                statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testOnlyOwnerCanUpdateItsCompetition() throws InterruptedException {
        Map<String, String> competition = new HashMap<String, String>();
        competition.put("name", "Marathon de Bordeaux");
        competition.put("place", "Bordeaux");
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                contentType("application/json").
                body(competition).
        when().
                put("/competitions/1").
        then().
                statusCode(HttpStatus.SC_FORBIDDEN);

        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
                contentType("application/json").
                body(competition).
        when().
                put("/competitions/1").
        then().
                statusCode(HttpStatus.SC_NO_CONTENT);

        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
        when().
                get("/competitions/1").
        then().
                statusCode(HttpStatus.SC_OK).
                body("place", is("Bordeaux"));

    }

    @Test
    public void testOnlyOwnerCanUpdateItsProfile() throws InterruptedException {
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("username", "sahbi");
        user.put("height", 200);
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
                contentType("application/json").
                body(user).
                when().
                put("/users/3").
                then().
                statusCode(HttpStatus.SC_FORBIDDEN);

        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                contentType("application/json").
                body(user).
                when().
                put("/users/3").
                then().
                statusCode(HttpStatus.SC_NO_CONTENT);

        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                when().
                get("/users/3").
                then().
                statusCode(HttpStatus.SC_OK).
                body("height", is(200));

    }

    @Test
    public void testOnlyOwnerCanUpdateItsTime() throws InterruptedException {
        Map<String, String> time = new HashMap<String, String>();
        time.put("time", "PT4H14M50S");
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
                contentType("application/json").
                body(time).
                when().
                put("/participations/2").
                then().
                statusCode(HttpStatus.SC_FORBIDDEN);

        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                contentType("application/json").
                body(time).
                when().
                put("/participations/2").
                then().
                statusCode(HttpStatus.SC_NO_CONTENT);

        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                when().
                get("/participations/2").
                then().
                statusCode(HttpStatus.SC_OK).
                body("time", is("PT4H14M50S"));
    }

}
