package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.Competition;
import org.aicha.hunters_leagues.repository.dto.CompetitionDetailsDTO;
import org.aicha.hunters_leagues.web.vm.CompetitionVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionVM toVM(Competition competition);
    Competition toCompetition(CompetitionVM competitionVM);
    CompetitionVM toDetailsVM(CompetitionDetailsDTO competitionDetailsDTO);
}