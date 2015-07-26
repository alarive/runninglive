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

}
