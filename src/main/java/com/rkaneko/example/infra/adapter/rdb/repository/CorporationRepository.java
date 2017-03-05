package com.rkaneko.example.infra.adapter.rdb.repository;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CorporationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Corporation save(Corporation corporation) {
        Preconditions.checkNotNull(corporation);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", corporation.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // @formatter:off
        namedParameterJdbcTemplate.update(
                "INSERT INTO `corporation` (`name`) VALUES (:name)"
                , parameters
                , keyHolder
        );
        // @formatter:on
        Long corporationId = keyHolder.getKey().longValue();
        return Corporation.of(corporationId, corporation.getName());
    }
}
