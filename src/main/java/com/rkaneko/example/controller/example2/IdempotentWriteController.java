package com.rkaneko.example.controller.example2;

import com.rkaneko.example.infra.adapter.rdb.repository.CorporationRepository;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IdempotentWriteController {
    private final CorporationRepository corporationRepository;

    @RequestMapping(value = "/api/example2", method = RequestMethod.POST)
    public IdempotentWriteOutputForm run(@Validated @RequestBody IdempotentWriteInputForm inputForm) {
        UUID uuid = UUID.randomUUID();
        Corporation corporation = Corporation.anonymous(inputForm.getCorporationName(), uuid.toString());
        Corporation creatd = corporationRepository.save(corporation);
        return new IdempotentWriteOutputForm(true);
    }
}
