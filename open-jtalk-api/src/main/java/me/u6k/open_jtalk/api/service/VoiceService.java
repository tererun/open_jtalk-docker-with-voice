
package me.u6k.open_jtalk.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoiceService {

    private static final Logger L = LoggerFactory.getLogger(VoiceService.class);

    public String say() {
        L.debug("#say");

        return "say";
    }

}
