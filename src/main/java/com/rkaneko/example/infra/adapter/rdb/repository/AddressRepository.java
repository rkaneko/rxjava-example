package com.rkaneko.example.infra.adapter.rdb.repository;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.client.Origin;
import org.eclipse.jetty.util.resource.PathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Address save(Address address) {
        Preconditions.checkNotNull(address);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("corporationId", address.getCorporationId());
        parameters.addValue("state", address.getState());
        parameters.addValue("messageUniqueId", address.getMessageUniqueId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // @formatter:off
        namedParameterJdbcTemplate.update(
                "INSERT INTO `address` (`corporation_id`, `state`, `message_unique_id`) " +
                        "VALUES (:corporationId, :state, :messageUniqueId)"
                , parameters
                , keyHolder
        );
        // @formatter:on
        Long addressId = keyHolder.getKey().longValue();
        return Address.builder().id(addressId).corporationId(address.getCorporationId())
                .state(address.getState()).build();
    }
}
