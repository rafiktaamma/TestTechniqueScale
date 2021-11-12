package com.rafiktaamma.TestTechniqueScale.services;

import com.rafiktaamma.TestTechniqueScale.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    /**
     * Enregistrez le fichier téléchargé dans ce dossier
     * le chemin d'accès à ce répertoire est défini dans le fichier application.properties
     */
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    /**
     * Cette fonction permet de télécharger un fichier vers le répertoire définit par "uploadDir"
     * @param file fichier
     * @return le chemin vers le fichier télechargé
     */
    public String uploadFile(MultipartFile file) {
        Path copyLocation=null;
        try {
             copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
       return copyLocation.toString();
    }
}

