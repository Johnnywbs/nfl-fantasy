package com.nfl.nfl_fantasy.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByPlayerNameContainingIgnoreCase(String playerName);

    List<Player> findByTeam(String team);

    List<Player> findByPosition(String position);

    List<Player> findByTeamAndPosition(String team, String position);

    Optional<Player> findByPlayerNameAndSeasonAndTeam(String playerName, int season, String team);
}