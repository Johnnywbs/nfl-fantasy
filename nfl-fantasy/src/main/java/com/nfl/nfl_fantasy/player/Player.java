package com.nfl.nfl_fantasy.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(
        name = "players_fantasy_stats",
        uniqueConstraints = @UniqueConstraint(columnNames = {"player_name", "season", "team"})
)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_name", nullable = false)
    @JsonProperty("player_name")
    private String playerName;

    @Column(name = "fantasy_points")
    @JsonProperty("fantasy_points")
    private Double fantasyPoints;

    @Column(nullable = false)
    private int season;

    private int age;

    @Column(nullable = false)
    private String team;

    private int games;

    @Column(name = "games_started")
    @JsonProperty("games_started")
    private int gamesStarted;

    @Column(name = "fant_pt")
    @JsonProperty("fant_pt")
    private Double fantPt;

    private Double ppr;

    @Column(name = "fantasy_pts_per_game")
    @JsonProperty("fantasy_pts_per_game")
    private Double fantasyPtsPerGame;

    @Column(name = "ppr_per_game")
    @JsonProperty("ppr_per_game")
    private Double pprPerGame;

    private String position;

    // --- Construtores ---
    public Player() {
        // Construtor padrão necessário para JPA
    }

    public Player(
            String playerName,
            Double fantasyPoints,
            int season,
            int age,
            String team,
            int games,
            int gamesStarted,
            Double fantPt,
            Double ppr,
            Double fantasyPtsPerGame,
            Double pprPerGame,
            String position
    ) {
        this.playerName = playerName;
        this.fantasyPoints = fantasyPoints;
        this.season = season;
        this.age = age;
        this.team = team;
        this.games = games;
        this.gamesStarted = gamesStarted;
        this.fantPt = fantPt;
        this.ppr = ppr;
        this.fantasyPtsPerGame = fantasyPtsPerGame;
        this.pprPerGame = pprPerGame;
        this.position = position;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Double getFantasyPoints() {
        return fantasyPoints;
    }

    public void setFantasyPoints(Double fantasyPoints) {
        this.fantasyPoints = fantasyPoints;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public void setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public Double getFantPt() {
        return fantPt;
    }

    public void setFantPt(Double fantPt) {
        this.fantPt = fantPt;
    }

    public Double getPpr() {
        return ppr;
    }

    public void setPpr(Double ppr) {
        this.ppr = ppr;
    }

    public Double getFantasyPtsPerGame() {
        return fantasyPtsPerGame;
    }

    public void setFantasyPtsPerGame(Double fantasyPtsPerGame) {
        this.fantasyPtsPerGame = fantasyPtsPerGame;
    }

    public Double getPprPerGame() {
        return pprPerGame;
    }

    public void setPprPerGame(Double pprPerGame) {
        this.pprPerGame = pprPerGame;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
