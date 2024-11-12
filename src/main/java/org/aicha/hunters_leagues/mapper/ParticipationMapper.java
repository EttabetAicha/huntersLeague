package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.Participation;
import org.aicha.hunters_leagues.web.vm.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "competition.id", target = "competitionId")
    ParticipationVM toVM(Participation participation);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "competitionId", target = "competition.id")
    Participation toEntity(ParticipationVM participationVM);
}