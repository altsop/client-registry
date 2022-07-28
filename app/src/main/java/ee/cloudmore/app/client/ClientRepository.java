package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

}
