USE gaussian;
CREATE TABLE gaussian1 (id int not null primary key auto_increment, requestTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, document varchar(255), result varchar(255));