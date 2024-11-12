package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.Hunt;
import org.aicha.hunters_leagues.repository.dto.HuntDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HuntMapper {
    @Mapping(source = "species.id", target = "speciesId")
    @Mapping(source = "participation.id", target = "participationId")
    HuntDTO toDTO(Hunt hunt);

    @Mapping(source = "speciesId", target = "species.id")
    @Mapping(source = "participationId", target = "participation.id")
    Hunt toEntity(HuntDTO huntDTO);
}