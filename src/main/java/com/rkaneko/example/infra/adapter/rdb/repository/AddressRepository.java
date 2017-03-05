package com.rkaneko.example.infra.adapter.rdb.repository;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.client.Origin;
import org.eclipse.jetty.util.resource.PathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Address save(Address address) {
        Preconditions.checkNotNull(address);
        Preconditions.checkNotNull(address.getMessageUniqueKey());

        Address saved = findByMessageUniqueId(address.getMessageUniqueKey());
        if (saved == null) {
            return insert(address);
        }
        Address adjusted = Address.builder()
                .id(saved.getId())
                .corporationId(address.getCorporationId())
                .state(address.getState())
                .messageUniqueKey(address.getMessageUniqueKey())
                .build();
        return update(adjusted);
    }

    public Address insert(Address address) {
        Preconditions.checkNotNull(address);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("corporationId", address.getCorporationId());
        parameters.addValue("state", address.getState());
        parameters.addValue("messageUniqueKey", address.getMessageUniqueKey());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // @formatter:off
        namedParameterJdbcTemplate.update(
                "INSERT INTO `address` (`corporation_id`, `state`, `message_unique_key`) " +
                        "VALUES (:corporationId, :state, :messageUniqueKey)"
                , parameters
                , keyHolder
        );
        // @formatter:on
        Long addressId = keyHolder.getKey().longValue();
        return Address.builder().id(addressId).corporationId(address.getCorporationId())
                .state(address.getState()).build();
    }

    private Address update(Address address) {
        Preconditions.checkNotNull(address);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", address.getId());
        parameters.addValue("corporationId", address.getCorporationId());
        parameters.addValue("state", address.getState());
        parameters.addValue("messageUniqueKey", address.getMessageUniqueKey());

        // @formatter:off
        namedParameterJdbcTemplate.update(
                "UPDATE `address` (`corporation_id`, `state`, `message_unique_key`) " +
                        "SET `corporation_id` = :corporationId, " +
                        "`state` = :state, " +
                        "`message_unique_key` = :messageUniqueKey " +
                        "WHERE `id` = :id"
                , parameters
        );
        // @formatter:on
        return Address.builder()
                .id(address.getId())
                .corporationId(address.getCorporationId())
                .state(address.getState())
                .messageUniqueKey(address.getMessageUniqueKey())
                .build();
    }

    public Address findByMessageUniqueId(String messageUniqueId) {
        Preconditions.checkNotNull(messageUniqueId);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("messageUniqueKey", messageUniqueId);

        // @formatter:off
        List<Address> list = namedParameterJdbcTemplate.query(
                "SELECT * FROM `address` WHERE `message_unique_id = :messageUniqueKey"
                , parameters
                , new AddressRowMapper()
        );
        // @formatter:on
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    private static class AddressExtractor implements ResultSetExtractor<Address> {
        @Override
        public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
            return Address.builder()
                    .id(rs.getLong("id"))
                    .corporationId(rs.getLong("corporation_id"))
                    .state(rs.getString("state"))
                    .messageUniqueKey(rs.getString("message_unique_key"))
                    .build();
        }
    }

    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = Address.builder()
                        .id(rs.getLong("id"))
                        .corporationId(rs.getLong("corporation_id"))
                        .state(rs.getString("state"))
                        .messageUniqueKey(rs.getString("message_unique_key"))
                        .build();
            return address;
        }
    }
}
