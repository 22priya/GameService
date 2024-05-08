package com.GameService;

import com.GameService.exception.PlayerNotExistException;
import com.GameService.game.Game;
import com.GameService.game.Shooting;
import com.GameService.observer.GameObserver;
import com.GameService.observer.TopNScores;
import com.GameService.player.Player;
import com.GameService.score.Score;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class GameServiceApplication {

	public static void main(String[] args) throws PlayerNotExistException {
		SpringApplication.run(GameServiceApplication.class, args);
		Game game=new Shooting("QuarterFinal");
		GameObserver top5Scorers=new TopNScores(5);
		game.addObserver(top5Scorers);

		game.addPlayer(new Player(1,"Alisha",25,"alisha@noemail"));
		game.addPlayer(new Player(2,"Bob",25,"bob@noemail"));
		game.addPlayer(new Player(3,"Catty",25,"catty@noemail"));
		game.addPlayer(new Player(4,"Donna",25,"donna@noemail"));
		game.addPlayer(new Player(5,"Elisa",25,"elisa@noemail"));
		game.addPlayer(new Player(6,"Fany",25,"fany@noemail"));
		game.addPlayer(new Player(7,"Greg",25,"greg@noemail"));
		game.addPlayer(new Player(8,"Han",25,"han@noemail"));
		game.addPlayer(new Player(9,"Iby",25,"iby@noemail"));
		game.addPlayer(new Player(10,"Jenny",25,"jenny@noemail"));

		Map<Integer,Integer> scoresMapping=new HashMap<>();
		Random random=new Random();
		System.out.println("All scores: ");
		for(int i=0;i<10;i++)
		{
			int x=random.nextInt(1,100);
			scoresMapping.put(i+1, x);
			System.out.println(x);
		}

		game.gameFinishedProcessing(scoresMapping);
		PriorityQueue<Score> scores=top5Scorers.getResults();

		while(!scores.isEmpty())
		{
			System.out.println(scores.poll());
		}
	}
}
