package br.com.gameresult.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;
import br.com.gameresult.controller.response.GameResultResponse;
import br.com.gameresult.dbo.ResultGames;
import br.com.gameresult.service.dto.GameInProgressDTO;
import br.com.gameresult.service.dto.PlayerDTO;
import br.com.gameresult.util.FileUtil;

@Service
public class GameReviewServiceImpl implements GameReviewService {
	
	
	private GameInProgressDTO gameInProgress = null;
	private Scanner scanner;

	@Override
	public AnalyzeLogResponse analyzeLog(AnalyzeLogRequest analyzeLogRequest) {
		InputStream arquivo = extrair(analyzeLogRequest.getBase64());
		readFile(arquivo);
		return null;
	}

	private void readFile(InputStream file) {
		scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			processesLine(scanner.nextLine());
		}
		gameInProgress = null;
	}

	private void processesLine(String line) {
		TypeOfLineEnum typeOfLine = TypeOfLineEnum.getTypeThisLine(line);

		if (isStartNewGame(typeOfLine)) {
			startGame(line);
			return;
		}

		if (isPlayerKiller(typeOfLine)) {
			gameInProgress.setPlayerKiller(line);
			return;
		}

		if (isMachineKiller(typeOfLine)) {
			gameInProgress.setMachineKiller(line);
			return;
		}

		if (isEndGame(typeOfLine)) {
			gameInProgress.setEndGame(line);
			addResults();
			gameInProgress = null;
			return;
		}

	}

	private void addResults() {
		GameResultResponse response = null;
		Collection<PlayerDTO> historicalPlayers = gameInProgress.getHistoricalPlayers().values();
		for (PlayerDTO player : historicalPlayers) {
			response = new GameResultResponse(player.getName(), player.getWeaponLiked(), player.getKilled(), player.getDied(), player.getAward());
			ResultGames.addResult(gameInProgress.getGameId(), response);
		}
		
	}

	private boolean isPlayerKiller(TypeOfLineEnum typeOfLine) {
		return gameInProgress != null && typeOfLine != null
				&& typeOfLine.equals(TypeOfLineEnum.PLAYER_KILLER);
	}

	private boolean isMachineKiller(TypeOfLineEnum typeOfLine) {
		return gameInProgress != null && typeOfLine != null
				&& typeOfLine.equals(TypeOfLineEnum.MACHINE_KILLER);
	}

	private boolean isEndGame(TypeOfLineEnum typeOfLine) {
		return gameInProgress != null && typeOfLine != null
				&& typeOfLine.equals(TypeOfLineEnum.END_GAME);
	}

	private boolean isStartNewGame(TypeOfLineEnum typeOfLine) {
		return gameInProgress == null && typeOfLine != null
				&& typeOfLine.equals(TypeOfLineEnum.START_GAME);
	}

	private void startGame(String line) {
		gameInProgress = new GameInProgressDTO(line);

	}

	private InputStream extrair(String string) {
		final byte[] content = FileUtil.convert(string);
		return new ByteArrayInputStream(content);
	}

}
