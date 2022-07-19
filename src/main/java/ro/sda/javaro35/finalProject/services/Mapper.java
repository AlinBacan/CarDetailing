package ro.sda.javaro35.finalProject.services;

public interface Mapper <E,D>{
    D convertToDto(E entity);
    E convertToEntity(D dto);
}