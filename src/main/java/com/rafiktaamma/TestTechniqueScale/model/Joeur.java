package com.rafiktaamma.TestTechniqueScale.model;


import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import com.rafiktaamma.TestTechniqueScale.exceptions.OutOfRangeGrilles;

import java.io.Serializable;
import java.util.Arrays;

public class Joeur implements Serializable {
    private final String name;
    private final Grille[] grilles;

    /**
     * Un constructeur qui prend tous les arguments pour construire un objet jeour
     * @param name
     * @param grilles
     * @throws OutOfRangeGrilles si le fichier csv contient pour un joeur plus de 5 grilles , le contructeur lève cette exception
     */
    public Joeur(String name, Grille[] grilles) throws OutOfRangeGrilles {
        if(grilles.length<=5){
            this.name = name;
            this.grilles = grilles;
        } else throw new OutOfRangeGrilles("out of range grilles : plus de 5 grilles " +
                "ont été identifié");

    }

    /**
     * Un Getter pour accéder au nom du joeur
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Un Getter pour accéder aux grilles de joeur
     * @return Grilles[] grilles
     */
    public Grille[] getGrilles() {
        return grilles;
    }

    /**
     * Cette fonction permet de construire un objet joeur à partir d'une ligne dans le fichier csv
     * @param line une ligne qui continet les informations sur le joeur(nom,grilles)
     * @return Joeur un objet de la classe joeur
     */
    public static Joeur LineToJoeur(String[] line)  {
        String name= line[3];
        try {
            Grille[] grilles = {
                    new Grille(line[4]),
                    new Grille(line[5]),
                    new Grille(line[6]),
                    new Grille(line[7]),
                    new Grille(line[8]),
            };
            return new Joeur(name,grilles);
        } catch (IllegalGrilleException | OutOfRangeGrilles e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(line));
            return null;
        } catch (NumberFormatException e){
            System.out.println("Number Format exception");
            System.out.println(line.toString());
            return null;
        }
    }



    @Override
    public String toString() {
        return "Joeur{" +
                "name='" + name + '\'' +
                ", grilles=" + Arrays.toString(grilles) +
                '}';
    }
}
