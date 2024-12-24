package jeff.dev.smartstock.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmartStockService {

	private final ReportService reportService;

	public SmartStockService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void start(String reportPath) {
		// 1. ler csv
		try {
			var items = reportService.readStockReport(reportPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// 2. chamar a api do setor de compras para cada item
		// 3. salvar no mongodb
	}
}