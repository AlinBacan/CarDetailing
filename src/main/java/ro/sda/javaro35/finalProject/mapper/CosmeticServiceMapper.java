package ro.sda.javaro35.finalProject.mapper;

import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.mapper.Mapper;

@Service
public class CosmeticServiceMapper implements Mapper<CosmeticService, CosmeticServiceDto> {
    @Override
    public CosmeticServiceDto convertToDto(CosmeticService entity) {
        CosmeticServiceDto result = new CosmeticServiceDto();
        result.setPrice(entity.getPrice ());
        result.setId(entity.getId());
        result.setServiceName(entity.getServiceName ());
        return result;

    }

    @Override
    public CosmeticService convertToEntity(CosmeticServiceDto dto) {
        CosmeticService result = new CosmeticService ();
        result.setPrice (dto.getPrice());
        result.setId(dto.getId());
        result.setServiceName (dto.getServiceName());
        return result;
    }
}
