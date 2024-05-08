package com.GameService.game;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.exception.ShootingNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/shooting")
public class ShootingController {
    @Autowired
    private ShootingService shootingService;

    @PostMapping
    public Shooting create(@RequestBody Shooting shooting)
    {
        return this.shootingService.createShooting(shooting);
    }

    @PatchMapping("/{shootingId}/players/{playerId}")
    public Shooting addPlayer(@PathVariable Integer shootingId,@PathVariable Integer playerId) throws Exception {
            return this.shootingService.addPlayerToShootingGame(shootingId,playerId);
    }
}
