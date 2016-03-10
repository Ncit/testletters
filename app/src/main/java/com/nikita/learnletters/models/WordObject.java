package com.nikita.learnletters.models;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by nikita on 10.03.16.
 */
public class WordObject extends RealmObject {
    @PrimaryKey
    private String meaningId;
    private String posCode;
    private String text;
    private String translation;
    private String definition;
    private String example;
//    private Properties properties;
    private String transcription;
    private RealmList<Alternative> alternatives;
    private RealmList<RealmString> images;
    private String soundUrl;

    public String getMeaningId() {
        return meaningId;
    }

    public void setMeaningId(String meaningId) {
        this.meaningId = meaningId;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

//    public Properties getProperties() {
//        return properties;
//    }
//
//    public void setProperties(Properties properties) {
//        this.properties = properties;
//    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public RealmList<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(RealmList<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public RealmList<RealmString> getImages() {
        return images;
    }

    public void setImages(RealmList<RealmString> images) {
        this.images = images;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }
}
