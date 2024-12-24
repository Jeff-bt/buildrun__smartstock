package jeff.dev.smartstock.client;

import jeff.dev.smartstock.client.dto.PurcahseResponse;
import jeff.dev.smartstock.client.dto.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PurchaseSectorClient", url = "${api.purchase-sector-url}")
public interface PurchaseSectorClient {

	@PostMapping("api/purchases")
	ResponseEntity<PurcahseResponse> sendPurchaseRequest(@RequestHeader("Authorization") String token,
	                                                     @RequestBody PurchaseRequest request);
}
