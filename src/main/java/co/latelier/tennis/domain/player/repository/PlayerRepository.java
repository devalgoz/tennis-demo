package co.latelier.tennis.domain.player.repository;

import co.latelier.tennis.domain.player.Player;

import java.util.List;

public interface PlayerRepository {
    List<Player> getPlayers();
}
