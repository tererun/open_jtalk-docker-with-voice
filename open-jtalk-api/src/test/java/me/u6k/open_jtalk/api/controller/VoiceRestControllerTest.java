
package me.u6k.open_jtalk.api.controller;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;

import me.u6k.open_jtalk.api.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({ "server.port:0" })
public class VoiceRestControllerTest {

    @Value("${local.server.port:0}")
    private int _port;

    @Before
    public void setup() {
        RestAssured.port = _port;
    }

    @Test
    public void say() {
        Response resp = given() //
                .contentType("text/plain;charset=UTF-8") //
                .config(getUTF8Config()) //
                .when() //
                .get("/voice");

        assertThat(resp.statusCode(), is(HttpStatus.OK.value()));
        assertThat(resp.contentType(), is("text/plain;charset=UTF-8"));
        assertThat(resp.asString(), is("say"));
    }

    private RestAssuredConfig getUTF8Config() {
        return new RestAssuredConfig().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"));
    }

}
