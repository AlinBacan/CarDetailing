package ro.sda.javaro35.finalProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.sda.javaro35.finalProject.dto.CosmeticServiceDto;
import ro.sda.javaro35.finalProject.entities.CosmeticService;
import ro.sda.javaro35.finalProject.exceptions.EntityNotFoundError;
import ro.sda.javaro35.finalProject.mapper.CosmeticServiceMapper;
import ro.sda.javaro35.finalProject.repository.CosmeticServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CosmeticServiceService {

    private CosmeticServiceRepository cosmeticServiceRepository;
    private CosmeticServiceMapper cosmeticServiceMapper;

    public List<CosmeticServiceDto> getAllCosmeticServices() {

        return cosmeticServiceRepository.findAll().stream().map(book -> cosmeticServiceMapper.convertToDto(book)).collect(Collectors.toList());
    }

    public CosmeticServiceDto createCosmeticService(CosmeticServiceDto form) {
        CosmeticService cosmeticService= cosmeticServiceMapper.convertToEntity(form);
        cosmeticService= cosmeticServiceRepository.save(cosmeticService);
        return cosmeticServiceMapper.convertToDto(cosmeticService);
    }

    public CosmeticServiceDto findById(Integer id) {
        CosmeticService entityCosmeticService= cosmeticServiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Cosmetic service with %s does not exist", id)));
        return cosmeticServiceMapper.convertToDto(entityCosmeticService);
    }

    public void deleteById(Integer id) {
        cosmeticServiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundError(String.format("Cosmetic service with %s does not exist", id)));
        cosmeticServiceRepository.deleteById(id);
    }
}
