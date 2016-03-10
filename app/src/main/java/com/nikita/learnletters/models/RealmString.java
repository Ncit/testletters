package com.nikita.learnletters.models;

import io.realm.RealmObject;

/**
 * Created by nikita on 10.03.16.
 */
public class RealmString extends RealmObject {
    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}
