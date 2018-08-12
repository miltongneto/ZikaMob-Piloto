package com.zika.lika.zikamob.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milton on 12/08/2018.
 */

public enum TypeFocus {
    BOTTLE("Latas, Vidros, Garrafas"),
    WHEEL("Pneus"),
    DRINKING_TROUGHS("Bebedouros de animais domésticos"),
    FLOWER_VASES("Vasos de flores, pratos de vasos de planta"),
    GUTTER("Calhas entupidas"),
    POOL("Piscina"),
    TANKS("Caixas D’água,  Tonéis, Latões e Cisternas"),
    GARBAGE("Lixo a céu aberto"),
    TRAY("Bandejas de ar-condicionado e de geladeiras"),
    PUDDLES("Poças"),
    OTHERS("Outros");

    private String description;

    private TypeFocus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static List<String> getDescriptions() {
        List<String> results = new ArrayList<>();
        for(TypeFocus type : values()) {
            results.add(type.getDescription());
        }
        return  results;
    }

}
