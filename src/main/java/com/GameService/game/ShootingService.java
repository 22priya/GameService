package com.GameService.game;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.exception.ShootingNotExistException;
import com.GameService.player.Player;
import com.GameService.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShootingService {
    @Autowired
    private ShootingRepository shootingRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Shooting createShooting(Shooting shooting)
    {
        return shootingRepository.save(shooting);
    }

    public Shooting addPlayerToShootingGame(Integer shootingGameId, Integer playerId) throws Exception {
        Optional<Player> player=playerRepository.findById(playerId);
        if(player.isEmpty())
            throw new PlayerNotExistException("Player with Id "+playerId+" do not exist");
        Optional<Shooting> shooting=shootingRepository.findById(shootingGameId);
        if(shooting.isEmpty())
            throw new ShootingNotExistException("ShootingGame with id "+shootingGameId+ "do not exist");
        if(shooting.get().getPlayers().stream().filter(x->x.getId().equals(playerId)).count()>0)
            throw new Exception("Player is already added in this game");
        shooting.get().addPlayer(player.get());
        playerRepository.save(player.get());
        return shootingRepository.save(shooting.get());
    }
}
