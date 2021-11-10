package com.rafiktaamma.TestTechniqueScale.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.rafiktaamma.TestTechniqueScale.model.Gagnant;
import com.rafiktaamma.TestTechniqueScale.model.Grille;
import com.rafiktaamma.TestTechniqueScale.model.Joeur;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TirageService {

    public List<Gagnant> getWinners(File file, Grille winingGrille, byte rang){
        List<Gagnant> gagnants=new ArrayList<>();
        // reading the csv file
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
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
            return gagnants;

        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }
}
