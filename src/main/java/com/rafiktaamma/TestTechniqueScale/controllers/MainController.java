package com.rafiktaamma.TestTechniqueScale.controllers;


import com.rafiktaamma.TestTechniqueScale.exceptions.IllegalGrilleException;
import com.rafiktaamma.TestTechniqueScale.model.Gagnant;
import com.rafiktaamma.TestTechniqueScale.model.Grille;
import com.rafiktaamma.TestTechniqueScale.services.FileService;
import com.rafiktaamma.TestTechniqueScale.services.TirageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
       return "upload";
   }
/*
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }*/

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<Gagnant>> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Byte rang, @RequestParam("grille") String WiningGrille) throws IllegalGrilleException {

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
        System.out.println(winners);
        System.out.println(winners.get(0).getGrille().getNumbers().hashCode());
        return ResponseEntity.ok(winners);
    }

    @GetMapping(value = "/test")
    public String test(){
        byte[] ba2 = {1,2,3,4,5};
        return Arrays.toString(ba2);
    }

}

