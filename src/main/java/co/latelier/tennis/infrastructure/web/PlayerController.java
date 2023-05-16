package co.latelier.tennis.infrastructure.web;

import co.latelier.tennis.domain.player.Country;
import co.latelier.tennis.domain.player.Player;
import co.latelier.tennis.application.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Player findById(@PathVariable("id") Long id) {
        return playerService.findById(id);
    }

    @GetMapping("/stats/averageBodyMassIndex")
    public double averageBodyMassIndex() {
        return playerService.averageBodyMassIndex();
    }

    @GetMapping("/stats/countryWithinBestVictoriesRatio")
    public Country countryWithinBestVictoriesRatio() {
        return playerService.countryWithinBestVictoriesRatio();
    }

    @GetMapping("/stats/playerHeightMedian")
    public double playerHeightMedian() {
        return playerService.playerHeightMedian();
    }
}
