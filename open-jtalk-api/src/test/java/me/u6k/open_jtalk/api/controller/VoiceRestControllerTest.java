
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
    public void say_テキストを指定して音声データを出力() {
        Response resp = given().get("/voice?text=こんにちは");
        String md5 = "6863e3bcac79948b7c2cc24283d4571f";

        assertThat(resp.statusCode(), is(HttpStatus.OK.value()));
        assertThat(resp.contentType(), is("audio/wav;charset=UTF-8"));
        assertThat(resp.header("Content-Length"), is("154124"));
        assertThat(DigestUtils.md5Hex(resp.asByteArray()), is(md5));
    }

    @Test
    public void say_パラメータをいろいろ設定して音声データを出力() {
        String url = "/voice?text=こんにちは&s=44000&p=128&a=0.5&b=0.1&r=1.5&fm=0.1&u=0.6&jm=1.1&jf=1.1&g=60.0&z=0";
        Response resp = given().get(url);
        String md5 = "569f434ab6f2295cc0617e9ac0b01877";

        assertThat(resp.statusCode(), is(HttpStatus.OK.value()));
        assertThat(resp.contentType(), is("audio/wav;charset=UTF-8"));
        assertThat(resp.header("Content-Length"), is("55340"));
        assertThat(DigestUtils.md5Hex(resp.asByteArray()), is(md5));
    }

    @Test
    public void say_パラメータ未指定の場合はエラー() {
        Response resp = given().get("/voice");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));

        resp = given().get("/voice?text=");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));

        resp = given().get("/voice?txt=こんにちは");

        assertThat(resp.statusCode(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void say_パラメータに未定義の値を指定した場合はエラー() {
        Response resp = given().get("/voice?text=こんにちは&r=-1.0");

        assertThat(resp.statusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

}
