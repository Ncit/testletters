package com.nikita.learnletters.models;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by nikita on 10.03.16.
 */
public class Alternative extends RealmObject {
    @Required
    private String text;
    private String translation;

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
}
