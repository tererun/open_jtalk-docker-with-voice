
package me.u6k.open_jtalk.api.service;

import org.apache.commons.lang.builder.ToStringBuilder;

public class VoiceParameter {

    private String text;

    private Integer samplingFrequency;

    private Integer framePeriod;

    private Float allPassConstant;

    private Float postfilteringCoefficient;

    private Float speechSpeedRate;

    private Float additionalHalftone;

    private Float voicedUnvoicedThreshold;

    private Float weightOfGvForSpectrum;

    private Float weightOfGvForLogF0;

    private Float volume;

    private Integer audioBufferSize;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(Integer samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public Integer getFramePeriod() {
        return framePeriod;
    }

    public void setFramePeriod(Integer framePeriod) {
        this.framePeriod = framePeriod;
    }

    public Float getAllPassConstant() {
        return allPassConstant;
    }

    public void setAllPassConstant(Float allPassConstant) {
        this.allPassConstant = allPassConstant;
    }

    public Float getPostfilteringCoefficient() {
        return postfilteringCoefficient;
    }

    public void setPostfilteringCoefficient(Float postfilteringCoefficient) {
        this.postfilteringCoefficient = postfilteringCoefficient;
    }

    public Float getSpeechSpeedRate() {
        return speechSpeedRate;
    }

    public void setSpeechSpeedRate(Float speechSpeedRate) {
        this.speechSpeedRate = speechSpeedRate;
    }

    public Float getAdditionalHalftone() {
        return additionalHalftone;
    }

    public void setAdditionalHalftone(Float additionalHalftone) {
        this.additionalHalftone = additionalHalftone;
    }

    public Float getVoicedUnvoicedThreshold() {
        return voicedUnvoicedThreshold;
    }

    public void setVoicedUnvoicedThreshold(Float voicedUnvoicedThreshold) {
        this.voicedUnvoicedThreshold = voicedUnvoicedThreshold;
    }

    public Float getWeightOfGvForSpectrum() {
        return weightOfGvForSpectrum;
    }

    public void setWeightOfGvForSpectrum(Float weightOfGvForSpectrum) {
        this.weightOfGvForSpectrum = weightOfGvForSpectrum;
    }

    public Float getWeightOfGvForLogF0() {
        return weightOfGvForLogF0;
    }

    public void setWeightOfGvForLogF0(Float weightOfGvForLogF0) {
        this.weightOfGvForLogF0 = weightOfGvForLogF0;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Integer getAudioBufferSize() {
        return audioBufferSize;
    }

    public void setAudioBufferSize(Integer audioBufferSize) {
        this.audioBufferSize = audioBufferSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
