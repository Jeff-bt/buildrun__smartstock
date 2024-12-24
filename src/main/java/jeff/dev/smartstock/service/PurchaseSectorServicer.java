package jeff.dev.smartstock.service;

import jeff.dev.smartstock.client.PurchaseSectorClient;
import jeff.dev.smartstock.client.dto.PurchaseRequest;
import jeff.dev.smartstock.domain.CsvStockItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorServicer {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseSectorServicer.class);

	private final AuthService authService;
	private final PurchaseSectorClient purchaseSectorClient;

	public PurchaseSectorServicer(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
		this.authService = authService;
		this.purchaseSectorClient = purchaseSectorClient;
	}

	public boolean sendPurchaseRequest(CsvStockItem item,
	                                   Integer purchaseQuantity){

		// 1. autenticao com api para recuperar o token
		var token = authService.getToken();

		// 2. enviar solicatcao de compra com o token gerado na chaada anterior
		var request = new PurchaseRequest(
				item.getItemId(),
				item.getItemName(),
				item.getSupplierName(),
				item.getSupplierEmail(),
				purchaseQuantity
		);
		var response = purchaseSectorClient.sendPurchaseRequest(token, request);

		// 3. validar se a resposta veio com sucesso
		if (response.getStatusCode().value() != HttpStatus.ACCEPTED.value()) {
			logger.error("error while sending purchase request, status: {}, response: {} " +
					response.getStatusCode(),
					response.getBody());

			return false;
		}

		return true;
	}
}
