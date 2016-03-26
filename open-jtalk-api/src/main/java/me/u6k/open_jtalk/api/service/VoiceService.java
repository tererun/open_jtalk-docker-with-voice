
package me.u6k.open_jtalk.api.service;

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

    public byte[] say(VoiceParameter param) {
        L.debug("#say: param=" + param);

        if (param == null) {
            throw new IllegalArgumentException("param is null.");
        }
        if (StringUtils.isBlank(param.getText())) {
            throw new IllegalArgumentException("param.text is blank.");
        }

        param.setText(param.getText().trim());

        byte[] voiceData = _openJTalkRepo.createVoice(param);
        L.debug("voiceData.length=" + voiceData.length);

        return voiceData;
    }

}
