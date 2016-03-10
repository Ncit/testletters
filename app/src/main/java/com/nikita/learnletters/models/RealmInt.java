package com.nikita.learnletters.models;

import io.realm.RealmObject;

/**
 * Created by nikita on 10.03.16.
 */
public class RealmInt extends RealmObject {
    private int val;

    public RealmInt() {
    }

    public RealmInt(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
