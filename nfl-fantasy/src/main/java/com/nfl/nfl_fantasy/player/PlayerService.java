package com.nfl.nfl_fantasy.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Retorna todos os jogadores cadastrados.
     */
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Busca um jogador pelo ID.
     */
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Jogador com ID " + id + " não encontrado."));
    }

    /**
     * Retorna jogadores de um determinado time.
     */
    public List<Player> getPlayersByTeam(String teamName) {
        return playerRepository.findByTeam(teamName);
    }

    /**
     * Busca jogadores por nome (filtro parcial e case insensitive).
     */
    public List<Player> getPlayersByName(String nameFragment) {
        return playerRepository.findByPlayerNameContainingIgnoreCase(nameFragment);
    }

    /**
     * Retorna jogadores por posição.
     */
    public List<Player> getPlayersByPosition(String position) {
        return playerRepository.findByPosition(position);
    }

    /**
     * Retorna jogadores filtrando por time e posição.
     */
    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findByTeamAndPosition(team, position);
    }

    /**
     * Adiciona um novo jogador, garantindo que não haja duplicação por nome, temporada e time.
     */
    public Player addPlayer(Player player) {
        playerRepository.findByPlayerNameAndSeasonAndTeam(
                player.getPlayerName(), player.getSeason(), player.getTeam()
        ).ifPresent(existingPlayer -> {
            throw new IllegalStateException(
                    String.format("Já existe um jogador %s no time %s na temporada %d.",
                            existingPlayer.getPlayerName(),
                            existingPlayer.getTeam(),
                            existingPlayer.getSeason())
            );
        });

        return playerRepository.save(player);
    }

    /**
     * Atualiza um jogador existente.
     */
    @Transactional
    public Player updatePlayer(Long id, Player updatedData) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("Jogador com ID " + id + " não encontrado."));

        // Atualiza os campos necessários
        existingPlayer.setPlayerName(updatedData.getPlayerName());
        existingPlayer.setFantasyPoints(updatedData.getFantasyPoints());
        existingPlayer.setSeason(updatedData.getSeason());
        existingPlayer.setAge(updatedData.getAge());
        existingPlayer.setTeam(updatedData.getTeam());
        existingPlayer.setGames(updatedData.getGames());
        existingPlayer.setGamesStarted(updatedData.getGamesStarted());
        existingPlayer.setFantPt(updatedData.getFantPt());
        existingPlayer.setPpr(updatedData.getPpr());
        existingPlayer.setFantasyPtsPerGame(updatedData.getFantasyPtsPerGame());
        existingPlayer.setPprPerGame(updatedData.getPprPerGame());
        existingPlayer.setPosition(updatedData.getPosition());

        return existingPlayer; // Como está em contexto @Transactional, as alterações são persistidas automaticamente.
    }

    /**
     * Exclui um jogador pelo ID.
     */
    @Transactional
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new IllegalStateException("Jogador com ID " + id + " não encontrado para exclusão.");
        }
        playerRepository.deleteById(id);
    }
}
