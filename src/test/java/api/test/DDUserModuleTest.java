package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDUserModuleTest {

	//Method to test post request based on data driven testing form excel sheet
	@Test(priority = 1, dataProvider= "data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String password, String phoneNumber) {
		
		//Creating object for pay load class to set post body object
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(password);
		userPayload.setPhone(phoneNumber);
		
		Response response = UserEndpoints.createUser(userPayload); 
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	
	//Method to test delete request on data driven testing form excel sheet
	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {
		
		//Passing userNames from userName column in the data sheet
		Response response = UserEndpoints.deleteUser(userName); 
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
