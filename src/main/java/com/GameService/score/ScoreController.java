package com.GameService.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/score")
public class ScoreController {

    @Autowired
    private ScoreService service;

    @GetMapping("/{playerId}")
    public List<Score> findByPlayerId(Integer playerId){
        return this.service.getAllScoresByPlayer(playerId);
    }
}
