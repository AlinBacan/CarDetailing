package ro.sda.javaro35.finalProject.mapper;

public interface Mapper <E,D>{
    D convertToDto(E entity);
    E convertToEntity(D dto);
}