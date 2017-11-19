-- --------------------------------------------------------
-- DATABASES CREATION
-- --------------------------------------------------------
-- Create database
CREATE DATABASE IF NOT EXISTS `adopteunjungle`;

-- --------------------------------------------------------
-- USERS CREATION
-- --------------------------------------------------------
CREATE USER 'adopteunjungle_STL'@'%' IDENTIFIED BY 'passw0rd';
CREATE USER 'adopteunjungle_STL'@'localhost' IDENTIFIED BY 'passw0rd';

-- --------------------------------------------------------
-- USERS PRIVILEGES RESET
-- --------------------------------------------------------
GRANT USAGE ON `adopteunjungle`.* TO 'adopteunjungle_STL'@'%';
GRANT USAGE ON `adopteunjungle`.* TO 'adopteunjungle_STL'@'localhost';

-- --------------------------------------------------------
-- USERS PRIVILEGES
-- --------------------------------------------------------
GRANT ALL ON `adopteunjungle`.* TO 'adopteunjungle_STL'@'%';
GRANT ALL ON `adopteunjungle`.* TO 'adopteunjungle_STL'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;
