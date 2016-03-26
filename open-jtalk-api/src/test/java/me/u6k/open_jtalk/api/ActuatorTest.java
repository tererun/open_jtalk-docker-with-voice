
package me.u6k.open_jtalk.api;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.port:0" })
public class ActuatorTest {

    @Value("${local.server.port:0}")
    private int _port;

    @Before
    public void setup() {
        RestAssured.port = _port;
    }

    @Test
    public void info() {
        given() //
                .get("/actuator/info") //
                .then() //
                .statusCode(HttpStatus.OK.value()) //
                .body("name", is("open-jtalk-api")) //
                .body("version", is("0.7.0-SNAPSHOT"));
    }

}
