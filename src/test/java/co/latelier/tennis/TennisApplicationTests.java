package co.latelier.tennis;

import co.latelier.tennis.application.PlayerService;
import co.latelier.tennis.application.PlayerServiceImpl;
import co.latelier.tennis.domain.player.Country;
import co.latelier.tennis.domain.player.Player;
import co.latelier.tennis.domain.player.PlayerNotFoundException;
import co.latelier.tennis.domain.player.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TennisApplicationTests {

    private final List<Player> players = PlayerFactory.getPlayers();

    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        playerService = new PlayerServiceImpl(playerRepository);
        when(playerRepository.getPlayers()).thenReturn(players);
    }

    @Test
    public void findById_should_throw_not_found() {
        when(playerRepository.getPlayers()).thenReturn(players);
        Exception exception = Assertions.assertThrows(PlayerNotFoundException.class, () -> playerService.findById(599L));
        String expectedMessage = "Player within the provided ID does not exist";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findById_should_succeed() {
        final Long id = 5L;
        Player player = playerService.findById(id);
        Assertions.assertTrue(player.id().equals(id) && player.country().code().equals(PlayerFactory.FRANCE), "Should return ");
    }

    @Test
    public void averageBodyMassIndex_should_succeed() {
        final double expectedResult = 23.33;
        final double averageBodyMassIndex = playerService.averageBodyMassIndex();
        Assertions.assertEquals(expectedResult, averageBodyMassIndex);
    }

    @Test
    public void countryWithinBestVictoriesRatio_should_succeed() {
        final Country country = playerService.countryWithinBestVictoriesRatio();
        Assertions.assertEquals(country.code(), PlayerFactory.FRANCE);
    }

    @Test
    public void playerHeightMedian_should_succeed() {
        final double expectedResult = 179;
        final double result = playerService.playerHeightMedian();
        Assertions.assertEquals(result, expectedResult);
    }

}
