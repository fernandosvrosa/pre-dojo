package br.com.gameresult.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;
import br.com.gameresult.controller.response.GameResultResponse;
import br.com.gameresult.controller.response.LogIdsResponse;
import br.com.gameresult.controller.response.LogResponse;
import br.com.gameresult.dbo.ResultGames;
import br.com.gameresult.exception.LogHasBeenProcessedException;
import br.com.gameresult.service.GameReviewService;
import br.com.gameresult.util.FileUtil;

import com.google.common.collect.Lists;

@CrossOrigin
@RestController
@RequestMapping("/v1/analysis")
public class GameReviewController {


  private static final Logger LOG = LoggerFactory.getLogger(GameReviewController.class);

  @Autowired
  private GameReviewService gameReviewService;


  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LogIdsResponse> getLogs() {
    
     List<Long> gameIds =  Lists.newArrayList(ResultGames.getGameIds());
    return new ResponseEntity<LogIdsResponse>(new LogIdsResponse(gameIds), HttpStatus.OK);
  }
  
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LogResponse> getLog(@PathVariable(value = "id") final Long id) {
    
    List<GameResultResponse> gameById = ResultGames.getGameById(id);
    return new ResponseEntity<LogResponse>(new LogResponse(gameById), HttpStatus.OK);
  }
  

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AnalyzeLogResponse> analyzeLog(@RequestBody final AnalyzeLogRequest analyzeLogRequest) {
    AnalyzeLogResponse analyzeLogResponse = null;
    if (Validation(analyzeLogRequest)) {
      return new ResponseEntity<AnalyzeLogResponse>(HttpStatus.BAD_REQUEST);
    }

    try {
      analyzeLogResponse = gameReviewService.analyzeLog(analyzeLogRequest);
    } catch (LogHasBeenProcessedException e) {
      LOG.error("file already imported " + e.getGameId());
      return new ResponseEntity<AnalyzeLogResponse>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      LOG.error(e.getMessage());
      return new ResponseEntity<AnalyzeLogResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<AnalyzeLogResponse>(analyzeLogResponse, HttpStatus.CREATED);
  }

  private boolean Validation(AnalyzeLogRequest analyzeLogRequest) {
    return !isAttachmentInformed(analyzeLogRequest) || !FileUtil.isValideBase64Content(analyzeLogRequest.getBase64());
  }

  private boolean isAttachmentInformed(final AnalyzeLogRequest analyzeLogRequest) {
    return analyzeLogRequest != null
        && (!StringUtils.isBlank(analyzeLogRequest.getFileName())
            || !StringUtils.isBlank(analyzeLogRequest.getFileType()) || !StringUtils.isBlank(analyzeLogRequest
            .getFileSize()));
  }

}
