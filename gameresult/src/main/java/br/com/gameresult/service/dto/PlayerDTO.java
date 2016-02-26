package br.com.gameresult.service.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class PlayerDTO {

	private String name;

	private int killed;

	private int died;

	private Date date;

	private int award;

	private HashMap<String, ArmDTO> countArm = new HashMap<String, ArmDTO>();

	public PlayerDTO(String name, Date date, boolean killed) {
		this.name = name;
		this.date = date;
		if (killed) {
			addKilled();
		} else {
			addDied();
		}
	}

	public int getKilled() {
		return killed;
	}

	public void addKilled() {
		this.killed++;
	}

	public int getDied() {
		return died;
	}

	public void addDied() {
		this.died++;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public void addArmUse(String armUse) {
		ArmDTO arm = this.countArm.get(armUse);
		if (arm != null) {
			arm.count();
		} else {
			arm = new ArmDTO(armUse);
		}
		this.countArm.put(armUse, arm);
	}

	public int getAward() {
		return award;
	}

	public void setAward(int award) {
		this.award = award;
	}

	public String getWeaponLiked() {
		Collection<ArmDTO> weapons = countArm.values();
		if (weapons != null && weapons.size() > 0) {
			Optional<ArmDTO> optional = weapons.stream().max(
					(w1, w2) -> w1.getCont().compareTo(w2.getCont()));
			return optional.get().getArm();
		}
		
		return null;

	}

}
