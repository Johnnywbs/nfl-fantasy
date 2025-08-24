package com.nfl.nfl_fantasy.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
@CrossOrigin(origins = "http://localhost:3000") // Permite requisições do seu frontend
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Nenhuma alteração necessária aqui.
    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String name) {

        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (team != null) {
            return playerService.getPlayersByTeam(team);
        } else if (name != null) {
            return playerService.getPlayersByName(name);
        } else if (position != null) {
            return playerService.getPlayersByPosition(position);
        } else {
            return playerService.getAllPlayers();
        }
    }

    // Nenhuma alteração necessária aqui.
    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
        try {
            Player player = playerService.getPlayerById(playerId);
            return ResponseEntity.ok(player);
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Nenhuma alteração necessária aqui. O @JsonProperty na entidade Player cuida da conversão.
    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        try {
            Player createdPlayer = playerService.addPlayer(player);
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Nenhuma alteração necessária aqui.
    @PutMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(
            @PathVariable Long playerId,
            @RequestBody Player updatedPlayerData) {
        try {
            Player resultPlayer = playerService.updatePlayer(playerId, updatedPlayerData);
            return ResponseEntity.ok(resultPlayer);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Nenhuma alteração necessária aqui.
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long playerId) {
        try {
            playerService.deletePlayer(playerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        }
    }
}