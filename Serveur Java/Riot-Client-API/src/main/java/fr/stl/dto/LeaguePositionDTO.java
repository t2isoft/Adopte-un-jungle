package fr.stl.dto;

/**
 * Classe LeaguePositionDTO lié à l'API RIOT
 */
public class LeaguePositionDTO {

    /** Le rang */
    private String rank;

    /** */
    private boolean hotStreak;

    /** */
    private MiniSeriesDTO miniSeries;

    /** Nombres de victoire */
    private int wins;

    /** Vétéran de la ligue */
    private boolean veteran;

    /** Nombres de défaite */
    private int losses;

    /**  */
    private boolean freshBlood;

    /** Nom d'équipe ou joueur (1v1) */
    private String playerOrTeamName;

    /** Inactif */
    private boolean inactive;

    /** Id du joueur ou de l'équipe */
    private String playerOrTeamId;

    /** Point de league */
    private int leaguePoints;

    /**
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @return the hotStreak
     */
    public boolean isHotStreak() {
        return hotStreak;
    }

    /**
     * @param hotStreak the hotStreak to set
     */
    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    /**
     * @return the miniSeries
     */
    public MiniSeriesDTO getMiniSeries() {
        return miniSeries;
    }

    /**
     * @param miniSeries the miniSeries to set
     */
    public void setMiniSeries(MiniSeriesDTO miniSeries) {
        this.miniSeries = miniSeries;
    }

    /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * @param wins the wins to set
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * @return the veteran
     */
    public boolean isVeteran() {
        return veteran;
    }

    /**
     * @param veteran the veteran to set
     */
    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    /**
     * @return the losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * @param losses the losses to set
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * @return the freshBlood
     */
    public boolean isFreshBlood() {
        return freshBlood;
    }

    /**
     * @param freshBlood the freshBlood to set
     */
    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    /**
     * @return the playerOrTeamName
     */
    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    /**
     * @param playerOrTeamName the playerOrTeamName to set
     */
    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    /**
     * @return the inactive
     */
    public boolean isInactive() {
        return inactive;
    }

    /**
     * @param inactive the inactive to set
     */
    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    /**
     * @return the playerOrTeamId
     */
    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    /**
     * @param playerOrTeamId the playerOrTeamId to set
     */
    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    /**
     * @return the leaguePoints
     */
    public int getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * @param leaguePoints the leaguePoints to set
     */
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

}
