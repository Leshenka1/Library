# Library
Данный проект является результатом выполнения следующих заданий
## Часть 1. Java SE, ООП,  шаблоны
Создать консольное приложение на языке Java, удовлетворяющее требованиям:
На основе сущностей предметной области создать классы их описывающие. При создании объектов и их коллекций использовать порождающие шаблоны проектирования (Design Patterns).
Реализовать алгоритмы сортировки и поиска объекта (применяя Comparator). При реализации алгоритмов бизнес-логики использовать по возможности структурные и поведенческие шаблоны.
## Часть 2 JDBC API
1. Спроектировать базу данных для своего варианта задания. Таблицы БД должны быть во 2 нормальной форме. Для работы с БД рекомендуется использовать СУБД MySQL.
2. Организовать соединение с базой данных: создать Java-класс, реализующий работу с connection (JdbcConnector).3
3. При организации доступа к БД использовать шаблон Data Access Object. Создать DAO классы для выполнения запросов на извлечение информации из БД (использовать PreparedStatement) и добавление информации. Подготовленные SQL запросы хранить в виде констант в соответствующем DAO классе.
4. Использовать файл ресурсов database.properties, в котором хранить параметры доступа к БД.  
## Часть 3 Пул соединений
1. Создать класс (ConnectionPool), реализующий пул соединений, хранящихся в коллекции и предоставляемых повторно. Обеспечить потокобезопасность пула. Заменить класс JdbcConnector на ConnectionPool.
2. Продумать обработку исключений, выбрасываемых в пуле и классах DAO, логгер
