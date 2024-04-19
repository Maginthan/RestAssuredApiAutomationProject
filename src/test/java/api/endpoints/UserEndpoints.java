package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class UserEndpoints {

	// Method for creating user
	public static Response createUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.post_url);

		return response;
	}

	// Method to get user by user name
	public static Response getUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
			.when()
				.get(Routes.get_url);

		return response;
	}

	// Method to update the existing user
	public static Response updateUser(User payload, String userName) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
		    .when()
				.put(Routes.update_url);

		return response;
	}

	// Method to delete the user by user name
	public static Response deleteUser(String userName) {
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete(Routes.delete_url);
		return response;
	}

}
