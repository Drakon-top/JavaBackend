# Telegram Bot For Tracking Links

Проект имеет 2 web-сервиса для отслеживания обновлений контента по ссылкам. В сервисе поддерживаются:

* Вопросы со StackOverflow

* Репозитории GitHub

Управление подписками (ссылками) происходит через чат с ботом в Telegram. При новых изменениях в чат отправляется уведомление.

Сервисы общаются между собой как напрямую (по HTTP). Для хранения данных используется СУБД PostgreSQL.
## Стек используемых технологий и программ:
Spring, Docker, PostgreSql, Liquibase, TgBot Api, RabbitMQ
