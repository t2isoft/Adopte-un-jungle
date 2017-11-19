package fr.stl.client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.stl.dto.LeagueListDTO;
import fr.stl.dto.LeaguePositionDTO;
import fr.stl.dto.SummonerDTO;
import fr.stl.dto.profileIcon.ProfileIconDataDTO;
import fr.stl.dto.profileIcon.ProfileIconDetailsDTO;

/**
 * Classe de connexion à un ressources API en Get
 */
public class ClientGet extends AbstractApi {
    
    /** URL de l'api RIOT */
    static private String URL = "https://euw1.api.riotgames.com/lol";
    
    /** Gson */
    static private Gson gson = new Gson();
    
    /**
     * Récupération des informations d'un Summoner
     * @param summonerName le nom du summoner
     * @return un SummonerDTO
     */
    public SummonerDTO getSummonerBySummonerName(final String summonerName) {
        String json = connection(URL + "/summoner/v3/summoners/by-name/" + summonerName);
        if (json == null)
            return null;
        return gson.fromJson(json, SummonerDTO.class);
    }
    
    /**
     * Récupération de la liste des ligues d'un Summoner
     * @param summonerId l'id du summoner
     * @return un LeagueListDTO
     */
    public List<LeagueListDTO> getLeagueInAllQueuesBySummonerId(final String summonerId) {
        String json = connection(URL + "/league/v3/leagues/by-summoner/" + summonerId);
        Type listType = new TypeToken<ArrayList<LeagueListDTO>>(){}.getType();
        if (json == null)
            return null;
        return gson.fromJson(json, listType);
    }
    
    /**
     * Récupération de la liste des ligues d'un Summoner
     * @param summonerId l'id du summoner
     * @return un LeagueListDTO
     */
    public List<LeaguePositionDTO> getLeaguePositionInAllQueuesBySummonerId(final String summonerId) {
        String json = connection(URL + "/league/v3/positions/by-summoner/" + summonerId);
        Type listType = new TypeToken<ArrayList<LeaguePositionDTO>>(){}.getType();
        if (json == null)
            return null;
        return gson.fromJson(json, listType);
    }
    
    /**
     * Retrieve the Summoner profile icon
     * @param summonerProfileIconId the summoner profile icon id
     * @return the profile icon details
     */
    public ProfileIconDetailsDTO getProfileIconById(final String summonerProfileIconId) {
        String json = connection(URL + " /static-data/v3/profile-icons");
        if (json == null)
            return null;
        ProfileIconDataDTO profileIcons = gson.fromJson(json, ProfileIconDataDTO.class);
        for(Map.Entry<String, ProfileIconDetailsDTO> entry : profileIcons.getData().entrySet()) {
            if (entry.getKey() == summonerProfileIconId) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Download a file and save in the paramater savePath
     * exemple URL: "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png"
     * @param sourceURL path to dowload file
     * @param savePath path for save the downloaded file
     */
    /*
    public void getDataFromURL(String sourceURL, String savePath) {
        File forSave = new File(savePath);          
        URL url = new URL(sourceURL);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(forSave);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    */
    

}
