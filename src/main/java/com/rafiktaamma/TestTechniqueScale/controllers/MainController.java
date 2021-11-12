package com.rafiktaamma.TestTechniqueScale.controllers;


import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import com.rafiktaamma.TestTechniqueScale.model.Gagnant;
import com.rafiktaamma.TestTechniqueScale.model.Grille;
import com.rafiktaamma.TestTechniqueScale.services.FileService;
import com.rafiktaamma.TestTechniqueScale.services.TirageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {

  @Autowired
    FileService fileService;
  @Autowired
    TirageService tirageService;


   @GetMapping("/")
    public String index(){
       return "Working api";
   }

    /**
     * EndPoint permet d'avoir la liste des gagnants d'une compétion loto
     * @param file fichier csv contenant les données des joueurs (name , grilles).
     * @param rang le rang des gagnants souhaité
     * @param WiningGrille la grille gagnante (7 numéros , un numéro chance)
     * @return une liste des gagnantd (Répresenté par la classe gagnant)
     * @throws IllegalGrilleException Lève une exception si le service n'as pas pu créer une grille à partir la chaine de caractéres fournit
     */
    @PostMapping(value = "/api/winners", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<Gagnant>> getWinners(@RequestParam("file") MultipartFile file, @RequestParam Byte rang, @RequestParam("grille") String WiningGrille) throws IllegalGrilleException {

        String path = fileService.uploadFile(file);
        System.out.println("the path is " + path);
        System.out.println("the grille is " + new Grille(WiningGrille));
        System.out.println("the rang is " + rang);
        List<Gagnant> winners = null;
        try {
            winners = this.tirageService.getWinners(new File(path), new Grille(WiningGrille), rang);

        } catch (IllegalGrilleException e) {
            e.printStackTrace();
        }
        if(winners==null || winners.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Winners not found"
        );
        else return ResponseEntity.ok(winners);
    }



}

