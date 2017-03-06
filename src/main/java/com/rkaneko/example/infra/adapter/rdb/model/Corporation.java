package com.rkaneko.example.infra.adapter.rdb.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import com.google.common.base.Preconditions;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Corporation {
    private Long id;
    private String name;
    private String messageUniqueKey;
    private List<Address> addresses;

    public boolean isAnonymous() {
        if (addresses.isEmpty()) {
            return id != null;
        }

        return id != null && !addresses.stream().anyMatch(address -> address.getId() == null);
    }

    public static Corporation anonymous(String name, String messageUniqueKey) {
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(messageUniqueKey);
        return new Corporation(null, name, messageUniqueKey, Collections.emptyList());
    }

    public static Corporation of(Long id, String name, String messageUniqueKey) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(name);
        return new Corporation(id, name, messageUniqueKey, Collections.emptyList());
    }

    public static Corporation aggregate(Corporation corporation, Address... addresses) {
        Preconditions.checkNotNull(corporation);
        Preconditions.checkNotNull(addresses);

        return new Corporation(corporation.getId(), corporation.getName(), corporation.getMessageUniqueKey(),
                Arrays.asList(addresses));
    }
}
