package org.example;

import java.io.Serializable;

public class Pokemon implements Serializable {

    public String pkName;
    public String pkObject;
    public int pkAtk;
    public int pkDef;

    public Pokemon(String pkName, String pkObject, int pkAtk, int pkDef) {
        this.pkName = pkName;
        this.pkObject = pkObject;
        this.pkAtk = pkAtk;
        this.pkDef = pkDef;
    }
}
