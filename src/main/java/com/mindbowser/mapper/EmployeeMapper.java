package com.mindbowser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mindbowser.dto.EmployeeDTO;
import com.mindbowser.entity.EmployeeEntity;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    public abstract EmployeeDTO getDTOFromEntity(EmployeeEntity entity);

    public abstract EmployeeEntity getEntityFromDTO(EmployeeDTO model);

    public abstract List<EmployeeDTO> getAllDTOsFromEntities(List<EmployeeEntity> entities);

    public abstract List<EmployeeEntity> getAllEntitiesFromDTOs(List<EmployeeDTO> dtos);

}
