CREATE TABLE IF NOT EXISTS `corporation`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT
  , `name` VARCHAR(128) NOT NULL
  , `message_unique_key` VARCHAR(256) NOT NULL
  , PRIMARY KEY (`id`)
  , UNIQUE (`message_unique_key`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;