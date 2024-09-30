
-- Computer Science II
-- Lab 8.0 - Structured Query Language II
-- Queries
--
-- Name(s):  Immanuel Soh
--           isoh2@huskers.unl.edu
--
--
-- Part 3.2

-- 1. Choose your favorite album and insert it into the database by doing the
--   following.
--   1.  Write a query to insert the band of the album 
INSERT INTO Band (bandId,name) VALUES (1111111,'Funk Fiction');

--   2.  Write a query to insert the album 
INSERT INTO Album (albumId,title,year,number,bandId) VALUES (1111,'Sonic and Chill',2023,1,1111111);

--   3.  Write two queries to insert the first two songs of the album
INSERT INTO Song (songId, title) VALUES (10000, 'Chill Zone Act 1');
INSERT INTO Song (songId, title) VALUES (10001, 'Welcome to Station Square');

--   4.  Write two queries to associate the two songs with the inserted album
INSERT INTO AlbumSong (trackNumber,trackLength,albumId,songId) VALUES (1,62,1111,10000);
INSERT INTO AlbumSong (trackNumber,trackLength,albumId,songId) VALUES (2,198,1111,10001);

-- 2. Update the musician record for "P. Best", his first name should be "Pete".
UPDATE Musician SET firstName = "Pete" WHERE musicianId = 1005;

-- 3. Pete Best was the Beatles original drummer, but was fired in 1962. 
--    Write a query that removes Pete Best from the Beatles.
DELETE FROM BandMember WHERE musicianId = 1005;

-- 4. Attempt to delete the song "Big in Japan" (by Tom Waits on the album
--    *Mule Variations*).  Observe that the query will fail due to referencing
--    records. Write a series of queries that will allow you to delete the 
--    album *Mule Variations*.
DELETE FROM AlbumSong WHERE albumId = 6;
DELETE FROM Album WHERE albumId = 6;

