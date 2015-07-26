package com.runninglive.integrationtesting.competion;

import com.runninglive.competition.Competition;
import com.runninglive.integrationtesting.CommonIntegrationTestWithFixtures;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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
                body("_embedded.competitions[0].dateAndTime", is("2016-04-03T00:00")).
                body("_embedded.competitions[1].name", is("Frappadingue Opale X'TREM 2015")).
                body("_embedded.competitions[1].dateAndTime", is("2015-09-13T00:00"));
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

    /*
        User story #7 : En tant que coureur, je voudrais pouvoir lister toutes les compétitions auxquelles
        je suis inscrit ou j'ai participé.
     */
    @Test
    public void testRunnerCanListItsCompetitions() {
        given().auth().basic(runnerSahbi.getUsername(), runnerSahbi.getPassword()).
        when().
                get("/users/3/participations").
        then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.competitions[0].name", is("Frappadingue Opale X'TREM 2015"));
    }

    /*
        User story #8 : En tant qu'organisateur, je voudrais pouvoir créer une compétition
     */
    @Test
    public void testOrganizerCanCreateACompetition() {
        Competition competition = new Competition("Les foulees de la rue", LocalDateTime.parse("2015-06-06T08:00:00"));
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
                contentType("application/json")
                .body(competition)
        .when().
                post("/competitions").
        then().
                statusCode(HttpStatus.SC_CREATED);


        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
        when().
                get("/competitions/4").
        then().
                statusCode(HttpStatus.SC_OK).
        body("name", is("Les foulees de la rue"));
    }

    /*
        User story #9 : En tant qu'organisateur, je voudrais pouvoir lister tous les coureurs inscrits à une compétition.
     */
    @Test
    public void testOrganizerCanListRunnersInACompetition() {
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
        when().
                get("/competitions/1/participants").
        then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.users.username", is(Arrays.asList(new String[]{"aurelien", "sahbi"})));
    }

    /*
        User story #9 : En tant qu'organisateur, je voudrais pouvoir lister tous les coureurs inscrits à une compétition.
     */
    @Test
    public void testOrganizerCanListRunnersHeight() {
        given().auth().basic(organizerJessica.getUsername(), organizerJessica.getPassword()).
                when().
                get("/users").
                then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.users.height", is(Arrays.asList(new Integer[]{185, 180, 182})));
    }
}
