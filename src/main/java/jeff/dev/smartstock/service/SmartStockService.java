package jeff.dev.smartstock.service;

import jeff.dev.smartstock.domain.CsvStockItem;
import jeff.dev.smartstock.entity.PurchaseRequestEntity;
import jeff.dev.smartstock.repository.PurchaseRequestRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SmartStockService {

	private final ReportService reportService;
	private final PurchaseSectorServicer purchaseSectorServicer;
	private final PurchaseRequestRepository purchaseRequestRepository;

	public SmartStockService(ReportService reportService, PurchaseSectorServicer purchaseSectorServicer, PurchaseRequestRepository purchaseRequestRepository) {
		this.reportService = reportService;
		this.purchaseSectorServicer = purchaseSectorServicer;
		this.purchaseRequestRepository = purchaseRequestRepository;
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
					var purchasedWithSuccess = purchaseSectorServicer.sendPurchaseRequest(item, reorderQuantity);

					// 3. salvar no mongodb
					persist(item, reorderQuantity, purchasedWithSuccess);
				}

			});

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void persist(CsvStockItem item, Integer reorderQuantity, boolean purchasedWithSuccess) {
		var purchaseRequest = new PurchaseRequestEntity();
        purchaseRequest.setItemId(item.getItemId());
		purchaseRequest.setItemName(item.getItemName());
		purchaseRequest.setQuantityOnStock(item.getQuantity());
		purchaseRequest.setSupplierName(item.getSupplierName());
		purchaseRequest.setSupplierEmail(item.getSupplierEmail());
		purchaseRequest.setLastStockUpdateTime(LocalDateTime.parse(item.getLastStockUpdateTime()));

		purchaseRequest.setPurchaseQuantity(reorderQuantity);
		purchaseRequest.setPurchaseWithSuccess(purchasedWithSuccess);
		purchaseRequest.setPurchaseDateTime(LocalDateTime.now());

        purchaseRequestRepository.save(purchaseRequest);
	}

	private Integer calculateReorderQuantity(CsvStockItem item) {

		return item.getReorderThreshold() + ((int) Math.ceil(item.getReorderThreshold() * 0.2));
	}
}
