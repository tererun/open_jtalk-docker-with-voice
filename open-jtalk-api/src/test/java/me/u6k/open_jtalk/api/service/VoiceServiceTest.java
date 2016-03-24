
package me.u6k.open_jtalk.api.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.u6k.open_jtalk.api.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class VoiceServiceTest {

    @Autowired
    private VoiceService _s;

    @Test
    public void say() {
        byte[] result = _s.say("こんにちは");

        String md5 = "6863e3bcac79948b7c2cc24283d4571f";

        assertThat(DigestUtils.md5Hex(result), is(md5));
    }

}
