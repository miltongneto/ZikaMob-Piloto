package com.zika.lika.zikamob.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milton on 12/08/2018.
 */

public enum LocalFocus {
    CLOSEST_HOME("Perto de casa"),
    CLOSEST_SCHOOL("Perto da escola"),
    OTHER("Outro");

    private String description;

    private LocalFocus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static List<String> getDescriptions() {
        List<String> results = new ArrayList<>();
        for(LocalFocus local : values()) {
            results.add(local.getDescription());
        }
        return  results;
    }
}
