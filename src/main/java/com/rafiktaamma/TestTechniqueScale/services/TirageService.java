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

/**
 * cette classe est un service mettant en œuvre la logique de calcul des gagnants du concours de loto
 */
@Service
public class TirageService {
    /**
     * Retourne liste des gagnants d'un concours de loto
     * @param file fichier csv contenant les données des joueurs (name , grilles).
     * @param winingGrille la grille gagnante (7 numéros , un numéro chance)
     * @param rang le rang des gagnants souhaité
     * @return
     */
    public List<Gagnant> getWinners(File file, Grille winingGrille, byte rang){
        List<Gagnant> gagnants=new ArrayList<>();
        // Lecture de fichier csv
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> r = reader.readAll();

            for (int i=1; i<r.size();i++
            ) {
                // chaque ligne réprenste un joeur , on doit faire l'adaptation par rapport la classe joeur
                Joeur joeur=Joeur.LineToJoeur(r.get(i));
                if(joeur!=null) {
                    // s'il ya aucun probléme avec les données de joeurs on boucle sur les grilles fournis
                    Grille[] joeur_grilles=joeur.getGrilles();
                    for(int j=0;j<joeur_grilles.length;j++){
                        byte joeur_rang=joeur_grilles[j].getRange(winingGrille);
                        // si le joeur a un rang acceptable par le client on l'ajoute au liste des gagnants
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
