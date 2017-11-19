package fr.stl.dto;

/**
 * Classe SummonerDTO lié à l'API RIOT
 */
public class SummonerDTO {

    /** ID of the summoner icon associated with the summoner. */
    private int profileIconId;

    /** Summoner name. */
    private String name;

    /** Summoner level associated with the summoner. */
    private long summonerLevel;

    /** Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change */
    private long revisionDate;

    /** Summoner ID. */
    private long id;

    /** Account ID. */
    private long accountId;

    /**
     * @return the profileIconId
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     * @param profileIconId the profileIconId to set
     */
    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
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
     * @return the summonerLevel
     */
    public long getSummonerLevel() {
        return summonerLevel;
    }

    /**
     * @param summonerLevel the summonerLevel to set
     */
    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    /**
     * @return the revisionDate
     */
    public long getRevisionDate() {
        return revisionDate;
    }

    /**
     * @param revisionDate the revisionDate to set
     */
    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the accountId
     */
    public long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

}
