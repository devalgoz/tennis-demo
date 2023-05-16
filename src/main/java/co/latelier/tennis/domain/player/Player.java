package co.latelier.tennis.domain.player;

import lombok.Builder;

@Builder
public record Player(
        Long id,
        String firstname,
        String lastname,
        String shortname,
        Sex sex,
        Country country,
        String picture,
        Data data) {

    private static final int GRAMS_PER_KILO = 1000;
    private static final int CENTIMETERS_PER_METER = 100;
    private static final int VICTORY = 1;

    public Double getBbodyMassIndex() {
        return (data().weight().doubleValue() / GRAMS_PER_KILO)/
                Math.pow(data().height().doubleValue() / CENTIMETERS_PER_METER, 2);
    }

    public long getNumberOfVictories() {
        return data
                .points();
    }
}
