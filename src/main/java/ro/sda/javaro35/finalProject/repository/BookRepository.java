package ro.sda.javaro35.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.sda.javaro35.finalProject.entities.CosmeticService;

public interface BookRepository extends JpaRepository<CosmeticService,Long> {

}
