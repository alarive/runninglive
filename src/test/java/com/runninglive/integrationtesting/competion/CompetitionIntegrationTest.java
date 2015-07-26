package com.runninglive.integrationtesting.competion;

import com.runninglive.integrationtesting.CommonIntegrationTestWithFixtures;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * User: alarive
 */
public class CompetitionIntegrationTest extends CommonIntegrationTestWithFixtures {

    /*
        User story #5 : En tant qu'organisateur, je voudrais pouvoir lister toutes les compétitions.
        User story #6 : En tant qu'organisateur, je voudrais qu'une compétition ait un nom et une date.
     */
    @Test
    public void testOrganizerCanListCompetions() {
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
        when().
                get("/competitions").
        then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.competitions[0].name", is("Marathon de Paris 2016")).
                body("_embedded.competitions[0].date", is("2016-04-03T00:00")).
                body("_embedded.competitions[1].name", is("Frappadingue Opale X'TREM 2015")).
                body("_embedded.competitions[1].date", is("2015-09-13T00:00"));
    }

    /*
        User story #6 : En tant que coureur, je voudrais connaître le lieu d'une compétition.
     */
    @Test
    public void testRunnerCanKnowCompetitionPlace() {
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
                when().
                get("/competitions/1").
                then().
                statusCode(HttpStatus.SC_OK).
                body("place", is("Paris"));
    }
}
