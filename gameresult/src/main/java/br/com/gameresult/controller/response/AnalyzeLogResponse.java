package br.com.gameresult.controller.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyzeLogResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -72616348330614597L;
	
	private Long id;
	
	public AnalyzeLogResponse(Long id) {
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}

	@JsonProperty(value = "id")
	@JsonInclude(Include.NON_NULL)
	public void setId(Long id) {
		this.id = id;
	}

}
