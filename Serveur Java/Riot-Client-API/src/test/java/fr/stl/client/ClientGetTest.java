package fr.stl.client;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.stl.dto.LeagueListDTO;
import fr.stl.dto.SummonerDTO;

/**
 * Test du client Get
 */
public class ClientGetTest {
    
    /** Client Get */
    private ClientGet clientGet = new ClientGet();

    /**
     * Test de getAllLeagueBySummonerId cas nominal
     */
    @Test
    public void testGetAllLeagueBySummonerIdNominal() {
        SummonerDTO summoner = clientGet.getSummonerBySummonerName("Didjo972");
        Assert.assertNotNull(summoner);
        List<LeagueListDTO> leagueListDTO = clientGet.getAllLeagueBySummonerId(Long.toString(summoner.getId()));
        Assert.assertNotNull(leagueListDTO);
    }
    
}
