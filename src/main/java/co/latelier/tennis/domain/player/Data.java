package co.latelier.tennis.domain.player;

import lombok.Builder;

import java.util.List;

@Builder
public record Data(
        Integer rank,
        Integer points,
        Integer weight,
        Integer height,
        Integer age,
        List<Integer> last) {
}
