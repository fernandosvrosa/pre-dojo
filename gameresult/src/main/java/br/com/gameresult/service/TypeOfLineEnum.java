package br.com.gameresult.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TypeOfLineEnum {

	START_GAME(
			"^([\\w/]+\\s[\\w:]+) (-) (New match) ([0-9]{5,20}) (has started)"), 
	PLAYER_KILLER(
			"^([\\w/]+\\s[\\w:]+) (-) ([\\w]+) (killed) ([\\w]+) (using) ([\\w]+)"), 
	MACHINE_KILLER(
			"^([\\w/]+\\s[\\w:]+) (-) (<WORLD>) (killed) ([\\w]+) (by) ([\\w]+)"), 
	END_GAME(
			"^([\\w/]+\\s[\\w:]+) (-) (Match) ([0-9]{5,20}) (has ended)");

	private String regex;
	private Pattern pattern;

	TypeOfLineEnum(String regex) {
		this.regex = regex;
		this.pattern = Pattern.compile(this.regex, Pattern.CASE_INSENSITIVE);
	}

	public static TypeOfLineEnum getTypeThisLine(String line) {
		for (TypeOfLineEnum typeThisLine : values()) {
			Matcher matcher = typeThisLine.getPattern().matcher(line);
			if (matcher.matches()) {
				return typeThisLine;
			}
		}

		return null;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
