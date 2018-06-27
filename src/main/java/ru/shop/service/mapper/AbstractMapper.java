package ru.shop.service.mapper;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<Entity, DTO> implements IMapper<Entity, DTO> {
    private Class<Entity> entityClass;

    private Class<DTO> dtoClass;

    private ModelMapper modelMapper;

    public AbstractMapper(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public DTO toDTO(Entity entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public Entity toEntity(DTO dto) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<DTO> toDTOs(Collection<Entity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Entity> toEntities(Collection<DTO> DTOs) {
        return DTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
