package br.com.gameresult.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.gameresult.controller.request.AnalyzeLogRequest;
import br.com.gameresult.controller.response.AnalyzeLogResponse;
import br.com.gameresult.util.FileUtil;

@Service
public class GameReviewServiceImpl implements GameReviewService {

	@Override
	public AnalyzeLogResponse analyzeLog(AnalyzeLogRequest analyzeLogRequest) {
		InputStream arquivo = extrair(analyzeLogRequest.getBase64());
		lerArquivo(arquivo);
		return null;
	}

	private void lerArquivo(InputStream arquivo) {
		Scanner scanner = new Scanner(arquivo);
		while (scanner.hasNextLine()) {
			processaLinha(scanner.nextLine());
		}
	}

	private void processaLinha(String nextLine) {
		System.out.println(nextLine);

	}

	private InputStream extrair(String string) {
		final byte[] content = FileUtil.convert(string);
		return new ByteArrayInputStream(content);
	}

}
