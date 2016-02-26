package br.com.gameresult.controller.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogResponse implements Serializable {
  
  private static final long serialVersionUID = -2646074019716240855L;
  
  List<GameResultResponse> gameResult;
  
  public LogResponse(List<GameResultResponse> gameResult) {
    this.gameResult = gameResult;
  }

  @JsonProperty(value = "gameResult")
  @JsonInclude(Include.NON_NULL)
  public List<GameResultResponse> getGameResult() {
    return gameResult;
  }

  

}
