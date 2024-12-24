package jeff.dev.smartstock.service;

import jeff.dev.smartstock.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorServicer {

	private final AuthService authService;

	public PurchaseSectorServicer(AuthService authService) {
		this.authService = authService;
	}

	public boolean sendPurchaseRequest(CsvStockItem item,
	                                   Integer purchaseQuantity){

		// 1. autenticao com api para recuperar o token
		var token = authService.getToken();
		// 2. enviar solicatcao de compra com o token gerado na chaada anterior

		// 3. validar se a resposta veio com sucesso

		return false;
	}
}
