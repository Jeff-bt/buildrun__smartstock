package jeff.dev.smartstock.repository;

import jeff.dev.smartstock.entity.PurchaseRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRequestRepository extends MongoRepository<PurchaseRequestEntity, String> {
}
