package br.com.gameresult.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;
import br.com.gameresult.exception.DateInvalidFormatException;
import br.com.gameresult.service.dto.GameInProgressDTO;
import br.com.gameresult.util.FileUtil;

@Service
public class GameReviewServiceImpl implements GameReviewService {

	private GameInProgressDTO gameInProgress = null;

	@Override
	public AnalyzeLogResponse analyzeLog(AnalyzeLogRequest analyzeLogRequest) {
		InputStream arquivo = extrair(analyzeLogRequest.getBase64());
		lerArquivo(arquivo);
		return null;
	}

	private void lerArquivo(InputStream file) {
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			processaLinha(scanner.nextLine());
		}
		gameInProgress = null;
	}

	private void processaLinha(String line) {
		TypeOfLineEnum typeOfLine = TypeOfLineEnum.getTypeThisLine(line);

		if (isStartNewGame(typeOfLine)) {
			startGame(line);
			return;
		}

		if (isPlayerKiller(typeOfLine)) {
			gameInProgress.setPlayerKiller(line);
			return;
		}
		
		

		System.out.println(line);

	}

	private boolean isPlayerKiller(TypeOfLineEnum typeOfLine) {
		return gameInProgress != null && typeOfLine != null
				&& typeOfLine.equals(TypeOfLineEnum.PLAYER_KILLER);
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
