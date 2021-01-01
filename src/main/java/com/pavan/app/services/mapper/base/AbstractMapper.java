package com.pavan.app.services.mapper.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<ENTITY, DTO>{

    public ENTITY mapOneToEntity(DTO dto){
        if(dto != null){
            return mapToEntity(dto);
        }
        return null;
    }

    public DTO mapOneToDto(ENTITY entity) {
        if(entity != null){
            return mapToDto(entity);
        }
        return null;
    }

    public List<DTO> mapListToDto(List<ENTITY> entities) {
        if(entities != null && !entities.isEmpty()){
            return entities.stream()
                    .map(this::mapOneToDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    abstract protected ENTITY mapToEntity(DTO dto);

    abstract protected DTO mapToDto(ENTITY entity);
}
