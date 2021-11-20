## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is simple movie and TV show application. 
It allows the user to search the movie repository accessible via the external [IMDb API](https://imdb-api.com/api) and to create and manage their own playlists
	
## Technologies
Project is created with:
* Java
* Spring Boot
* Angular
	
## Setup
To run this project, clone the repo:
```
https://github.com/EN-IH-WDPT-JUN21/SquadGame_MoviesProject.git
```
for the back-end create databases and users according to the instructions below:
```
CREATE DATABASE IF NOT EXISTS homework5;
CREATE USER IF NOT EXISTS 'homework5'@'localhost' IDENTIFIED BY 'homework5';
GRANT ALL PRIVILEGES ON homework5.* TO 'homework5'@'localhost';
FLUSH PRIVILEGES;
```
```
CREATE DATABASE IF NOT EXISTS movieproject_userservice;
CREATE USER IF NOT EXISTS 'ironhacker'@'localhost' IDENTIFIED BY '1ronhacker';
GRANT ALL PRIVILEGES ON movieproject_userservice.* TO 'ironhacker'@'localhost';
FLUSH PRIVILEGES;
```

for the front-end install it locally using npm:

```
$ npm install
$ npm serve
```
