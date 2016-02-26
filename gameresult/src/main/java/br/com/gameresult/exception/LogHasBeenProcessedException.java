package br.com.gameresult.exception;

public class LogHasBeenProcessedException extends RuntimeException {

	private Long gameId;

	private static final long serialVersionUID = 100964445395430133L;

	public LogHasBeenProcessedException(Long gameId) {
		super();
		this.gameId = gameId;
	}

	public Long getGameId() {
		return gameId;
	}

}
