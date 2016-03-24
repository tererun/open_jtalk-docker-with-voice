
package me.u6k.open_jtalk.api.service;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.u6k.open_jtalk.api.domain.OpenJTalkRepository;

@Service
public class VoiceService {

    private static final Logger L = LoggerFactory.getLogger(VoiceService.class);

    @Autowired
    private OpenJTalkRepository _openJTalkRepo;

    public byte[] say(String message) {
        L.debug("#say: message=" + message);

        if (StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("message is blank.");
        }

        message = message.trim();
        UUID voiceId = _openJTalkRepo.createVoiceText(message);
        L.debug("voiceId=" + voiceId.toString());

        byte[] voiceData = _openJTalkRepo.createVoiceSound(voiceId);
        L.debug("voiceData.length=" + voiceData.length);

        _openJTalkRepo.deleteVoice(voiceId);
        L.debug("voice deleted.");

        return voiceData;
    }

}
