package com.rkaneko.example.infra.adapter.rdb.service;

import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import com.rkaneko.example.infra.adapter.rdb.repository.CorporationRepository;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CorporationService {
    private final CorporationRepository corporationRepository;

    public Observable<Corporation> save(Corporation corporation) {
        return Observable.fromCallable(() -> corporationRepository.save(corporation));
    }
}
