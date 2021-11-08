package model;

import exceptions.OutOfRangeGrilles;

public class Joeur {
    private final String name;
    private final Grille[] grilles;

    public Joeur(String name, Grille[] grilles) throws OutOfRangeGrilles {
        if(grilles.length<=5){
            this.name = name;
            this.grilles = grilles;
        } else throw new OutOfRangeGrilles();

    }

    public String getName() {
        return name;
    }

    public Grille[] getGrilles() {
        return grilles;
    }
}
