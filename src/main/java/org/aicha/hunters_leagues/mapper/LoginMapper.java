package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.web.vm.LoginVM;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LoginMapper {
    User toEntity(LoginVM loginVM);
    LoginVM toDto(User user);
}