
package me.u6k.open_jtalk.api.controller;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
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
    public void say_音声ファイルが出力される() {
        Response resp = given().get("/voice?message=こんにちは");
        String md5 = "6863e3bcac79948b7c2cc24283d4571f";

        assertThat(resp.statusCode(), is(HttpStatus.OK.value()));
        assertThat(resp.contentType(), is("audio/wav;charset=UTF-8"));
        assertThat(resp.header("Content-Length"), is("154124"));
        assertThat(DigestUtils.md5Hex(resp.asByteArray()), is(md5));
    }

    @Test
    public void say_パラメータ無しや間違えたパラメータは400() {
        Response resp = given().get("/voice");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));

        resp = given().get("/voice?message=");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));

        resp = given().get("/voice?msg=こんにちは");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

}
