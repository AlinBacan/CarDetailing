package ro.sda.javaro35.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.javaro35.finalProject.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
