
package me.u6k.open_jtalk.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.u6k.open_jtalk.api.service.VoiceService;

@RestController
@RequestMapping(value = "voice")
public class VoiceRestController {

    private static final Logger L = LoggerFactory.getLogger(VoiceRestController.class);

    @Autowired
    private VoiceService _s;

    @RequestMapping(method = RequestMethod.GET)
    public String say() {
        L.debug("#say");

        String result = _s.say();

        L.debug("return: " + result);
        return result;
    }

}
