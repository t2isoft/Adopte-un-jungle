-- --------------------------------------------------------
-- DATABASES CREATION
-- --------------------------------------------------------
-- Create database
CREATE DATABASE IF NOT EXISTS `adopteunjungle`;

-- --------------------------------------------------------
-- USERS CREATION
-- --------------------------------------------------------
CREATE USER 'adopteunjgl_STL'@'%' IDENTIFIED BY 'passw0rd';
CREATE USER 'adopteunjgl_STL'@'localhost' IDENTIFIED BY 'passw0rd';

-- --------------------------------------------------------
-- USERS PRIVILEGES RESET
-- --------------------------------------------------------
GRANT USAGE ON `adopteunjungle`.* TO 'adopteunjgl_STL'@'%';
GRANT USAGE ON `adopteunjungle`.* TO 'adopteunjgl_STL'@'localhost';

-- --------------------------------------------------------
-- USERS PRIVILEGES
-- --------------------------------------------------------
GRANT ALL ON `adopteunjungle`.* TO 'adopteunjgl_STL'@'%';
GRANT ALL ON `adopteunjungle`.* TO 'adopteunjgl_STL'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;
