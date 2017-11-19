package fr.stl.util;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Classe Utilitaire de gestion de fichier
 */
public class FileUtil {

    /** Le Logger */
    private final static Logger LOG = Logger.getLogger("FileUtil");

    /**
     * Récupération du contenu d'un fichier
     * @param file le fichier
     * @return le contenu
     */
    static public String getFile(File file) {
        if (file == null || file.isDirectory()) {
            return null;
        }
        StringBuilder result = new StringBuilder("");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (result.length() > 0) {
                    result.append("\n");
                }
                result.append(line);
            }
            scanner.close();
        } catch (IOException e) {
            LOG.error(e);
        }
        return result.toString();

    }

}
