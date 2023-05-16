package co.latelier.tennis.infrastructure.provider.repository;

import co.latelier.tennis.domain.player.Player;
import co.latelier.tennis.domain.player.TennisExcpetion;
import co.latelier.tennis.domain.player.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class PlainFilePlayerRepository implements PlayerRepository {

    private static final String DATA_FILE_LOCATION = "headtohead.json";

    private Data data;

    @Override
    public List<Player> getPlayers() {
        return loadData().players();
    }

    private Data loadData() {
        if (data == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                data = objectMapper.readValue(new ClassPathResource(DATA_FILE_LOCATION).getInputStream(), Data.class);
            } catch (IOException e) {
                throw new TennisExcpetion("Failed to load data ", e);
            }
        }
        return data;
    }

    private record Data(List<Player> players) {}

}
