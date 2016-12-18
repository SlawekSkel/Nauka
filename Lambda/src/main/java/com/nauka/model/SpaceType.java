package com.nauka.model;

/**
 * Created by LENOVO on 2016-12-18.
 */
public enum SpaceType {
    NONE(""), SPACE(" "), _UNDER("_");

    private String description;

    SpaceType(String desc) {
        description = desc;
    }

    public String getSpace() {
        return description;
    }

}
