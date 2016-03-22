
package me.u6k.open_jtalk.api.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        String result = _s.say();

        assertThat(result, is("say"));
    }

}
