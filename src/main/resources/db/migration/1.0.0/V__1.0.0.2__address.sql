CREATE TABLE IF NOT EXISTS `address`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT
  , `corporation_id` BIGINT(20) NOT NULL
  , `state` VARCHAR(128) NOT NULL
  , `message_unique_key` VARCHAR(256) NOT NULL
  , PRIMARY KEY (`id`)
  , UNIQUE (`message_unique_key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;