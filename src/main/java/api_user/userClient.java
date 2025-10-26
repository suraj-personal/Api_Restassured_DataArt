package api_user;

import static io.restassured.RestAssured.*;
import api_models.userDetails;
import io.restassured.response.Response;

public class userClient {
	
	
	private static final String BASE_PATH = "/user";

    public Response createUser(userDetails user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_PATH);
    }

    public Response getUser(String username) {
        return given()
                .when()
                .get(BASE_PATH + "/" + username);
    }

    public Response updateUser(String username, userDetails user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(BASE_PATH + "/" + username);
    }

    public Response deleteUser(String username) {
        return given()
                .when()
                .delete(BASE_PATH + "/" + username);

}
}
