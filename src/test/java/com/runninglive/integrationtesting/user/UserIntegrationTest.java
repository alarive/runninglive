package com.runninglive.integrationtesting.user;

import com.runninglive.integrationtesting.CommonIntegrationTestWithFixtures;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: alarive
 */
public class UserIntegrationTest extends CommonIntegrationTestWithFixtures {

    /*
        User Story #2 : En tant que coureur, je voudrais pouvoir me connecter au web service.
     */
    @Test
    public void testRunnerCanConnect() {
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
        when().
                get("/users/{id}", runnerSahbi.getId()).
        then().
                statusCode(HttpStatus.SC_OK).
                body("username", is(runnerSahbi.getUsername()));
    }

    /*
        User Story #3 : En tant qu'organisateur, je voudrais pouvoir me connecter au web service.
     */
    @Test
    public void testOrganizerCanConnect() {
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
        when().
                get("/users/{id}", organizerJessica.getId()).
        then().
                statusCode(HttpStatus.SC_OK).
                body("username", is(organizerJessica.getUsername()));
    }

    @Test
    public void testRolesAreNotExported() {
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
        when().
                get("/roles").
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
