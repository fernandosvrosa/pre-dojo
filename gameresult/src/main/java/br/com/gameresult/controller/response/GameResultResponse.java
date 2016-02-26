package br.com.gameresult.controller.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("player")
public class GameResultResponse implements Serializable {

  private static final long serialVersionUID = -2228548220047279988L;

  private String playerName;

  private String weaponLiked;

  private Integer killed = 0;

  private Integer died = 0;

  private Integer award = 0;

  public GameResultResponse(String playerName, String weaponLiked, Integer killed, Integer died, Integer award) {
    super();
    this.playerName = playerName;
    this.weaponLiked = weaponLiked;
    this.killed = killed;
    this.died = died;
    this.award = award;
  }

  @JsonInclude(Include.NON_NULL)
  public String getPlayerName() {
    return playerName;
  }

  @JsonInclude(Include.NON_NULL)
  public String getWeaponLiked() {
    return weaponLiked;
  }

  @JsonInclude(Include.NON_NULL)
  public Integer getKilled() {
    return killed;
  }

  @JsonInclude(Include.NON_NULL)
  public Integer getDied() {
    return died;
  }

  @JsonInclude(Include.NON_NULL)
  public Integer getAward() {
    return award;
  }

}
