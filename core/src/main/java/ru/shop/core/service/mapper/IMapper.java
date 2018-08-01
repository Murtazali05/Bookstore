package ru.shop.core.service.mapper;

import javassist.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface IMapper<Entity, DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto) throws NotFoundException;

    List<DTO> toDTOs(Collection<Entity> entities);

    List<Entity> toEntities(Collection<DTO> DTOs);

}
