package com.rafiktaamma.TestTechniqueScale.model;

public class Gagnant {
    private final String name;
    private final Grille grille;
    private final Byte rang;

    public Gagnant(String name, Grille grille, Byte rang) {
        this.name = name;
        this.grille = grille;
        this.rang = rang;
    }

    public String getName() {
        return name;
    }

    public Grille getGrille() {
        return grille;
    }

    public Byte getRang() {
        return rang;
    }

    @Override
    public String toString() {
        return "Gagnant{" +
                "name='" + name + '\'' +
                ", grille=" + grille +
                ", rang=" + rang +
                '}';
    }
}
