drop database if exists mall;
drop user if exists 'dev'@'%';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database mall default character set utf8mb4 collate utf8mb4_unicode_ci;
use mall;
create user 'dev'@'%' identified by 'Season!0316';
grant all privileges on mall.* to 'dev'@'%';
flush privileges;