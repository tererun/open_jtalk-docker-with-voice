
package me.u6k.open_jtalk.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.u6k.open_jtalk.api.service.VoiceParameter;
import me.u6k.open_jtalk.api.service.VoiceService;

@RestController
@RequestMapping(value = "voice")
public class VoiceRestController {

    private static final Logger L = LoggerFactory.getLogger(VoiceRestController.class);

    @Autowired
    private VoiceService _s;

    @RequestMapping(method = RequestMethod.GET, produces = "audio/wav")
    public byte[] say(@RequestParam(name = "text") String text, //
            @RequestParam(name = "s", required = false) Integer samplingFrequency, //
            @RequestParam(name = "p", required = false) Integer framePeriod, //
            @RequestParam(name = "a", required = false) Float allPassConstant, //
            @RequestParam(name = "b", required = false) Float postfilteringCoefficient, //
            @RequestParam(name = "r", required = false) Float speechSpeedRate, //
            @RequestParam(name = "fm", required = false) Float additionalHalftone, //
            @RequestParam(name = "u", required = false) Float voicedUnvoicedThreshold, //
            @RequestParam(name = "jm", required = false) Float weightOfGvForSpectrum, //
            @RequestParam(name = "jf", required = false) Float weightOfGvForLogF0, //
            @RequestParam(name = "g", required = false) Float volume, //
            @RequestParam(name = "z", required = false) Integer audioBufferSize) {
        L.debug("#say");

        VoiceParameter param = new VoiceParameter();
        param.setText(text);
        param.setSamplingFrequency(samplingFrequency);
        param.setFramePeriod(framePeriod);
        param.setAllPassConstant(allPassConstant);
        param.setPostfilteringCoefficient(postfilteringCoefficient);
        param.setSpeechSpeedRate(speechSpeedRate);
        param.setAdditionalHalftone(additionalHalftone);
        param.setVoicedUnvoicedThreshold(voicedUnvoicedThreshold);
        param.setWeightOfGvForSpectrum(weightOfGvForSpectrum);
        param.setWeightOfGvForLogF0(weightOfGvForLogF0);
        param.setVolume(volume);
        param.setAudioBufferSize(audioBufferSize);
        L.debug("param=" + param);

        byte[] voiceData = _s.say(param);

        L.debug("return: voiceData.length=" + voiceData.length);
        return voiceData;
    }

}
