package br.com.gameresult.dbo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import br.com.gameresult.controller.response.GameResultResponse;

public class ResultGames {

	private static HashMap<Long, List<GameResultResponse>> mapGameResult = new HashMap<Long, List<GameResultResponse>>();

	public static void addResult(Long gameId,
			GameResultResponse gameResultResponse) {
		List<GameResultResponse> gameResultResponses = mapGameResult
				.get(gameId);

		if (gameResultResponses == null) {
			gameResultResponses = new ArrayList<GameResultResponse>();
			gameResultResponses.add(gameResultResponse);
		} else {
			gameResultResponses.add(gameResultResponse);
		}

		mapGameResult.put(gameId, gameResultResponses);

	}

	public static List<GameResultResponse> getGameById(Long id) {
	    List<GameResultResponse> list = mapGameResult.get(id);
		return list;
	}
	
	public static Set<Long> getGameIds() {
      return mapGameResult.keySet();
  }
	
}
