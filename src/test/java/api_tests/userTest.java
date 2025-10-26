package api_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api_models.userDetails;
import api_user.userClient;
import api_utils.config;
import io.restassured.response.Response;

public class userTest {
	
	
	private userClient client;
	
	@BeforeClass
	public void setup() {
		config.setupBaseURI();
		client=new userClient();
		
	}
	
	@Test
	public void testCRUD() throws InterruptedException {
		userDetails user=new userDetails();
		user.setId(101);
		user.setUsername("Madrista");
		user.setFirstName("suraj");
		user.setLastName("S");
		user.setEmail("suraj@RMA.com");
		user.setPassword("123456");
		user.setUserStatus(0);
		
		
		// CREATE
        Response createResponse = client.createUser(user);
        System.out.println("CREATE Response:");
        createResponse.prettyPrint();
        Assert.assertEquals(createResponse.getStatusCode(), 200);

        
        Thread.sleep(1500);

        // READ
        Response getResponse = client.getUser("Madrista");
        int statusCode = getResponse.getStatusCode();

        if (statusCode == 200) {
            System.out.println("User retrieved successfully!");
        } else if (statusCode == 404) {
            System.out.println("User not found — Petstore API is non-persistent.");
        } else {
            Assert.fail("Unexpected status code: " + statusCode);
        }
        // UPDATE
        user.setPassword("98765");
        user.setEmail("marvel@gmail.com");
        Response updateResponse = client.updateUser("Madrista", user);
        System.out.println("UPDATE Response:");
        updateResponse.prettyPrint();
        Assert.assertEquals(updateResponse.getStatusCode(), 200);

        // DELETE
        Response deleteResponse = client.deleteUser("Madrista");
        System.out.println("DELETE Response:");
        deleteResponse.prettyPrint();
        Assert.assertEquals(deleteResponse.getStatusCode(), 200);
    }
	}	
	


