package fr.stl.dto;

import java.util.List;

/**
 * Classe LeagueListDTO lié à l'API RIOT
 */
public class LeagueListDTO {

    /**  */
    private String tier;

    /** File */
    private String queue;

    /** Nom de la file */
    private String name;

    /** List des leagues */
    private List<LeaguePositionDTO> entries;

    /**
     * @return the tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * @param tier the tier to set
     */
    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * @return the queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(String queue) {
        this.queue = queue;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the entries
     */
    public List<LeaguePositionDTO> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<LeaguePositionDTO> entries) {
        this.entries = entries;
    }

}
