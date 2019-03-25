
CREATE TABLE UserAccount (
                UserAccountId INT NOT NULL,
                UserFirstName VARCHAR(30) NOT NULL,
                UserLastName VARCHAR(30) NOT NULL,
                Password VARBINARY(50) NOT NULL,
                PasswordSalt VARBINARY(50) NOT NULL,
                PRIMARY KEY (UserAccountId)
);


CREATE TABLE Waterfall (
                WaterfallId INT AUTO_INCREMENT NOT NULL,
                WaterfallName VARCHAR(30) NOT NULL,
                Height NUMERIC(4) NOT NULL,
                FallLatitude NUMERIC(7,5) NOT NULL,
                FallLongitude NUMERIC(8,5) NOT NULL,
                ParkingLatitude NUMERIC(7,5) NOT NULL,
                ParkingLongitude NUMERIC(8,5) NOT NULL,
                GPSRequired TINYINT NOT NULL,
                Description LONGTEXT NOT NULL,
                PRIMARY KEY (WaterfallId)
);


CREATE TABLE CompletedWaterfall (
                CompletedWaterfallId INT AUTO_INCREMENT NOT NULL,
                UserAccountId INT NOT NULL,
                WaterfallId INT NOT NULL,
                Ranking INT NOT NULL,
                PRIMARY KEY (CompletedWaterfallId)
);


ALTER TABLE CompletedWaterfall ADD CONSTRAINT useraccount_completedwaterfall_fk
FOREIGN KEY (UserAccountId)
REFERENCES UserAccount (UserAccountId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE CompletedWaterfall ADD CONSTRAINT waterfall_completedwaterfall_fk
FOREIGN KEY (WaterfallId)
REFERENCES Waterfall (WaterfallId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
