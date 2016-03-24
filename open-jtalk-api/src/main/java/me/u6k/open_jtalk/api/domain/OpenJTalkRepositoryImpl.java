
package me.u6k.open_jtalk.api.domain;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import me.u6k.open_jtalk.api.OpenJTalkRuntimeException;

@Component
public class OpenJTalkRepositoryImpl implements OpenJTalkRepository {

    private static final Logger L = LoggerFactory.getLogger(OpenJTalkRepositoryImpl.class);

    @Value("${open_jtalk.workDir}")
    private String _workDir;

    @Override
    public UUID createVoiceText(String message) {
        L.debug("#createVoiceText: message=" + message);

        UUID voiceId = UUID.randomUUID();
        File workDir = new File(_workDir);
        File voiceTextFile = new File(workDir, voiceId.toString() + ".txt");
        try {
            L.debug("write: " + voiceTextFile.getAbsolutePath());
            FileUtils.writeStringToFile(voiceTextFile, message, "UTF-8");
            L.debug("write success");
        } catch (IOException e) {
            throw new OpenJTalkRuntimeException(e);
        }

        L.debug("return: " + voiceId.toString());
        return voiceId;
    }

    @Override
    public byte[] createVoiceSound(UUID voiceId) {
        L.debug("#createVoiceSound: voiceId=" + voiceId);

        if (voiceId == null) {
            throw new IllegalArgumentException("voiceId is null.");
        }

        File workDir = new File(_workDir);
        File voiceTextFile = new File(workDir, voiceId.toString() + ".txt");
        File voiceDataFile = new File(workDir, voiceId.toString() + ".wav");
        String cmd = "open_jtalk -m /usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05/nitech_jp_atr503_m001.htsvoice -x /usr/local/lib/open_jtalk_dic_utf_8-1.09/ -ow " + voiceDataFile.getAbsolutePath() + " " + voiceTextFile.getAbsolutePath();
        L.debug("cmd=" + cmd);
        try {
            L.debug("txt=" + FileUtils.readFileToString(voiceTextFile));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        CommandLine cmdL = CommandLine.parse(cmd);
        DefaultExecutor cmdExec = new DefaultExecutor();
        cmdExec.setExitValue(0);
        try {
            int exitCode = cmdExec.execute(cmdL);
            L.debug("exitCode=" + exitCode);
            if (exitCode != 0) {
                throw new IOException("exitCode is not zero.");
            }
        } catch (IOException e) {
            throw new OpenJTalkRuntimeException(e);
        }

        byte[] voiceData;
        try {
            L.debug("read voiceFile: " + voiceDataFile.getAbsolutePath());
            voiceData = FileUtils.readFileToByteArray(voiceDataFile);
            L.debug("readed voiceFile: length=" + voiceData.length);
        } catch (IOException e) {
            throw new OpenJTalkRuntimeException(e);
        }

        return voiceData;
    }

    @Override
    public void deleteVoice(UUID voiceId) {
        L.debug("#deleteVoice: voiceId=" + voiceId);

        if (voiceId == null) {
            throw new IllegalArgumentException("voiceId is null.");
        }

        File workDir = new File(_workDir);
        File voiceTextFile = new File(workDir, voiceId.toString() + ".txt");
        if (voiceTextFile.exists()) {
            try {
                L.debug("voiceTextFile exists. deleting: " + voiceTextFile.getAbsolutePath());
                FileUtils.forceDelete(voiceTextFile);
                L.debug("deleted.");
            } catch (IOException e) {
                throw new OpenJTalkRuntimeException(e);
            }
        } else {
            L.debug("voiceTextFile not exists.");
        }

        File voiceDataFile = new File(workDir, voiceId.toString() + ".wav");
        if (voiceDataFile.exists()) {
            try {
                L.debug("voiceDataFile exists: deleting: " + voiceDataFile.getAbsolutePath());
                FileUtils.forceDelete(voiceDataFile);
                L.debug("deleted.");
            } catch (IOException e) {
                throw new OpenJTalkRuntimeException(e);
            }
        } else {
            L.debug("voiceDataFile not exists.");
        }
    }

}
