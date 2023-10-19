-- Создание таблицы LibraryBooks
CREATE TABLE if not exists LibraryBooks (
    bookID INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    copyNumber INT,
    availableCopies INT,
    readerID INT
);

-- Создание таблицы Readers
CREATE TABLE if not exists Readers (
    readerID INT PRIMARY KEY,
    name VARCHAR(255),
    age INT,
    hasOverdueFees BOOLEAN,
    feesDays INT
);

SELECT LibraryBooks.*, Readers.name AS readerName
FROM LibraryBooks
LEFT JOIN Readers ON LibraryBooks.readerID = Readers.readerID;

