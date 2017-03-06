package com.rkaneko.example.controller.example2;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkaneko.example.infra.adapter.rdb.model.Corporation;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IdempotentWriteController {
    private final CreateCorporationUseCase createCorporationUseCase;

    @RequestMapping(value = "/api/example2", method = RequestMethod.POST)
    public IdempotentWriteOutputForm run(@Validated @RequestBody IdempotentWriteInputForm inputForm) {
        CreateCorporationCommand aCommand = new CreateCorporationCommand(inputForm.getCorporationName(),
                inputForm.getState(), inputForm.getMessageUniqueKey());
        // @formatter:off
        final boolean success = createCorporationUseCase.execute(aCommand)
                .map(Corporation::isAnonymous)
                .onErrorReturn(throwable -> {
                    throwable.printStackTrace();
                    return false;
                })
                .blockingSingle();
        // @formatter:on
        return new IdempotentWriteOutputForm(success);
    }
}
