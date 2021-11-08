package model;

import exceptions.IllegalGrilleException;

public class Grille {

    private final byte[] numbers;
    private final byte chance_number;

    public Grille(byte[] numbers, byte chance_number) throws IllegalGrilleException {
        if((numbers.length<=7) && (0 <= chance_number && chance_number <= 10) ){
            this.numbers = numbers;
            this.chance_number = chance_number;
        }
        else throw new IllegalGrilleException();

    }

    public byte[] getNumbers() {
        return numbers;
    }

    public byte getChance_number() {
        return chance_number;
    }
    /**
     * Retourne le rang de la grille en comparant un grille de joeur avec la grille gagnante
     * @param grille La grille gagnante
     * @return le rang de la grille
     */
    public byte getRange(Grille grille){
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
    private byte countCommonElement(byte[] numbers){
        byte count=0;
        for (int i = 0; i < this.numbers.length; i++) {
            if(this.numbers[i]==numbers[i]) count++;
        }
        return count;
    }

}
