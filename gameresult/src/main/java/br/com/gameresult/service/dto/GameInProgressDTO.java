package br.com.gameresult.service.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.gameresult.exception.DateInvalidFormatException;
import br.com.gameresult.service.TypeOfLineEnum;

public class GameInProgressDTO {

	private Long gameId;

	private Date initialDate;

	private Date finalDate;

	private HashMap<String, PlayerDTO> historicalPlayers = new HashMap<String, PlayerDTO>();

	private HashMap<String, ArmDTO> countArm = new HashMap<String, ArmDTO>();

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	public GameInProgressDTO(String line) {
		Pattern pattern = TypeOfLineEnum.START_GAME.getPattern();
		Matcher matcher = pattern.matcher(line);

		if (matcher.find()) {
			try {
				this.initialDate = simpleDateFormat.parse(matcher.group(1));
			} catch (ParseException e) {
				throw new DateInvalidFormatException();
			}
			this.gameId = Long.parseLong(matcher.group(4));

		}
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public void setPlayerKiller(String line) {
		Pattern pattern = TypeOfLineEnum.PLAYER_KILLER.getPattern();
		Matcher matcher = pattern.matcher(line);

		if (matcher.find()) {
			Date data;
			try {
				data = simpleDateFormat.parse(matcher.group(1));
			} catch (ParseException e) {
				throw new DateInvalidFormatException();
			}
			addPlayerKilledInGame(matcher.group(3), data);
			addDeadPayerInGame(matcher.group(5));
			addArmUse(matcher.group(7));
		}

	}

	private void addArmUse(String armUse) {
		ArmDTO arm = countArm.get(armUse);
		if (arm != null) {
			arm.count();
		} else {
			arm = new ArmDTO(armUse);
		}
		countArm.put(armUse, arm);

	}

	private void addDeadPayerInGame(String deadPayer) {
		PlayerDTO dead = historicalPlayers.get(deadPayer);
		if (dead != null) {
			dead.addDied();
		} else {
			dead = new PlayerDTO(deadPayer, null, false);
		}
		historicalPlayers.put(deadPayer, dead);

	}

	private void addPlayerKilledInGame(String playerKilled, Date data) {
		PlayerDTO Killed = historicalPlayers.get(playerKilled);
		if (Killed != null) {
			Killed.addKilled();
			;
		} else {
			Killed = new PlayerDTO(playerKilled, data, true);
		}
		historicalPlayers.put(playerKilled, Killed);

	}

}
