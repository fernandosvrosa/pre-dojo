package br.com.gameresult.controller.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyzeLogRequest implements Serializable {

	private static final long serialVersionUID = -689527329843413028L;

	private String fileName;
	private String fileType;
	private String fileSize;
	private String base64;
	
	
	public String getFileName() {
		return fileName;
	}
	
	@JsonProperty(value = "filename")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	
	@JsonProperty(value = "filetype")
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	
	@JsonProperty(value = "filesize")
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getBase64() {
		return base64;
	}
	
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
		


}
