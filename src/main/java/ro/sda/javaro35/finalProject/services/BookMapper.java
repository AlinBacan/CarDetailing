package ro.sda.javaro35.finalProject.services;

import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.BookDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;

@Service
public class BookMapper implements Mapper<CosmeticService, BookDto> {
    @Override
    public BookDto convertToDto(CosmeticService entity) {
        BookDto result = new BookDto();
        result.setAuthor(entity.getPrice ());
        result.setId(entity.getIdCosmeticService ());
        result.setTitle(entity.getServiceName ());
        return result;

    }

    @Override
    public CosmeticService convertToEntity(BookDto dto) {
        CosmeticService result = new CosmeticService ();
        result.setPrice (dto.getAuthor());
        result.setIdCosmeticService (dto.getId());
        result.setServiceName (dto.getTitle());
        return result;
    }
}
