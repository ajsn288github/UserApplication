package com.pnc.training.UserApplication;

import com.pnc.training.userapp.UserApplication;
import com.pnc.training.userapp.model.User;
import com.pnc.training.userapp.model.dto.UserDto;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {

    @LocalServerPort
    private int localPort;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetAllUserInSadPath() {

        ResponseEntity<String> response = restTemplate.getForEntity(createURLWithPort("/users"), String.class);
        String expected = "{\"status\":{\"statusCode\":200,\"status\":\"SUCCESS\",\"message\":\"\"},\"data\":[{\"userId\":1,\"userName\":\"Selva\",\"role\":\"SSE\",\"location\":\"Pittsburgh\"},{\"userId\":2,\"userName\":\"Narayanan\",\"role\":\"TL\",\"location\":\"Pittsburgh\"},{\"userId\":3,\"userName\":\"Andal\",\"role\":\"BA\",\"location\":\"Pittsburgh\"}]}";
        try {
            JSONAssert.assertNotEquals(expected, response.getBody(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserWithCorrectUserId() {

        ResponseEntity<UserDto> response = restTemplate.getForEntity(createURLWithPort("/users/1"), UserDto.class);
        String expectedUserId =  "1";
        try {
            JSONAssert.assertEquals("SUCCESS", response.getBody().getStatus(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByNameWithWrongUserName() {

        ResponseEntity<UserDto> response = restTemplate.getForEntity(createURLWithPort("/users/name/poda"), UserDto.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void addUserWithProperJSONData() {

        User user = new User();
        user.setUserName("name");
        user.setRole("role");
        user.setLocation("location");
        given().contentType(String.valueOf(MediaType.APPLICATION_JSON)).body(user).when().post(createURLWithPort("/users/")).then().statusCode(200);
    }

    @Test
    public void deleteUserInHappyPath() {

        given().pathParam("userId", 3).when().delete(createURLWithPort("/users/{userId}")).then().statusCode(200);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + localPort + uri;
    }

}