-- Part 3.2.2
-- Write quries to create your new tables for concerts, venues, etc. here:
create table Venue (
  venueId int not null primary key auto_increment,
  seatNumber int not null,
  name varchar(200) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Concert (
  concertId int not null primary key auto_increment,
  title varchar(100) not null,
  concertDate varchar(100) not null,
  ticketsSold int not null,
  bandId int not null,
  venueId int not null,
  foreign key (bandId) references Band(bandId),
  foreign key (venueId) references Venue(venueId)
)engine=InnoDB,collate=latin1_general_cs;

create table ConcertSong (
  concertSongId int not null primary key auto_increment,
  concertId int not null,
  songId int not null,
  foreign key (concertId) references Concert(concertId),
  foreign key (songId) references Song(songId),
  #prevent duplicate entries
  constraint uniquePair unique index(concertId,songId)
)engine=InnoDB,collate=latin1_general_cs;

-- Part 3.3.3
-- 
-- Insert some test data to your new tables
--
-- 1.  Write queries to insert at least two `Concert` records.
INSERT INTO Venue (venueId, seatNumber, name) VALUES (1, 500, 'Rococo Theater');
INSERT INTO Venue (venueId, seatNumber, name) VALUES (2, 4200, 'Ralston Arena');

INSERT INTO Concert (concertId, title, concertDate, ticketsSold, bandId, venueId) VALUES (1, 'Forward to the Future', '2024-04-02', 500, 1, 1);
INSERT INTO Concert (concertId, title, concertDate, ticketsSold, bandId, venueId) VALUES (2, 'Blast From the Past', '2024-04-01', 4000, 2, 2);

-- 2.  Write queries to associate at least 2 songs with each of the two concerts
INSERT INTO ConcertSong (concertId,songId) VALUES (1,1);
INSERT INTO ConcertSong (concertId,songId) VALUES (1,2);
INSERT INTO ConcertSong (concertId,songId) VALUES (2,3);
INSERT INTO ConcertSong (concertId,songId) VALUES (2,4);

-- 3.  Write a select-join query to retrieve these new results and produce
--     a playlist for each concert
SELECT * FROM Venue b JOIN Concert a ON b.venueId = a.venueId JOIN ConcertSong x ON a.concertID = x.concertID JOIN Song s ON s.songId = x.songId;

-- Computer Science II
-- Lab 7.0 - Structured Query Language I
-- Queries
--
-- Name(s):  Immanuel Soh
--			 isoh2@huskers.unl.edu
-- 
--
-- For each question, write an SQL query to get the specified result. You
-- are highly encouraged to use a GUI SQL tool such as MySQL Workbench and
-- keep track of your queries in an SQL script so that lab instructors can
-- verify your work. If you do, write your queries in the script file
-- provided rather than hand-writing your queries here.

-- Simple Queries 
-- --------------

-- 1. List all albums in the database.
SELECT * FROM Album;

-- 2. List all albums in the database from newest to oldest.
SELECT * FROM Album ORDER BY year DESC;

-- 3. List all bands in the database that begin with "The".
SELECT bandId, name FROM Band WHERE name LIKE '%The%';

-- 4. List all songs in the database in alphabetic order.
SELECT songId, title FROM Song ORDER BY title;

-- 5. Write a query that gives just the albumId of the album "Nevermind".
SELECT albumId FROM Album WHERE title LIKE 'Nevermind';

-- Simple Aggregate Queries 
-- ------------------------

-- 6. Write a query to determine how many musicians are in the database.
SELECT COUNT(*) AS numMusicians FROM Musician;

-- 7. Write a (nested) query to list the oldest album(s) in the database.
SELECT * FROM Album ORDER BY year LIMIT 1;

-- 8. Write a query to find the total running time (in seconds) of all 
--    tracks on the album *Rain Dogs* by Tom Waits
SELECT SUM(trackLength) FROM AlbumSong WHERE albumID = 5;

-- Join Queries 
-- ------------

-- 9. Write a query list all albums in the database along with the album's
--    band, but only include the album title, year and band name.
SELECT a.title, a.year, b.name FROM Album a JOIN Band b ON a.bandID = b.bandID;

-- 10. Write a query that lists all albums and all tracks on the albums 
--     for the band Nirvana.
SELECT * FROM Band b JOIN Album a ON b.bandId = a.bandId JOIN AlbumSong x ON a.albumID = x.albumID JOIN Song s ON s.songId = x.songId WHERE a.bandId = 2;

-- 11. Write a query that list all bands along with all their albums in 
--     the database *even if they do not have any*.
SELECT b.name, a.title, a.year FROM Band b LEFT JOIN Album a ON b.bandId = a.bandId;

-- Grouped Join Queries 
-- --------------------

-- 12. Write a query list all bands along with a *count* of how many albums
--     they have in the database (as you saw in the previous query, some should
--     have zero).
SELECT b.name, COUNT(albumId) FROM Band b LEFT JOIN Album a ON b.bandId = a.bandId GROUP BY b.bandId;

-- 13. Write a query that lists all albums in the database along with the
--     number of tracks on them.
SELECT a.title, COUNT(x.albumSongId) FROM Album a JOIN AlbumSong x ON a.albumId = x.albumId GROUP BY a.albumId;

-- 14. Write the same query, but limit it to albums which have 12 or more
--     tracks on them.
SELECT a.title, COUNT(x.albumSongId) AS numTracks FROM Album a JOIN AlbumSong x ON a.albumId = x.albumId GROUP BY a.albumId HAVING COUNT(*) > 11;

-- 15. Write a query to find all musicians that are not in any bands.
SELECT m.firstName, m.lastName FROM Musician m LEFT JOIN BandMember b ON m.musicianId = b.musicianId GROUP BY m.musicianId HAVING COUNT(bandMemberId) = 0;

-- 16. Write a query to find all musicians that are in more than one band.
SELECT m.firstName, m.lastName, COUNT(bandMemberId) AS bandNum FROM Musician m LEFT JOIN BandMember b ON m.musicianId = b.musicianId GROUP BY m.musicianId HAVING COUNT(bandMemberId) > 1;

-- Write a query to find some information from all albums using the criteria from the lab
SELECT a.albumId, a.title, a.year, b.bandId, b.name FROM Album a JOIN Band b ON a.bandID = b.bandID;

-- Write queries to display all relevant information from a specific album using a certain albumId
SELECT a.albumId, a.title, a.year, a.bandId, s.title FROM Album a JOIN AlbumSong x ON a.albumId = x.albumId JOIN Song s ON s.songId = x.songId WHERE a.albumId = 1;

SELECT bandId, name FROM Band b WHERE name LIKE 'Nirvana';