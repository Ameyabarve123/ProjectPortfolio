-- create the tables for our movies
CREATE TABLE `movies` (
	`movieid` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`title` varchar(100) NOT NULL,
	`year` char(4) DEFAULT NULL,
	PRIMARY KEY (`movieid`)
);
-- insert data into the tables
INSERT INTO movies
VALUES (1, "Elizabeth", "1998"),
	(2, "Black Widow", "2021"),
	(3, "Oh Brother Where Art Thou?", "2000"),
	(
		4,
		"The Lord of the Rings: The Fellowship of the Ring",
		"2001"
	),
	(5, "Up in the Air", "2009");


CREATE TABLE `actors` (
  `actorid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `last_name` varchar(100) NOT NULL,
  `first_names` varchar(100) NOT NULL,
  `dob` varchar(100) NOT NULL,
  PRIMARY KEY (`actorid`)
);

-- insert data into the tables
INSERT INTO actors
VALUES (1, "Ameya", "Barve", "1945-05-30" ),
  (2, "John", "Smith", "1950-02-20"),
  (3, "Bram", "Verma", "1948-01-15"),
  (4, "Ryan", "Prashant", "2001-02-06"),
  (5, "Bob", "Lee", "2003-03-03");


CREATE TABLE `movieActors` (
  `movieid` int(10) unsigned NOT NULL,
  `actorid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`movieid`, `actorid`),
  FOREIGN KEY (`movieid`) REFERENCES `movies` (`movieid`),
  FOREIGN KEY (`actorid`) REFERENCES `actors` (`actorid`)
);

-- Associate actors with movies
INSERT INTO `movieActors` (`movieid`, `actorid`)
VALUES
  (1, 1), 
  (2, 2),  
  (3, 3), 
  (4, 4), 
  (5, 5);