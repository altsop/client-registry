package ee.cloudmore.app.client;

import ee.cloudmore.app.client.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    Client findClientByNameAndSurname(String name, String surname);

    List<Client> findAllByEventTime(Instant offsetDateTime);

}
