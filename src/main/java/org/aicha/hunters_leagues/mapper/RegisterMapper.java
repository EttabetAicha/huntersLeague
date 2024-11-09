package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.web.vm.RegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    User toEntity(RegisterVM registerVM);
    RegisterVM toDto(User user);
}