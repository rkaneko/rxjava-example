package com.rkaneko.example.infra.adapter.rdb.repository;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import lombok.RequiredArgsConstructor;
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
public class CorporationRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Corporation save(Corporation corporation) {
        Preconditions.checkNotNull(corporation);
        Preconditions.checkNotNull(corporation.getMessageUniqueKey());

        Corporation saved = findByMessageUniqueKey(corporation.getMessageUniqueKey());
        if (saved == null) {
            return insert(corporation);
        }
        Corporation adjusted = Corporation.of(saved.getId(), corporation.getName(), corporation.getMessageUniqueKey());
        return update(adjusted);
    }

    private Corporation insert(Corporation corporation) {
        Preconditions.checkNotNull(corporation);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", corporation.getName());
        parameters.addValue("messageUniqueKey", corporation.getMessageUniqueKey());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // @formatter:off
        namedParameterJdbcTemplate.update(
                "INSERT INTO `corporation` (`name`, `message_unique_key`) VALUES " +
                        "(:name, :messageUniqueKey)"
                , parameters
                , keyHolder
        );
        // @formatter:on
        Long corporationId = keyHolder.getKey().longValue();
        return Corporation.of(corporationId, corporation.getName(), corporation.getMessageUniqueKey());
    }

    private Corporation update(Corporation corporation) {
        Preconditions.checkNotNull(corporation);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", corporation.getId());
        parameters.addValue("name", corporation.getName());
        parameters.addValue("messageUniqueKey", corporation.getMessageUniqueKey());

        // @formatter:off
        namedParameterJdbcTemplate.update(
                "UPDATE `corporation` SET `name` = :name, `message_unique_key` = :messageUniqueKey " +
                        "WHERE `id` = :id"
                , parameters
        );
        // @formatter:on
        return Corporation.of(corporation.getId(), corporation.getName(), corporation.getMessageUniqueKey());
    }

    private Corporation findByMessageUniqueKey(String messageUniqueKey) {
        Preconditions.checkNotNull(messageUniqueKey);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("messageUniqueKey", messageUniqueKey);

        // @formatter:off
        List<Corporation> list = namedParameterJdbcTemplate.query(
                "SELECT * FROM `corporation` WHERE `message_unique_key` = :messageUniqueKey"
                , parameters
                , new CorporationRowMapper()
        );
        // @formatter:on
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    private static class CorporationExtractor implements ResultSetExtractor<Corporation> {
        @Override
        public Corporation extractData(ResultSet rs) throws SQLException, DataAccessException {
            return Corporation.of(rs.getLong("id"), rs.getString("name"), rs.getString("message_unique_key"));
        }
    }

    private static class CorporationRowMapper implements RowMapper<Corporation> {
        @Override
        public Corporation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Corporation corporation = Corporation.of(
                        rs.getLong("id")
                        , rs.getString("name")
                        , rs.getString("message_unique_key")
                );
            return corporation;
        }
    }
}
