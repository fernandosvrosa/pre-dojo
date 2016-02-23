package br.com.gameresult.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;
import br.com.gameresult.service.GameReviewService;
import br.com.gameresult.util.FileUtil;

@CrossOrigin
@RestController
@RequestMapping("/v1/analysis")
public class GameReviewController {
	
	
	@Autowired
	private GameReviewService gameReviewService;
	
	
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public String index(){
        return "funciona?";
    }
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AnalyzeLogResponse> analyzeLog(@RequestBody final AnalyzeLogRequest analyzeLogRequest) {
		
		if (Validation(analyzeLogRequest)) {
			return new ResponseEntity<AnalyzeLogResponse>(HttpStatus.BAD_REQUEST);
		}
		
		AnalyzeLogResponse analyzeLogResponse = gameReviewService.analyzeLog(analyzeLogRequest);
		
		return new ResponseEntity<AnalyzeLogResponse>(analyzeLogResponse, HttpStatus.OK);
	}
	
	private boolean Validation(AnalyzeLogRequest analyzeLogRequest) {
		
		return !isAttachmentInformed(analyzeLogRequest) || !FileUtil.isValideBase64Content(analyzeLogRequest.getBase64());
	}

	private boolean isAttachmentInformed(final AnalyzeLogRequest analyzeLogRequest) {
	    return analyzeLogRequest != null
	        && (!StringUtils.isBlank(analyzeLogRequest.getFileName()) || !StringUtils.isBlank(analyzeLogRequest.getFileType()) || !StringUtils
	            .isBlank(analyzeLogRequest.getFileSize()));
	  }

}
