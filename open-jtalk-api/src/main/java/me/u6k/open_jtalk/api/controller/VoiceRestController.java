
package me.u6k.open_jtalk.api.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.u6k.open_jtalk.api.service.VoiceService;

@RestController
@RequestMapping(value = "voice")
public class VoiceRestController {

    private static final Logger L = LoggerFactory.getLogger(VoiceRestController.class);

    @Autowired
    private VoiceService _s;

    @RequestMapping(method = RequestMethod.GET, produces = "audio/wav")
    public byte[] say(@RequestParam Map<String, Object> req) {
        L.debug("#say: message=" + req.get("message"));

        String message = (String) req.get("message");
        byte[] voiceData = _s.say(message);

        L.debug("return: voiceData.length=" + voiceData.length);
        return voiceData;
    }

}
