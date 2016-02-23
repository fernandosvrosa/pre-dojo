package br.com.gameresult.service;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;

public interface GameReviewService {

	AnalyzeLogResponse analyzeLog(AnalyzeLogRequest analyzeLogRequest);

}
