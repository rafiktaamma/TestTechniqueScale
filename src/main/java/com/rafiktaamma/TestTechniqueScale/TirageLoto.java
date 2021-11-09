package com.rafiktaamma.TestTechniqueScale;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import com.rafiktaamma.TestTechniqueScale.model.Gagnant;
import com.rafiktaamma.TestTechniqueScale.model.Grille;
import com.rafiktaamma.TestTechniqueScale.model.Joeur;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TirageLoto {
    public static void main(String[] args) throws IllegalGrilleException {

        System.out.println("TirageLoto");
        Grille winingGrille=new Grille("6:2:35:10:22:15:49:3");
        byte rang=4;
        String fileName = "src/main/resources/myFile_10000_rows.csv";
        List<Gagnant> gagnants=new ArrayList<>();
        // reading the csv file
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();

            for (int i=1; i<r.size();i++
                 ) {

                Joeur joeur=Joeur.LineToJoeur(r.get(i));
                if(joeur!=null) {
                    Grille[] joeur_grilles=joeur.getGrilles();
                for(int j=0;j<joeur_grilles.length;j++){
                    byte joeur_rang=joeur_grilles[j].getRange(winingGrille);
                    if(joeur_rang<=rang){
                        gagnants.add(new Gagnant(joeur.getName(),joeur_grilles[j],joeur_rang));
                    }
                }

                }
            }
            for (Gagnant gagnant:gagnants
                 ) {
                System.out.println(gagnant);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }


}
