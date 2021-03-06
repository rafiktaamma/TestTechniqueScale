package com.rafiktaamma.TestTechniqueScale.model;


import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Stream;

public class Grille {

    private final Byte[] numbers;
    private final byte chance_number;

    /**
     * un construteur qui prends tous les arguments
     * @param numbers
     * @param chance_number
     * @throws IllegalGrilleException si le fichier csv contient une erreur (joeur à spécifier plus de 7 numéros dans la grille ou (numéro dans la grille ou le numéro de chance non conforme), le contructeur léve un exception
     */
    public Grille(Byte[] numbers, byte chance_number) throws IllegalGrilleException {
        if((numbers.length<=7) && (isGrilleValid(numbers)) && (-1 <= chance_number && chance_number <= 10 && chance_number != 0 ) ){
            this.numbers = numbers;
            this.chance_number = chance_number;
        }
        else throw new IllegalGrilleException("Illegal grille : plus de 7 nombres ou nombre de chance illégal");

    }

    /**
     * un constructeur qui construit la grille en utilisant une chaine de characters de numéros (8 numéros séparés par des ":" , le dernier numéro répresente le numéro de chance)
     * @throws IllegalGrilleException si le fichier csv contient une erreur (joeur à spécifier plus de 7 numéros dans la grille ou (numéro dans la grille ou le numéro de chance non conforme), le contructeur léve un exception
     */
    public Grille(String grille) throws IllegalGrilleException {
        Byte[] array_grille=Stream.of(grille.split(":"))
                .map(Byte::parseByte)
                .toArray(Byte[]::new);
        if((array_grille.length==8) && (isGrilleValid(array_grille)) && ((-1 <= array_grille[7] && array_grille[7] <= 10 && array_grille[7] != 0 )) ){
            // just our bytes

        this.numbers= Arrays.copyOfRange(array_grille, 0, 7);;
        this.chance_number=array_grille[7];
        }
        else throw new IllegalGrilleException("Illegal grille : plus de 7 nombres ou nombre de chance illégal");
    }


    public Byte[] getNumbers() {
        return numbers;
    }

    /**
     * Un Getter pour accéder au numéro de chance dans la grille
     * @return byte chance_number
     */
    public byte getChance_number() {
        return chance_number;
    }


    /**
     * Retourne le rang de la grille en comparant un grille de joeur avec la grille gagnante
     * @param grille La grille gagnante
     * @return le rang de la grille
     */
    public Byte getRange(Grille grille){
        boolean withChanceNumber=(this.chance_number==grille.getChance_number());
        byte count=this.countCommonElement(grille.getNumbers());
        byte rang;
        if(count>5) rang=1;
        else if(count==5){
          if(withChanceNumber) rang=1; else rang=2;
        }
        else if(count==4) rang=3;
        else if(count==3) rang=4;
        else if(count==2) rang=5;
        else if(count==1 || count==0) rang=6;
        else rang=-1;
        return rang;
    }
    private Byte countCommonElement(Byte[] numbers){
        byte count=0;
        for (int i = 0; i < this.numbers.length; i++) {
            if(this.numbers[i]==numbers[i]) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return "Grille{" +
                "numbers=" + Arrays.toString(numbers) +
                ", chance_number=" + chance_number +
                '}';
    }

    private boolean isGrilleValid(Byte[] numbers){
      boolean valid=true;
        for (Byte number:numbers
             ) {
           if(number>49 || number==0 || number<-1) valid=false;
        }
       return valid;
    }
}
