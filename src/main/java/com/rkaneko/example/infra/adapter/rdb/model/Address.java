package com.rkaneko.example.infra.adapter.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.google.common.base.Preconditions;

@AllArgsConstructor
@Getter
@Builder
public class Address {
    private Long id;
    private Long corporationId;
    private String state;
    private String messageUniqueKey;

    public static Address anonymous(Long corporationId, String state, String messageUniqueKey) {
        Preconditions.checkNotNull(corporationId);
        Preconditions.checkNotNull(state);
        Preconditions.checkNotNull(messageUniqueKey);

        return new Address(null, corporationId, state, messageUniqueKey);
    }

    public static Address of(Long id, Address anonymous) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(anonymous);

        return new Address(id, anonymous.getCorporationId(), anonymous.getState(), anonymous.getMessageUniqueKey());
    }
}
