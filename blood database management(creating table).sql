-- Donor table
CREATE TABLE Donor (
  DonorID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  DateOfBirth DATE,
  Gender VARCHAR(10),
  MedicalHistoryID INT,
  Phone VARCHAR(20),
  Email VARCHAR(100),
  Address VARCHAR(100),
);
ALTER TABLE Donor
ADD FOREIGN KEY (MedicalHistoryID) REFERENCES MedicalHistory(MedicalHistoryID)

-- Recipient table
CREATE TABLE Recipient (
  DonorID INT,
  DonationID INT,
  DonationDate DATE,
  DonationType VARCHAR(50),
  PRIMARY KEY (DonorID, DonationID),
);
ALTER TABLE Recipient
ADD FOREIGN KEY (DonorID) REFERENCES Donor(DonorID),
	FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID)
	 
-- BloodDonation table
CREATE TABLE BloodDonation (
  DonationID INT PRIMARY KEY,
  BloodBagID INT,
  StaffID INT,
  DonorID INT,
);
ALTER TABLE BloodDonation
ADD	FOREIGN KEY (BloodBagID) REFERENCES BloodBank(BloodBagID),
	FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
	FOREIGN KEY (DonorID) REFERENCES Donor(DonorID)

-- BloodBank table
CREATE TABLE BloodBank (
  BloodBagID INT PRIMARY KEY,
  BloodType VARCHAR(10),
  RhFactor VARCHAR(10),
  ExpirationDate DATE,
  StorageLocation VARCHAR(50)
);

-- MedicalHistory table
CREATE TABLE MedicalHistory (
  MedicalHistoryID INT PRIMARY KEY,
  MedicalCondition VARCHAR(100),
  Medications VARCHAR(100),
  Allergies VARCHAR(100),
  TestResults VARCHAR(100),
  ReactionID INT,
);
ALTER TABLE MedicalHistory
ADD FOREIGN KEY (ReactionID) REFERENCES AdverseReactions(ReactionID)

-- AdverseReactions table
CREATE TABLE AdverseReactions (
  ReactionID INT PRIMARY KEY,
  DonationID INT,
  ReactionType VARCHAR(100),
  ReactionDate DATE,
  Severity VARCHAR(50),
);
ALTER TABLE AdverseReactions
ADD FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID)

-- Staff table
CREATE TABLE Staff (
  StaffID INT PRIMARY KEY,
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Phone VARCHAR(20),
  Email VARCHAR(100),
  Address VARCHAR(100),
  Role VARCHAR(50),
  DonorID INT,
);
ALTER TABLE Staff
ADD FOREIGN KEY (DonorID) REFERENCES Donor(DonorID)

-- StaffDonor table (Staff to Donor relationship)
CREATE TABLE StaffDonor (
  StaffID INT,
  DonorID INT,
  PRIMARY KEY (StaffID, DonorID),
  FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
  FOREIGN KEY (DonorID) REFERENCES Donor(DonorID)
);

-- DonationBloodBank table (Donation to BloodBank relationship)
CREATE TABLE DonationBloodBank (
  DonationID INT,
  BloodBagID INT,
  PRIMARY KEY (DonationID, BloodBagID),
  FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID),
  FOREIGN KEY (BloodBagID) REFERENCES BloodBank(BloodBagID)
);
-- Staff to Blood Donation relationship table
CREATE TABLE StaffBloodDonation (
  StaffID INT,
  DonationID INT,
  PRIMARY KEY (StaffID, DonationID),
  FOREIGN KEY (StaffID) REFERENCES Staff(StaffID),
  FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID)
);
-- Blood Donation to Adverse Reactions relationship table
CREATE TABLE BloodDonationAdverseReactions (
  DonationID INT,
  ReactionID INT,
  PRIMARY KEY (DonationID, ReactionID),
  FOREIGN KEY (DonationID) REFERENCES BloodDonation(DonationID),
  FOREIGN KEY (ReactionID) REFERENCES AdverseReactions(ReactionID)
);

create table DonorPassword(
	DonorID int primary key,
	DonorPassword varchar(50),
	Foreign key (DonorID) references Donor(DonorID)
);

Create table StaffPassword(
	StaffID int primary key,
	StaffPassword varchar(50),
	Foreign key (StaffID) references Staff(StaffID)
);
