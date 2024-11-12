package org.aicha.hunters_leagues.service;

import org.aicha.hunters_leagues.domain.Hunt;
import org.aicha.hunters_leagues.repository.HuntRepository;
import org.aicha.hunters_leagues.web.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HuntService {

    private final HuntRepository huntRepository;

    public HuntService(HuntRepository huntRepository) {
        this.huntRepository = huntRepository;
    }

    @Transactional
    public Hunt save(Hunt hunt) {
        return huntRepository.save(hunt);
    }

    @Transactional(readOnly = true)
    public List<Hunt> findAll() {
        return huntRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hunt findById(UUID id) {
        return huntRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hunt not found with id " + id));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!huntRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hunt not found with id " + id);
        }
        huntRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Hunt> findBySpeciesId(UUID speciesId) {
        return huntRepository.findBySpeciesId(speciesId);
    }

    @Transactional
    public void deleteBySpeciesId(UUID speciesId) {
        huntRepository.deleteBySpeciesId(speciesId);
    }
}