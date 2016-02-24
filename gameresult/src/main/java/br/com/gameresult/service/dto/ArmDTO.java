package br.com.gameresult.service.dto;

public class ArmDTO {

	private String arm;

	private int cont;
	
	public ArmDTO(String arm) {
		this.arm = arm;
		count();
	}

	public String getArm() {
		return arm;
	}
	public int getCont() {
		return cont;
	}

	public void count() {
		this.cont++;
	}

}
