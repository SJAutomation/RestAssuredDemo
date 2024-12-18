package demo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTP_Requests {
	
	
	int id;
	
  //@Test
   void getUser()
  {
	  
	  given()
	  
	  
	 .when()
	 	.get("https://reqres.in/api/users/")
	 
	  .then()
	  	.statusCode(200)
	  	.log().all();
  }
  
 @Test(priority = 1)
  void getUsers()
  {
	  given()
	  
	  
		 .when()
		 .get("https://reqres.in/api/users?page=2")
		 
		  .then()
		  	.statusCode(200)
		  	.body("page", equalTo(2))
		  	.log().all();
	  
  }
  
  @Test(priority = 2)
  void createUser()
  {
	  HashMap data=new HashMap();
	  data.put("name", "abc");
	  data.put("job","IT");
	  
	  
	id= given()
	  	.contentType("application/json")
	  	.body(data)
	  
	  .when()
	  	.post("https://reqres.in/api/users")
	  	.jsonPath().getInt("id");
	  	
	  
	//.then()
	 	//.statusCode(201)
	  //	.log().all();
  }
  
  
  @Test(dependsOnMethods = {"createUser"},priority = 3)
  void updateuser()
  {
	  HashMap data=new HashMap();
	  data.put("name", "abcd");
	  data.put("job","Sales");
	  
	  
	  given()
	  	.contentType("application/json")
	  	.body(data)
	  
	  .when()
	  	.put("https://reqres.in/api/users/"+id)
	  	
	  	
	  .then()
	  	.statusCode(200)
	  	.log().all();
  }
  
  @Test(priority = 4)
  void deleteUser()
  {
	  given()
	  
	  .when()
	  	.delete("https://reqres.in/api/users/"+id)
	  	
	  .then()
	  	.statusCode(204)
	  	.log().all();
	  
  }
}
