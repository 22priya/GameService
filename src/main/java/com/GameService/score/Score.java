package com.GameService.score;

import com.GameService.game.Shooting;
import com.GameService.player.Player;
import jakarta.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Shooting shooting;
    private Integer score;

    public Score(Player player, Integer score) {
        this.player = player;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", player=" + player +
                ", score=" + score +
                '}';
    }
}
