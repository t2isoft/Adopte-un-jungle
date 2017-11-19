package fr.stl.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * Classe abstraite de connexion à l'API
 */
public abstract class AbstractApi {
    
    /** Le logger */
    private final static Logger LOG = Logger.getLogger("AbstractApi");

    /**
     * Connexion à l'API
     * @param uri url à contacter
     * @return les données en json
     */
    public static String connection(String uri) {
        String retourJson = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Riot-Token", "RGAPI-1f700745-f69d-4e01-8f39-c82c34aae67b");
            conn.setRequestProperty("Content-Type", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuffer response = new StringBuffer();
            while ((output = br.readLine()) != null) {
                 response.append(output);
            }
            br.close();
            retourJson = response.toString();
            conn.disconnect();
        } catch (MalformedURLException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }

        return retourJson;
    }

}
