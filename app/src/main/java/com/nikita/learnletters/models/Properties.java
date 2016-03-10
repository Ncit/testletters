package com.nikita.learnletters.models;

import io.realm.RealmObject;

/**
 * Created by nikita on 10.03.16.
 */
public class Properties extends RealmObject {
    private Boolean irregular_plural;
    private String plural;

    public Boolean getIrregular_plural() {
        return irregular_plural;
    }

    public void setIrregular_plural(Boolean irregular_plural) {
        this.irregular_plural = irregular_plural;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }
}
