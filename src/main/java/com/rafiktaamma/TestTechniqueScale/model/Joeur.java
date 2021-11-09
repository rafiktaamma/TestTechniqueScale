package com.rafiktaamma.TestTechniqueScale.model;


import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import com.rafiktaamma.TestTechniqueScale.exceptions.OutOfRangeGrilles;

import java.util.Arrays;

public class Joeur {
    private final String name;
    private final Grille[] grilles;

    public Joeur(String name, Grille[] grilles) throws OutOfRangeGrilles {
        if(grilles.length<=5){
            this.name = name;
            this.grilles = grilles;
        } else throw new OutOfRangeGrilles("out of range grilles : plus de 5 grilles " +
                "ont été identifié");

    }

    public String getName() {
        return name;
    }

    public Grille[] getGrilles() {
        return grilles;
    }

    public static Joeur LineToJoeur(String[] line)  {
        String name= line[3];
        // creating grilles

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
