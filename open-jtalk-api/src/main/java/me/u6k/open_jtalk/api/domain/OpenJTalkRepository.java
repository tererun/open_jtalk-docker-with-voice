
package me.u6k.open_jtalk.api.domain;

import java.util.UUID;

public interface OpenJTalkRepository {

    UUID createVoiceText(String message);

    byte[] createVoiceSound(UUID voiceId);

    void deleteVoice(UUID voiceId);

}
