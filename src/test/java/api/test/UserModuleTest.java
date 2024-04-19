package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserModuleTest {
	
	//Creating instance of faker class to pass fake date for testing
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser() {

		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		//Validating the response of the create user
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		//Validating the response of the get user
		AssertJUnit.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		//Updating the user data in the pay load 
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().all();
		//Validating the response of the create user
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		//Validating whether the user data is changes as expected
		Response responseAfterUpdate = UserEndpoints.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(),200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	

}
