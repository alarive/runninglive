package com.runninglive.integrationtesting.security;

import com.runninglive.integrationtesting.CommonIntegrationTestWithFixtures;
import org.apache.http.HttpStatus;
import org.junit.Test;

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

}
