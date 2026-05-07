package farcic.dev.footApi.service;

import farcic.dev.footApi.entity.Scopes;
import farcic.dev.footApi.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScopeService {

    private final ScopeRepository repository;

    public Scopes findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scope not found"));
    }

}
