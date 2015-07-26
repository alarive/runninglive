package com.runninglive.integrationtesting;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: alarive
 */
public class HealthcheckIntegrationTest extends CommonIntegrationTestWithFixtures {

    /*
        User Story #1 : En tant que développeur, je voudrais avoir un healthcheck afin de m'assurer que le service fonctionne.
     */
    @Test
    public void testDeveloperCanGetHealthcheck() {
        given().auth().basic(developerAurelien.getUsername(), developerAurelien.getPassword()).
                when().
                get("/management/health").
                then().
                statusCode(HttpStatus.SC_OK)
                .body("status", is("UP"));
    }

}
