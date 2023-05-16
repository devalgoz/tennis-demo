package co.latelier.tennis.application;

import co.latelier.tennis.domain.player.Country;
import co.latelier.tennis.domain.player.Player;
import co.latelier.tennis.domain.player.PlayerNotFoundException;
import co.latelier.tennis.domain.player.repository.PlayerRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository
                .getPlayers()
                .stream()
                .sorted(Comparator.comparing(player -> player.data().rank()))
                .toList();
    }

    @Override
    public Player findById(Long id) {
        return playerRepository
                .getPlayers()
                .stream()
                .filter(player -> player.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException("Player within the provided ID does not exist"));

    }

    @Override
    public Double averageBodyMassIndex() {
        final double result =
                playerRepository
                        .getPlayers()
                        .stream()
                        .mapToDouble(Player::getBbodyMassIndex)
                        .average()
                        .orElse(0);
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    @Override
    public Country countryWithinBestVictoriesRatio() {
        return playerRepository
                .getPlayers()
                .stream()
                .collect(Collectors.groupingBy(Player::country))
                .entrySet()
                .stream()
                .map(entry -> new Tuple(
                        entry.getKey(),
                        entry
                                .getValue()
                                .stream()
                                .map(Player::getNumberOfVictories)
                                .reduce(0L, Long::sum)
                ))
                .max(Comparator.comparing(Tuple::numberOfVictories))
                .orElse(new Tuple(null, null))
                .country();
    }

    @Override
    public double playerHeightMedian() {
        final List<Player> sorted =
                playerRepository
                        .getPlayers()
                        .stream()
                        .sorted(Comparator.comparing(player -> player.data().height()))
                        .toList();
        final int middle = playerRepository.getPlayers().size() / 2;
        return middle % 2 == 1
                ? sorted.get(middle).data().height()
                : (sorted.get(middle - 1).data().height() + sorted.get(middle).data().height()) / 2.0;
    }

    private record Tuple(Country country, Long numberOfVictories) {
    }

}
