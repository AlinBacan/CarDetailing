package ro.sda.javaro35.finalProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sda.javaro35.finalProject.entities.OrderLine;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, Integer> {
}
