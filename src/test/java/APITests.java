import models.User;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class APITests {

    @Test
    public void getUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?server=1&db=api_testing_sample_db&table=user_details&pos=0";
        var response = given().
                queryParam("user_id", 4).
                when().
                get(endpoint).
                then().log().body().assertThat().statusCode(200);
    }

    @Test
    public void getUsers() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        var response = given().
                when().
                get(endpoint).
                then();
        response.log().headers().assertThat().statusCode(200).
                header("Content-Type", equalTo("text/html; charset=utf-8")).
                body("records.size()", greaterThan(0)).
                body("records.user_id", everyItem(notNullValue())).
                body("records.username", everyItem(notNullValue())).
                body("records.first_name", everyItem(notNullValue())).
                body("records.last_name", everyItem(notNullValue())).
                body("records.gender", everyItem(notNullValue())).
                body("records.password", everyItem(notNullValue())).
                body("records.status", everyItem(notNullValue()));
    }

    @Test
    public void getHeadersUsers() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        var response = given().
                when().
                get(endpoint).
                then();
        response.log().headers().assertThat().statusCode(200);
    }

    @Test
    public void createUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        String body = """
                {
                "username": "Jeremy34",
                "first_name": "Jeremy",
                "last_name": "Tomas",
                "gender": "Male",
                "password": "aero23go45o6734psfk",
                "status": 1
                }
                """;
        var response = given().
                body(body).
                when().
                post(endpoint).
                then();
        response.log().body().assertThat().statusCode(equalTo(200));
    }

    @Test
    public void deleteUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?server=1&db=api_testing_sample_db&table=user_details&pos=0";
        var response = given().
                queryParam("user_id", 4).
                when().
                delete(endpoint).
                then().log().body().assertThat().statusCode(200);
    }

    @Test
    public void createSerializedUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        User user = new User(
                "Jeff",
                "Fitzgerald",
                "Male",
                "1aork4956ksf3266768",
                1
        );
        var response = given().
                body(user).
                when().post(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void getDeserializedUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        User expectedUser = new User(
                "Jeff",
                "Fitzgerald",
                "Male",
                "1aork4956ksf3266768",
                1);
        User actualUser = given().
                queryParam("user_id", "10").
                when().
                get(endpoint).
                as(User.class);
        assertThat(actualUser, samePropertyValuesAs(expectedUser));
    }

    @Test
    public void updateUser() {
        String endpoint = "http://localhost:81/phpMyAdmin/sql.php?db=api_testing_sample_db&table=user_details&pos=0";
        String body = """
                {
                "username": "J34",
                "first_name": "Jeremy",
                "last_name": "Tomas",
                "gender": "Male",
                "password":e12048i35kdsf,
                }
                """;
        var response = given().
                body(body).
                put(endpoint).
                then();
        response.log().body().assertThat().statusCode(200);
    }
}