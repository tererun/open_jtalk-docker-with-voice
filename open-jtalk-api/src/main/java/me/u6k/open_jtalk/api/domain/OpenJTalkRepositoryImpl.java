
package me.u6k.open_jtalk.api.domain;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import me.u6k.open_jtalk.api.OpenJTalkRuntimeException;
import me.u6k.open_jtalk.api.service.VoiceParameter;

@Component
public class OpenJTalkRepositoryImpl implements OpenJTalkRepository {

    private static final Logger L = LoggerFactory.getLogger(OpenJTalkRepositoryImpl.class);

    @Value("${open_jtalk.workDir}")
    private String _workDir;

    @Override
    public byte[] createVoice(VoiceParameter param) {
        L.debug("#createVoiceSound: param=" + param);
        try {
            File workDir = new File(_workDir);

            UUID voiceId = UUID.randomUUID();
            File voiceTextFile = new File(workDir, voiceId.toString() + ".txt");
            L.debug("write: " + voiceTextFile.getAbsolutePath());
            FileUtils.writeStringToFile(voiceTextFile, param.getText(), "UTF-8");
            L.debug("write success");

            File voiceDataFile = new File(workDir, voiceId.toString() + ".wav");

            String cmd = "open_jtalk";
            if (param.getVoice() == null) {
                cmd += " -m /usr/local/lib/hts_voice_nitech_jp_atr503_m001-1.05/nitech_jp_atr503_m001.htsvoice";
            } else {
                cmd += " -m " + param.getVoice();
            }
            cmd += " -x /usr/local/lib/open_jtalk_dic_utf_8-1.09/";
            if (param.getSamplingFrequency() != null) {
                cmd += " -s " + param.getSamplingFrequency();
            }
            if (param.getFramePeriod() != null) {
                cmd += " -p " + param.getFramePeriod();
            }
            if (param.getAllPassConstant() != null) {
                cmd += " -a " + param.getAllPassConstant();
            }
            if (param.getPostfilteringCoefficient() != null) {
                cmd += " -b " + param.getPostfilteringCoefficient();
            }
            if (param.getSpeechSpeedRate() != null) {
                cmd += " -r " + param.getSpeechSpeedRate();
            }
            if (param.getAdditionalHalftone() != null) {
                cmd += " -fm " + param.getAdditionalHalftone();
            }
            if (param.getVoicedUnvoicedThreshold() != null) {
                cmd += " -u " + param.getVoicedUnvoicedThreshold();
            }
            if (param.getWeightOfGvForSpectrum() != null) {
                cmd += " -jm " + param.getWeightOfGvForSpectrum();
            }
            if (param.getWeightOfGvForLogF0() != null) {
                cmd += " -jf " + param.getWeightOfGvForLogF0();
            }
            if (param.getVolume() != null) {
                cmd += " -g " + param.getVolume();
            }
            if (param.getAudioBufferSize() != null) {
                cmd += " -z " + param.getAudioBufferSize();
            }
            cmd += " -ow " + voiceDataFile.getAbsolutePath() + " " + voiceTextFile.getAbsolutePath();
            L.debug("cmd=" + cmd);

            CommandLine cmdL = CommandLine.parse(cmd);
            DefaultExecutor cmdExec = new DefaultExecutor();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            cmdExec.setStreamHandler(new PumpStreamHandler(bout));
            cmdExec.setExitValue(0);
            try {
                cmdExec.execute(cmdL);
            } catch (ExecuteException e) {
                throw new OpenJTalkRuntimeException(e.getMessage(), e);
            }

            L.debug("read voiceFile: " + voiceDataFile.getAbsolutePath());
            byte[] voiceData = FileUtils.readFileToByteArray(voiceDataFile);
            L.debug("readed voiceFile: length=" + voiceData.length);

            L.debug("voiceTextFile exists. deleting: " + voiceTextFile.getAbsolutePath());
            FileUtils.forceDelete(voiceTextFile);
            L.debug("deleted.");

            L.debug("voiceDataFile exists: deleting: " + voiceDataFile.getAbsolutePath());
            FileUtils.forceDelete(voiceDataFile);
            L.debug("deleted.");

            return voiceData;
        } catch (IOException e) {
            throw new OpenJTalkRuntimeException(e);
        }
    }

}
