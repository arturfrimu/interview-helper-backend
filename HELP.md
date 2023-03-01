# Learning Management System REST API

This REST API is designed to support a learning management system app. It includes endpoints for managing topics, lessons, quizzes, users, progress, achievements, courses, chapters, sections, exercises, projects, forums, posts, comments, and reviews.

The API provides functionality for creating, updating, deleting, and retrieving data related to learning management, including progress tracking, achievement tracking, and discussion forums.

The API uses a database schema consisting of 14 tables to organize, store, and access data. These tables include Topic, Lesson, Quiz, User, Progress, Achievement, Course, Chapter, Section, Exercise, Project, Forum, Comment, and Review.

The API is built using SpringBoot and uses JpaRepository to interact with the database. The code is divided into three parts:

- `@RestController` classes that handle incoming HTTP requests,
- `@Service` classes that contain business logic, and
- `@Repository` (JpaRepository) classes that handle database operations.

## API endpoints

Here are the available endpoints for this API:

### Topics

- `GET /topics`: get a list of all available topics to learn
- `GET /topics/{id}`: get details about a specific topic
- `POST /topics`: create a new topic to learn
- `PUT /topics/{id}`: update an existing topic
- `DELETE /topics/{id}`: delete an existing topic

### Lessons

- `GET /lessons`: get a list of all available lessons
- `GET /lessons/{id}`: get details about a specific lesson
- `POST /lessons`: create a new lesson
- `PUT /lessons/{id}`: update an existing lesson
- `DELETE /lessons/{id}`: delete an existing lesson

### Quizzes

- `GET /quizzes`: get a list of all available quizzes
- `GET /quizzes/{id}`: get details about a specific quiz
- `POST /quizzes`: create a new quiz
- `PUT /quizzes/{id}`: update an existing quiz
- `DELETE /quizzes/{id}`: delete an existing quiz

### Users

- `GET /users/{id}/progress`: get progress for a specific user
- `POST /users/{id}/progress`: update progress for a specific user
- `GET /users/{id}/achievements`: get all achievements for a specific user
- `POST /users/{id}/achievements`: create a new achievement for a specific user

### Courses

- `GET /courses`: get a list of all available courses to learn
- `GET /courses/{id}`: get details about a specific course
- `POST /courses`: create a new course to learn
- `PUT /courses/{id}`: update an existing course
- `DELETE /courses/{id}`: delete an existing course

### Chapters

- `GET /chapters`: get a list of all available chapters
- `GET /chapters/{id}`: get details about a specific chapter
- `POST /chapters`: create a new chapter
- `PUT /chapters/{id}`: update an existing chapter
- `DELETE /chapters/{id}`: delete an existing chapter

### Sections

- `GET /sections`: get a list of all available sections
- `GET /sections/{id}`: get details about a specific section
- `POST /sections`: create a new section
- `PUT /sections/{id}`: update an existing section
- `DELETE /sections/{id}`: delete an existing section

### Exercises

- `GET /exercises`: get a list of all available exercises
- `GET /exercises/{id}`: get details about a specific exercise
- `POST /exercises`: create a new exercise
- `PUT /exercises/{id}`: update an existing exercise
- `DELETE /exercises/{id}`: delete an existing exercise

### Projects

- `GET /projects`: get a list of all available projects to work on
- `GET /projects/{id}`: get details about a specific project
- `POST /projects`: create a new project to work on
- `PUT /projects/{id}`: update an existing project
- `DELETE /projects/{id}`: delete an existing project

### Tasks

- `GET /tasks`: get a list of all available tasks
- `GET /tasks/{id}`: get details about a specific task
- `POST /tasks`: create a new task
- `PUT /tasks/{id}`: update an existing task
- `DELETE /tasks/{id}`: delete an existing task

### Forums

- `GET /forums`: get a list of all available forums to discuss topics
- `GET /forums/{id}`: get details about a specific forum
- `POST /forums`: create a new forum to discuss topics
- `PUT /forums/{id}`: update an existing forum
- `DELETE /forums/{id}`: delete an existing forum

### Posts

- `GET /posts`: get a list of all available posts in forums
- `GET /posts/{id}`: get details about a specific post
- `POST /posts`: create a new post in forums
- `PUT /posts/{id}`: update an existing post
- `DELETE /posts/{id}`: delete an existing post

### Comments

- `GET /comments`: get a list of all available comments in posts
- `GET /comments/{id}`: get details about a specific comment
- `POST /comments`: create a new comment in posts
- `PUT /comments/{id}`: update an existing comment
- `DELETE /comments/{id}`: delete an existing comment

### Reviews

- `GET /reviews`: get a list of

all available reviews

- `GET /reviews/{id}`: get details about a specific review
- `POST /reviews`: create a new review
- `PUT /reviews/{id}`: update an existing review
- `DELETE /reviews/{id}`: delete an existing review

## Database schema

This API uses a database schema consisting of 14 tables to organize, store, and access data:

- `topics`: stores information about topics to learn
- `lessons`: stores information about lessons
- `quizzes`: stores information about quizzes
- `users`: stores information about registered users
- `progress`: stores information about user progress
- `achievements`: stores information about user achievements
- `courses`: stores information about courses to learn
- `chapters`: stores information about chapters
- `sections`: stores information about sections
- `exercises`: stores information about exercises
- `projects`: stores information about projects to work on
- `tasks`: stores information about tasks
- `forums`: stores information about discussion forums
- `posts`: stores information about individual posts in forums
- `comments`: stores information about comments on posts

## Technologies used

This API is built using SpringBoot, a popular Java framework for building web applications. It uses Hibernate and JpaRepository to interact with the database. The API is documented using Swagger, an open-source tool for designing, building, and documenting RESTful APIs.

The API is containerized using Docker, a popular tool for creating, deploying, and running applications in containers. The database is hosted on a cloud-based server and the API is deployed on a cloud-based platform as a service (PaaS).

## Getting started

To use this API, you will need to have Docker and Docker Compose installed on your system. You can then clone the repository and run the following command in the root directory:

```shell
docker run --name studies -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres
```

This will start the API and the database. You can then access the API documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Conclusion

This REST API provides a robust backend for a learning management system app. It includes endpoints for managing topics, lessons, quizzes, users, progress, achievements, courses, chapters, sections, exercises, projects, forums, posts, comments, and reviews. The API is built using SpringBoot and uses JpaRepository to interact with the database. The API is documented using Swagger and is containerized using Docker.

## Contributors

- [Artur Frimu](https://github.com/arturfrimu?tab=repositories)