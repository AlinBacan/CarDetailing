package ro.sda.javaro35.finalProject.dto;

import lombok.Data;

import java.util.Objects;


@Data
public class CosmeticServiceDto {
    private Integer id;
    private String serviceName;
    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmeticServiceDto that = (CosmeticServiceDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
