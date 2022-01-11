<!-- TITLE/ -->

# Employee Managment <a href="http://localhost:8085/swagger-ui.html"><img src="https://img.shields.io/badge/Website-008f8a?style=plastic&logoColor=white"></a>

<!-- TITLE/ -->


<!-- BADGES/ -->

<img src="https://img.shields.io/badge/Java-ED8B00?style=plastic&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/Spring-6DB33F?style=plastic&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=plastic&logo=mysql&logoColor=white"> 
<img src="https://img.shields.io/badge/JSON-9777a8?style=plastic&logo=json&logoColor=white"> 
<img src="https://img.shields.io/badge/APACHE_MAVEN-C71A36?style=plastic&logo=apache-maven&logoColor=white"> 
<img src="https://img.shields.io/badge/SWAGGER-85EA2D?style=plastic&logo=swagger&logoColor=white">


<img src="https://img.shields.io/badge/release_v1.0-17394A?style=plastic&logoColor=white">

<!-- BADGES/ -->


<!-- ABOUT/ -->

## About
This project contains a monolithic architecture for different buisness objectives.

<!-- ABOUT/ -->


<!-- GETTING STARTED/ -->

## Getting Started
This is a Maven Project. Using Eclipse we will run this project.

<!-- GETTING STARTED/ -->


<!-- PREREQUISITES/ -->

### Prerequisites
* OpenJDK 8
* MySQL 5.7.32
* Workbench 6.3

<!-- PREREQUISITES/ -->

<!-- PROFILING/ -->

## Profiling
* local - localhost development (updated - 11 Jan)

<!-- PROFILING/ -->

<!-- RUN/ -->

## Run Steps (TODO)

Step 1 - Checkout the project and move inside.

 ```
$ git clone https://github.com/<your-user>/employee-management
```

Step 2 - Navigate into the folder  

```
$ cd employee-management
```

Step 3 - Install dependencies

```
$ mvn install
```

Step 4 - Run the project

```
$ mvn spring-boot:run
```
Step 5 - Run following SQL insert statements

```
INSERT INTO roles(role_name) VALUES('MANAGER');
INSERT INTO roles(role_name) VALUES('EMPLOYEE');

```

Step 6 - Navigate to `http://localhost:8085/swagger-ui.html` in your browser to check everything is working correctly. You can change the port in the `application.properties` file

```properties
server.port=8085
```

Step 7 - Make a GET,POST,PUT,DELETE request to `/api/v1/employee/**` to check you're not authenticated. You should receive a response with a `403` with an `Access Denied` message since you haven't set your valid JWT token yet

```
$ curl -X GET --header 'Accept: application/json' --header 'Authorization: Bearer ' 'http://localhost:8085/api/v1/employee'
```

Step 8 - Make a POST request to `/api/v1/auth/signup` with the MANAGER role of user we  programmatically created to get a valid JWT token

```javascript
{
  "address": "indore",
  "birthDate": "2022-01-11T06:40:32.936Z",
  "companyName": "tcs",
  "email": "admin@gmail.com",
  "firstName": "shreyansh",
  "lastName": "soni",
  "password": "admin@123",
  "roles": [
    "MANAGER"
  ]
}
```
or use

```
$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer ' -d '{
  "address": "indore",
  "birthDate": "2022-01-11T06:40:32.936Z",
  "companyName": "tcs",
  "email": "admin@gmail.com",
  "firstName": "shreyansh",
  "lastName": "soni",
  "password": "admin@123",
  "roles": [
    "MANAGER"
  ]
}' 'http://localhost:8085/api/v1/auth/signup'
```

Step 9 - Make a POST request to `/api/v1/auth/signin` 

```javascript
{
  "email": "admin@gmail.com",
  "password": "admin@123"
}
```

or use

```
$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer ' -d '{
  "email": "admin@gmail.com",
  "password": "admin@123"
}' 'http://localhost:8085/api/v1/auth/signin'
```

Step 10 - You should get a similar response to this one, meaning that you're now authenticated and singin successfully

```javascript
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2NDE4OTQ1NDgsImV4cCI6MTY0MTk4MDk0OH0.DNjBvwrFQouK6QEUTe6_1rrW9MU85pOW78YMPQZLMzEcrHKtnVut0KXmxiqYbOzck4qX1rzzUviTjW3Cg1Y6pw",
  "type": "Bearer",
  "id": 1,
  "firstName": "shreyansh",
  "lastName": "soni",
  "email": "admin@gmail.com",
  "roles": [
    "MANAGER"
  ],
  "address": "indore",
  "birthDate": "2022-01-11T06:40:33.000+0000",
  "companyName": "tcs"
}
```

Step 11 - then copy token and paste swagger header like shown below
![JWT token copy and paste hear](swagger.png)


Step 12 - Make a GET,POST,PUT,DELETE request to `/api/v1/employee/**` to check you're authenticated.

```javascript
{
  "address": "indore",
  "birthDate": "1996-02-02T06:40:32.937Z",
  "city": "indore",
  "createdAt": "2022-01-11T06:40:32.937Z",
  "createdBy": "manager",
  "firstName": "ram",
  "lastName": "yadav",
  "mobile": 9893828296,
  "modifiedBy": "manager",
  "updatedAt": "2022-01-11T06:40:32.937Z"
}

```
or use

```javascript
$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2NDE4OTQ1NDgsImV4cCI6MTY0MTk4MDk0OH0.DNjBvwrFQouK6QEUTe6_1rrW9MU85pOW78YMPQZLMzEcrHKtnVut0KXmxiqYbOzck4qX1rzzUviTjW3Cg1Y6pw' -d '{
  "address": "indore",
  "birthDate": "1996-02-02T06:40:32.937Z",
  "city": "indore",
  "createdAt": "2022-01-11T06:40:32.937Z",
  "createdBy": "manager",
  "firstName": "ram",
  "lastName": "yadav",
  "mobile": 9893828296,
  "modifiedBy": "manager",
  "updatedAt": "2022-01-11T06:40:32.937Z"
}' 'http://localhost:8085/api/v1/employee'
```
You should get a similar response to this one, meaning that you're now authenticated

```javascript
{
  "createdAt": "2022-01-11T06:40:32.937+0000",
  "updatedAt": "2022-01-11T06:40:32.937+0000",
  "createdBy": "manager",
  "modifiedBy": "manager",
  "id": 1,
  "firstName": "ram",
  "lastName": "yadav",
  "address": "indore",
  "birthDate": "1996-02-02T06:40:32.937+0000",
  "mobile": 9893828296,
  "city": "indore"
}

```

