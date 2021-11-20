## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Usage](#usage)
* [Webpage](#webpage)

## General info
This project is simple movie and TV show application. 
It allows the user to search the movie repository accessible via the external [IMDb API](https://imdb-api.com/api) and to create and manage their own playlists
	
## Technologies
Project is created with:
* Java
* Spring Boot
* Angular
* TypeScript
* HTML
* CSS
* JavaScript
	
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
The front-end start by default at localhost:4200

## Usage
The architectural assumption of the project was to divide the functionality between microservices. The application consists of 4 microservices, including gateway and server. To run the project all microservices should be started - we recommend starting with the server. All endpoints are available from the gateway level, which operates on port 8000.

Method | Endpoint             | Description                  | Permission
------ | -------------------- | ---------------------------- | ----------------
GET    | /playlists           | to get all playlists         | logged user
GET    | /playlist-by-id/{id} | to get playlist by id        | logged user
GET    | /playlists/user/{id} | to get all user's playlists  | logged user
POST   | /playlists           | to create new playlist       | logged user
PATCH  | /playlists/{id}      | to update playlist's title   | logged user
DELETE | /playlists/{id}      | to delete playlist by id     | logged user
GET    | /playlist-item       | to get all playlist's items  | logged user
GET    | /playlist-item/{id}  | to get playlist's item by id | logged user
GET    | /playlist-item/playlist-title/{playlist-title}/{userId} | to get all playlist's items by playlist title and user | logged user
POST   | /playlist-item       | to create new playlist's item | logged user
DELETE | /playlist-item/{id}      | to delete playlist's item by id | logged user
DELETE | /playlist-item/{userId}/{playlistTitle} | to delete all playlist's items by user and playlist title | logged user
GET    | /user-details/{id} | to get user details bu user id | logged user
POST   | /users/login | to log the user | for all
POST   | /users | to create new user | for all
PUT    | /user-details/{id} | to change the user details by id | logged user

## Webpage
The homepage available by default at localhost:4200 contain navbar at the top. Links to login, register and search are also at the bottom of the page. From navbar user can choose:
```
Home - main page;
Search movies - to find information about movies and series;
Playlists - for creating your own playlists. Available for logged users;
My profile (user icon) - allows update information about user and add bio;
Register - way for new users to create account;
Login - to account gives access to playlists and user profile;
Logout - disconnecing from account.
```
To login user need username and password.<br>
To register: username, email and password is needed.<br>
User profile page allow to add picture and write bio.<br>
Each playlist can contain maximum ten movies. Every logged user can create many playlists.