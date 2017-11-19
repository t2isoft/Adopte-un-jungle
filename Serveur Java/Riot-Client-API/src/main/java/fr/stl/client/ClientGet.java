package fr.stl.client;

import java.util.Map;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.stl.dto.LeagueListDTO;
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
        return gson.fromJson(json, SummonerDTO.class);
    }
    
    /**
     * Récupération de la liste des ligues d'un Summoner
     * @param summonerId l'id du summoner
     * @return un LeagueListDTO
     */
    public List<LeagueListDTO> getAllLeagueBySummonerId(final String summonerId) {
        String json = connection(URL + "/league/v3/leagues/by-summoner/" + summonerId);
        Type listType = new TypeToken<ArrayList<LeagueListDTO>>(){}.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Retrieve the Summoner profile icon
     * @param summonerProfileIconId the summoner profile icon id
     * @return the profile icon details
     */
    public ProfileIconDetailsDTO getProfileIconById(final String summonerProfileIconId) {
        String json = connection(URL + " /lol/static-data/v3/profile-icons");
        ProfileIconDataDTO profileIcons = gson.fromJson(json, ProfileIconDataDTO.class);
        for(Map.Entry<String, ProfileIconDetailsDTO> entry : profileIcons.getData().entrySet()) {
            if (entry.getKey() == summonerProfileIconId) {
                return entry.getValue();
            }
        }
        return null;
    }
    

}
