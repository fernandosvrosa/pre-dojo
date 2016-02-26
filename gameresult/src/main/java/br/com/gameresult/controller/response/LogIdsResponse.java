package br.com.gameresult.controller.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LogIdsResponse implements Serializable{

  private static final long serialVersionUID = -3054307200187958447L;

  private List<Long> gameResultIds;

  public LogIdsResponse(List<Long> gameResultIds) {
    this.gameResultIds = gameResultIds;
  }

  @JsonProperty(value = "gameResultIds")
  @JsonInclude(Include.NON_NULL)
  public List<Long> getGameResultIds() {
    return gameResultIds;
  }

}
