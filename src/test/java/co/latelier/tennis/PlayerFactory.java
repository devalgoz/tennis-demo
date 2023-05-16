package co.latelier.tennis;

import co.latelier.tennis.domain.player.Country;
import co.latelier.tennis.domain.player.Data;
import co.latelier.tennis.domain.player.Player;

import java.util.List;

public class PlayerFactory {

    public static final String FRANCE = "FRANCE";

    public static final String ITALY = "ITALY";

    public static List<Player> getPlayers() {
        Country fr = new Country(FRANCE, null);
        Country it = new Country(ITALY, null);
        return List.of(
                newPlayer(5L, 55, 1264, 178, 67000, fr),
                newPlayer(10L, 118, 26, 186, 88000, it),
                newPlayer(64L, 2, 3777, 179, 75000, fr)
        );
    }

    public static Player newPlayer(
            final Long id,
            final Integer rank,
            final int points,
            final int height,
            final int weight,
            final Country country
    ) {
        return Player.builder()
                .id(id)
                .country(country)
                .data(Data.builder()
                        .rank(rank)
                        .points(points)
                        .height(height)
                        .weight(weight)
                        .build()
                )
                .build();

    }


}
