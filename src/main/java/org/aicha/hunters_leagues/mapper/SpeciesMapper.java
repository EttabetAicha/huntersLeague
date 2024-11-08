package org.aicha.hunters_leagues.mapper;

import org.aicha.hunters_leagues.domain.Species;
import org.aicha.hunters_leagues.web.vm.SpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpeciesMapper {

    SpeciesVM toVM(Species species);
    Species toSpecies(SpeciesVM speciesVM);
}