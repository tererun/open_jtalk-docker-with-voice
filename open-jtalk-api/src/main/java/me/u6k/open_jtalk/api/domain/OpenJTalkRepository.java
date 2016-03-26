
package me.u6k.open_jtalk.api.domain;

import me.u6k.open_jtalk.api.service.VoiceParameter;

public interface OpenJTalkRepository {

    byte[] createVoice(VoiceParameter param);

}
