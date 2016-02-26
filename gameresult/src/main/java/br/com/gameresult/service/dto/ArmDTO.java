package br.com.gameresult.service.dto;

public class ArmDTO {

	private String arm;

	private Integer cont = 0;
	
	public ArmDTO(String arm) {
		this.arm = arm;
		count();
	}

	public String getArm() {
		return arm;
	}
	public Integer getCont() {
		return cont;
	}

	public void count() {
		this.cont++;
	}

}
