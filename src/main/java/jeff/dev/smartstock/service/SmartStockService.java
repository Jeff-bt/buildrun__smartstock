package jeff.dev.smartstock.service;

import jeff.dev.smartstock.domain.CsvStockItem;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmartStockService {

	private final ReportService reportService;
	private final PurchaseSectorServicer purchaseSectorServicer;

	public SmartStockService(ReportService reportService, PurchaseSectorServicer purchaseSectorServicer) {
		this.reportService = reportService;
		this.purchaseSectorServicer = purchaseSectorServicer;
	}

	public void start(String reportPath) {
		// 1. ler csv
		try {
			var items = reportService.readStockReport(reportPath);

			items.forEach(item -> {

				if (item.getQuantity() < item.getReorderThreshold()) {

					// 1. Calcular a quantidade a ser recomprada
					var reorderQuantity = calculateReorderQuantity(item);

					// 2. chamar a api do setor de compras para cada item
					purchaseSectorServicer.sendPurchaseRequest(item, reorderQuantity);

					// 3. salvar no mongodb
				}

			});

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private Integer calculateReorderQuantity(CsvStockItem item) {

		return item.getReorderThreshold() + ((int) Math.ceil(item.getReorderThreshold() * 0.2));
	}
}
