
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
import me.u6k.open_jtalk.api.OpenJTalkRuntimeException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class VoiceServiceTest {

    @Autowired
    private VoiceService _s;

    @Test
    public void say_テキストを指定して音声データを出力() {
        VoiceParameter param = new VoiceParameter();
        param.setText("こんにちは");

        byte[] result = _s.say(param);

        String md5 = "6863e3bcac79948b7c2cc24283d4571f";

        assertThat(DigestUtils.md5Hex(result), is(md5));
    }

    @Test
    public void say_パラメータをいろいろ設定して音声データを出力() {
        VoiceParameter param = new VoiceParameter();
        param.setText("こんにちは");
        param.setSamplingFrequency(44000);
        param.setFramePeriod(128);
        param.setAllPassConstant(0.5f);
        param.setPostfilteringCoefficient(0.1f);
        param.setSpeechSpeedRate(1.5f);
        param.setAdditionalHalftone(0.1f);
        param.setVoicedUnvoicedThreshold(0.6f);
        param.setWeightOfGvForSpectrum(1.1f);
        param.setWeightOfGvForLogF0(1.1f);
        param.setVolume(60.0f);
        param.setAudioBufferSize(0);

        byte[] result = _s.say(param);

        String md5 = "569f434ab6f2295cc0617e9ac0b01877";

        assertThat(DigestUtils.md5Hex(result), is(md5));
    }

    @Test
    public void say_パラメータ未指定の場合はエラー() {
        try {
            _s.say(null);

            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("param is null."));
        }

        VoiceParameter param = new VoiceParameter();
        try {
            _s.say(param);

            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("param.text is blank."));
        }

        param = new VoiceParameter();
        param.setText("");
        try {
            _s.say(param);

            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("param.text is blank."));
        }

        param = new VoiceParameter();
        param.setText(" ");
        try {
            _s.say(param);

            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("param.text is blank."));
        }
    }

    @Test
    public void say_パラメータに未定義の値を指定した場合はエラー() {
        VoiceParameter param = new VoiceParameter();
        param.setText("こんにちは");
        param.setSpeechSpeedRate(-1.0f);
        try {
            _s.say(param);

            fail();
        } catch (OpenJTalkRuntimeException e) {
            e.printStackTrace();
        }
    }

}
