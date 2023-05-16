package co.latelier.tennis.application;

import co.latelier.tennis.domain.player.Country;
import co.latelier.tennis.domain.player.Player;

import java.util.List;

public interface PlayerService {

    List<Player> findAll();

    Player findById(Long id);

    Double averageBodyMassIndex();

    Country countryWithinBestVictoriesRatio();

    double playerHeightMedian();
}
