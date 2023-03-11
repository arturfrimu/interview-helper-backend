package com.arturfrimu.studies.component;

import com.arturfrimu.studies.dto.request.Requests;
import com.arturfrimu.studies.dto.request.Requests.CreateChapterRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateCommentRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateCourseRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateExerciseRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateLessonRequest;
import com.arturfrimu.studies.dto.request.Requests.CreatePostRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateProjectRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateQuizRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateSectionRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateUserRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateChapterRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateCommentRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateExerciseRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateLessonRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdatePostRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateProjectRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateQuizRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateSectionRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.studies.dto.response.Response.QuizInfoResponse;
import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.entity.Chapter;
import com.arturfrimu.studies.entity.Comment;
import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.entity.Exercise;
import com.arturfrimu.studies.entity.Forum;
import com.arturfrimu.studies.entity.Lesson;
import com.arturfrimu.studies.entity.Post;
import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.entity.Section;
import com.arturfrimu.studies.entity.Topic;
import com.arturfrimu.studies.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.arturfrimu.studies.dto.request.Requests.CreateAchievementRequest;
import static com.arturfrimu.studies.dto.request.Requests.UpdateAchievementRequest;
import static com.arturfrimu.studies.dto.request.Requests.UpdateUserRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.RequestEntity.put;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ComponentTest {

    @Autowired
    BaseRestTemplate restTemplate;

    @Test
    void testLifecycle() {
        testCreateForum();
        testListForums();
        testFindForum();


        testCreateCourse();
        testListCourses();
        testFindCourse();


        testCreateTopic();
        testListTopics();
        testFindTopic();


        testCreateUser();
        testListUsers();
        testFindUser();


        testCreateAchievement();
        testListAchievements();
        testFindAchievement();
        testFindAchievementsByUserId();


        testCreateChapter();
        testListChapters();
        testFindChapter();

        testCreatePost();
        testListPosts();
        testFindPost();


        testCreateSection();
        testListSections();
        testFindSection();


        testCreateProject();
        testListProjects();
        testFindProject();


        testCreateComment();
        testListComments();
        testFindComment();


        testCreateExercise();
        testListExercises();
        testFindExercise();


        testCreateLesson();
        testListLessons();
        testFindLesson();


        testCreateQuiz();
        testListQuizzes();
        testFindQuiz();


        testUpdateForum();
        testUpdateCourse();
        testUpdateTopic();
        testUpdateUser();
        testUpdateAchievement();
        testUpdateChapter();
        testUpdatePost();
        testUpdateSection();
        testUpdateProject();
        testUpdateComment();
        testUpdateExercise();
        testUpdateLesson();
        testUpdateQuiz();


        testDeleteComment();
        testDeleteProject();
        testDeleteSection();
        testDeleteExercise();
        testDeleteChapter();
        testDeleteCourse();
        testDeleteQuiz();
        testDeleteLesson();
        testDeleteTopic();
        testDeleteAchievement();
        testDeleteUser();
        testDeletePost();
        testDeleteForum();
    }

    void testCreateForum() {
        var body = new Requests.CreateForumRequest("Forum 1", "This is the first forum");

        var response = restTemplate.exchange(post(FORUM_BASE_URL).body(body), FORUM);

        var createdForum = response.getBody();

        assertThat(createdForum).isNotNull();

        assertThat(createdForum.getForumId()).isNotNull();
        assertThat(createdForum.getName()).isEqualTo("Forum 1");
        assertThat(createdForum.getDescription()).isEqualTo("This is the first forum");
    }

    void testListForums() {
        var response = restTemplate.exchange(get(FORUM_BASE_URL).build(), FORUM_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var forums = response.getBody();

        assertThat(forums).isNotNull();
        assertThat(forums.size()).isEqualTo(1);

        assertThat(forums.get(0).getName()).isEqualTo("Forum 1");
        assertThat(forums.get(0).getDescription()).isEqualTo("This is the first forum");
    }

    void testFindForum() {
        var response = restTemplate.exchange(get(FORUM_BASE_URL + "/1").build(), FORUM);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var forum = response.getBody();

        assertThat(forum).isNotNull();

        assertThat(forum.getName()).isEqualTo("Forum 1");
        assertThat(forum.getDescription()).isEqualTo("This is the first forum");
    }

    void testUpdateForum() {
        var body = new Requests.UpdateForumRequest("Forum 1 Updated", "This is the first forum update");

        var response = restTemplate.exchange(put(FORUM_BASE_URL + "/1").body(body), FORUM);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedForum = response.getBody();

        assertThat(updatedForum).isNotNull();

        assertThat(updatedForum.getName()).isEqualTo("Forum 1 Updated");
        assertThat(updatedForum.getDescription()).isEqualTo("This is the first forum update");
    }

    void testDeleteForum() {
        var response = restTemplate.exchange(delete(FORUM_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedForum = restTemplate.exchange(get(FORUM_BASE_URL + "/1").build(), FORUM);

        assertThat(deletedForum.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateCourse() {
        var body = new CreateCourseRequest("Course 1", "This is the first course");

        var response = restTemplate.exchange(post(COURSE_BASE_URL).body(body), COURSE);

        var createdCourse = response.getBody();

        assertThat(createdCourse).isNotNull();

        assertThat(createdCourse.getCourseId()).isNotNull();
        assertThat(createdCourse.getName()).isEqualTo("Course 1");
        assertThat(createdCourse.getDescription()).isEqualTo("This is the first course");
    }

    void testListCourses() {
        var response = restTemplate.exchange(get(COURSE_BASE_URL).build(), COURSE_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var courses = response.getBody();

        assertThat(courses).isNotNull();

        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getName()).isEqualTo("Course 1");
        assertThat(courses.get(0).getDescription()).isEqualTo("This is the first course");
    }

    void testFindCourse() {
        var response = restTemplate.exchange(get(COURSE_BASE_URL + "/1").build(), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var course = response.getBody();

        assertThat(course).isNotNull();

        assertThat(course.getName()).isEqualTo("Course 1");
        assertThat(course.getDescription()).isEqualTo("This is the first course");
    }

    void testUpdateCourse() {
        var body = new Requests.UpdateCourseRequest("Course 1 Updated", "This is the first course update");

        var response = restTemplate.exchange(put(COURSE_BASE_URL + "/1").body(body), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedCourse = response.getBody();

        assertThat(updatedCourse).isNotNull();

        assertThat(updatedCourse.getName()).isEqualTo("Course 1 Updated");
        assertThat(updatedCourse.getDescription()).isEqualTo("This is the first course update");
    }

    void testDeleteCourse() {
        var response = restTemplate.exchange(delete(COURSE_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedCourse = restTemplate.exchange(get(COURSE_BASE_URL + "/1").build(), COURSE);

        assertThat(deletedCourse.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateTopic() {
        var body = new CreateTopicRequest("Topic 1", "This is the first topic");

        var response = restTemplate.exchange(post(TOPIC_BASE_URL).body(body), TOPIC);

        var createdTopic = response.getBody();

        assertThat(createdTopic).isNotNull();

        assertThat(createdTopic.getTopicId()).isNotNull();
        assertThat(createdTopic.getName()).isEqualTo("Topic 1");
        assertThat(createdTopic.getDescription()).isEqualTo("This is the first topic");
    }

    void testListTopics() {
        var response = restTemplate.exchange(get(TOPIC_BASE_URL).build(), TOPIC_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var topics = response.getBody();

        assertThat(topics).isNotNull();
        assertThat(topics.size()).isEqualTo(1);

        assertThat(topics.get(0).getName()).isEqualTo("Topic 1");
        assertThat(topics.get(0).getDescription()).isEqualTo("This is the first topic");
    }

    void testFindTopic() {
        var response = restTemplate.exchange(get(TOPIC_BASE_URL + "/1").build(), TOPIC);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var topic = response.getBody();

        assertThat(topic).isNotNull();

        assertThat(topic.getName()).isEqualTo("Topic 1");
        assertThat(topic.getDescription()).isEqualTo("This is the first topic");
    }

    void testUpdateTopic() {
        var body = new UpdateTopicRequest("Topic 1 Updated", "This is the first topic update");

        var response = restTemplate.exchange(put(TOPIC_BASE_URL + "/1").body(body), TOPIC);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedTopic = response.getBody();

        assertThat(updatedTopic).isNotNull();

        assertThat(updatedTopic.getName()).isEqualTo("Topic 1 Updated");
        assertThat(updatedTopic.getDescription()).isEqualTo("This is the first topic update");
    }

    void testDeleteTopic() {
        var response = restTemplate.exchange(delete(TOPIC_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedTopic = restTemplate.exchange(get(TOPIC_BASE_URL + "/1").build(), TOPIC);

        assertThat(deletedTopic.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateUser() {
        var body = new CreateUserRequest("User 1", "user@email.com");

        var response = restTemplate.exchange(post(USER_BASE_URL).body(body), USER);

        var createdUser = response.getBody();

        assertThat(createdUser).isNotNull();

        assertThat(createdUser.getUserId()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo("User 1");
        assertThat(createdUser.getEmail()).isEqualTo("user@email.com");
    }

    void testListUsers() {
        var response = restTemplate.exchange(get(USER_BASE_URL).build(), USER_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var users = response.getBody();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);

        assertThat(users.get(0).getName()).isEqualTo("User 1");
        assertThat(users.get(0).getEmail()).isEqualTo("user@email.com");
    }

    void testFindUser() {
        var response = restTemplate.exchange(get(USER_BASE_URL + "/1").build(), USER);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var user = response.getBody();

        assertThat(user).isNotNull();

        assertThat(user.getName()).isEqualTo("User 1");
        assertThat(user.getEmail()).isEqualTo("user@email.com");
    }

    void testUpdateUser() {
        var body = new UpdateUserRequest("User 1 Updated", "USER@EMAIL.COM");

        var response = restTemplate.exchange(put(USER_BASE_URL + "/1").body(body), USER);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedUser = response.getBody();

        assertThat(updatedUser).isNotNull();

        assertThat(updatedUser.getName()).isEqualTo("User 1 Updated");
        assertThat(updatedUser.getEmail()).isEqualTo("USER@EMAIL.COM");
    }

    void testDeleteUser() {
        var response = restTemplate.exchange(delete(USER_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedUser = restTemplate.exchange(get(USER_BASE_URL + "/1").build(), USER);

        assertThat(deletedUser.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateAchievement() {
        var body = new CreateAchievementRequest("New Achievement", 1L);

        var response = restTemplate.exchange(post(ACHIEVEMENT_BASE_URL).body(body), ACHIEVEMENT);

        var createdAchievement = response.getBody();

        assertThat(createdAchievement).isNotNull();

        assertThat(createdAchievement.getAchievementId()).isNotNull();
        assertThat(createdAchievement.getDescription()).isEqualTo("New Achievement");

        assertThat(createdAchievement.getUser().getUserId()).isEqualTo(1L);
        assertThat(createdAchievement.getUser().getName()).isEqualTo("User 1");
        assertThat(createdAchievement.getUser().getEmail()).isEqualTo("user@email.com");
    }

    void testListAchievements() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL).build(), ACHIEVEMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievements = response.getBody();

        assertThat(achievements).isNotNull();
        assertThat(achievements.size()).isEqualTo(1);

        assertThat(achievements.get(0).getAchievementId()).isEqualTo(1L);
        assertThat(achievements.get(0).getDescription()).isEqualTo("New Achievement");

        assertThat(achievements.get(0).getUser().getUserId()).isEqualTo(1L);
        assertThat(achievements.get(0).getUser().getName()).isEqualTo("User 1");
        assertThat(achievements.get(0).getUser().getEmail()).isEqualTo("user@email.com");
    }

    void testFindAchievement() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/1").build(), ACHIEVEMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievement = response.getBody();

        assertThat(achievement).isNotNull();

        assertThat(achievement.getAchievementId()).isEqualTo(1L);
        assertThat(achievement.getDescription()).isEqualTo("New Achievement");

        assertThat(achievement.getUser().getUserId()).isEqualTo(1L);
        assertThat(achievement.getUser().getName()).isEqualTo("User 1");
        assertThat(achievement.getUser().getEmail()).isEqualTo("user@email.com");
    }

    void testFindAchievementsByUserId() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/users/1").build(), ACHIEVEMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievements = response.getBody();

        assertThat(achievements).isNotNull();
        assertThat(achievements.size()).isEqualTo(1);

        assertThat(achievements.get(0).getAchievementId()).isEqualTo(1L);
        assertThat(achievements.get(0).getDescription()).isEqualTo("New Achievement");

        assertThat(achievements.get(0).getUser().getUserId()).isEqualTo(1L);
        assertThat(achievements.get(0).getUser().getName()).isEqualTo("User 1");
        assertThat(achievements.get(0).getUser().getEmail()).isEqualTo("user@email.com");
    }

    void testUpdateAchievement() {
        var body = new UpdateAchievementRequest("Updated Achievement", 1L);

        var response = restTemplate.exchange(put(ACHIEVEMENT_BASE_URL + "/1").body(body), ACHIEVEMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedAchievement = response.getBody();

        assertThat(updatedAchievement).isNotNull();

        assertThat(updatedAchievement.getAchievementId()).isEqualTo(1L);
        assertThat(updatedAchievement.getDescription()).isEqualTo("Updated Achievement");

        assertThat(updatedAchievement.getUser().getUserId()).isEqualTo(1L);
        assertThat(updatedAchievement.getUser().getName()).isEqualTo("User 1 Updated");
        assertThat(updatedAchievement.getUser().getEmail()).isEqualTo("USER@EMAIL.COM");
    }

    void testDeleteAchievement() {
        var response = restTemplate.exchange(delete(ACHIEVEMENT_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedAchievement = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/1").build(), ACHIEVEMENT);

        assertThat(deletedAchievement.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateChapter() {
        var body = new CreateChapterRequest("Chapter 1", "This is the first chapter", 1L);

        var response = restTemplate.exchange(post(CHAPTER_BASE_URL).body(body), CHAPTER);

        var createdChapter = response.getBody();

        assertThat(createdChapter).isNotNull();

        assertThat(createdChapter.getChapterId()).isNotNull();
        assertThat(createdChapter.getName()).isEqualTo("Chapter 1");
        assertThat(createdChapter.getDescription()).isEqualTo("This is the first chapter");

        assertThat(createdChapter.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(createdChapter.getCourse().getName()).isEqualTo("Course 1");
        assertThat(createdChapter.getCourse().getDescription()).isEqualTo("This is the first course");
    }

    void testListChapters() {
        var response = restTemplate.exchange(get(CHAPTER_BASE_URL).build(), CHAPTER_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var chapters = response.getBody();

        assertThat(chapters).isNotNull();
        assertThat(chapters.size()).isEqualTo(1);

        assertThat(chapters.get(0).getName()).isEqualTo("Chapter 1");
        assertThat(chapters.get(0).getDescription()).isEqualTo("This is the first chapter");
    }

    void testFindChapter() {
        var response = restTemplate.exchange(get(CHAPTER_BASE_URL + "/1").build(), CHAPTER);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var chapter = response.getBody();

        assertThat(chapter).isNotNull();

        assertThat(chapter.getName()).isEqualTo("Chapter 1");
        assertThat(chapter.getDescription()).isEqualTo("This is the first chapter");
    }

    void testUpdateChapter() {
        var body = new UpdateChapterRequest("Chapter 1 Updated", "This is the first chapter update", 1L);

        var response = restTemplate.exchange(put(CHAPTER_BASE_URL + "/1").body(body), CHAPTER);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedChapter = response.getBody();

        assertThat(updatedChapter).isNotNull();

        assertThat(updatedChapter.getName()).isEqualTo("Chapter 1 Updated");
        assertThat(updatedChapter.getDescription()).isEqualTo("This is the first chapter update");
    }

    void testDeleteChapter() {
        var response = restTemplate.exchange(delete(CHAPTER_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedChapter = restTemplate.exchange(get(CHAPTER_BASE_URL + "/1").build(), CHAPTER);

        assertThat(deletedChapter.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreatePost() {
        var body = new CreatePostRequest("Post 1", "This is the first post", 1L);

        var response = restTemplate.exchange(post(POST_BASE_URL).body(body), POST);

        var createdPost = response.getBody();

        assertThat(createdPost).isNotNull();

        assertThat(createdPost.getPostId()).isNotNull();
        assertThat(createdPost.getTitle()).isEqualTo("Post 1");
        assertThat(createdPost.getContent()).isEqualTo("This is the first post");

        assertThat(createdPost.getForum().getForumId()).isEqualTo(1L);
        assertThat(createdPost.getForum().getName()).isEqualTo("Forum 1");
        assertThat(createdPost.getForum().getDescription()).isEqualTo("This is the first forum");
    }

    void testListPosts() {
        var response = restTemplate.exchange(get(POST_BASE_URL).build(), POST_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var posts = response.getBody();

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(1);

        assertThat(posts.get(0).getTitle()).isEqualTo("Post 1");
        assertThat(posts.get(0).getContent()).isEqualTo("This is the first post");

        assertThat(posts.get(0).getForum().getForumId()).isEqualTo(1L);
        assertThat(posts.get(0).getForum().getName()).isEqualTo("Forum 1");
        assertThat(posts.get(0).getForum().getDescription()).isEqualTo("This is the first forum");
    }

    void testFindPost() {
        var response = restTemplate.exchange(get(POST_BASE_URL + "/1").build(), POST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var post = response.getBody();

        assertThat(post).isNotNull();

        assertThat(post.getPostId()).isNotNull();
        assertThat(post.getTitle()).isEqualTo("Post 1");
        assertThat(post.getContent()).isEqualTo("This is the first post");

        assertThat(post.getForum().getForumId()).isEqualTo(1L);
        assertThat(post.getForum().getName()).isEqualTo("Forum 1");
        assertThat(post.getForum().getDescription()).isEqualTo("This is the first forum");
    }

    void testUpdatePost() {
        var body = new UpdatePostRequest("Post 1 Updated", "This is the first post update", 1L);

        var response = restTemplate.exchange(put(POST_BASE_URL + "/1").body(body), POST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedPost = response.getBody();

        assertThat(updatedPost).isNotNull();

        assertThat(updatedPost.getTitle()).isEqualTo("Post 1 Updated");
        assertThat(updatedPost.getContent()).isEqualTo("This is the first post update");

        assertThat(updatedPost.getForum().getForumId()).isEqualTo(1L);
        assertThat(updatedPost.getForum().getName()).isEqualTo("Forum 1 Updated");
        assertThat(updatedPost.getForum().getDescription()).isEqualTo("This is the first forum update");
    }

    void testDeletePost() {
        var response = restTemplate.exchange(delete(POST_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedPost = restTemplate.exchange(get(POST_BASE_URL + "/1").build(), POST);

        assertThat(deletedPost.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateSection() {
        var body = new CreateSectionRequest("Section 1", "This is the first section", 1L, 1L);

        var response = restTemplate.exchange(post(SECTION_BASE_URL).body(body), SECTION);

        var createdSection = response.getBody();

        assertThat(createdSection).isNotNull();

        assertThat(createdSection.getSectionId()).isNotNull();
        assertThat(createdSection.getName()).isEqualTo("Section 1");
        assertThat(createdSection.getDescription()).isEqualTo("This is the first section");

        assertThat(createdSection.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(createdSection.getCourse().getName()).isEqualTo("Course 1");
        assertThat(createdSection.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(createdSection.getChapter().getChapterId()).isEqualTo(1L);
        assertThat(createdSection.getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(createdSection.getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testListSections() {
        var response = restTemplate.exchange(get(SECTION_BASE_URL).build(), SECTION_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var sections = response.getBody();

        assertThat(sections).isNotNull();
        assertThat(sections.size()).isEqualTo(1);

        assertThat(sections.get(0).getName()).isEqualTo("Section 1");
        assertThat(sections.get(0).getDescription()).isEqualTo("This is the first section");

        assertThat(sections.get(0).getCourse().getCourseId()).isEqualTo(1L);
        assertThat(sections.get(0).getCourse().getName()).isEqualTo("Course 1");
        assertThat(sections.get(0).getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(sections.get(0).getChapter().getChapterId()).isEqualTo(1L);
        assertThat(sections.get(0).getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(sections.get(0).getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testFindSection() {
        var response = restTemplate.exchange(get(SECTION_BASE_URL + "/1").build(), SECTION);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var section = response.getBody();

        assertThat(section).isNotNull();

        assertThat(section.getSectionId()).isNotNull();
        assertThat(section.getName()).isEqualTo("Section 1");
        assertThat(section.getDescription()).isEqualTo("This is the first section");

        assertThat(section.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(section.getCourse().getName()).isEqualTo("Course 1");
        assertThat(section.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(section.getChapter().getChapterId()).isEqualTo(1L);
        assertThat(section.getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(section.getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testUpdateSection() {
        var body = new UpdateSectionRequest("Section 1 Updated", "This is the first section update", 1L, 1L);

        var response = restTemplate.exchange(put(SECTION_BASE_URL + "/1").body(body), SECTION);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedSection = response.getBody();

        assertThat(updatedSection).isNotNull();

        assertThat(updatedSection.getName()).isEqualTo("Section 1 Updated");
        assertThat(updatedSection.getDescription()).isEqualTo("This is the first section update");

        assertThat(updatedSection.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(updatedSection.getCourse().getName()).isEqualTo("Course 1 Updated");
        assertThat(updatedSection.getCourse().getDescription()).isEqualTo("This is the first course update");

        assertThat(updatedSection.getChapter().getChapterId()).isEqualTo(1L);
        assertThat(updatedSection.getChapter().getName()).isEqualTo("Chapter 1 Updated");
        assertThat(updatedSection.getChapter().getDescription()).isEqualTo("This is the first chapter update");
    }

    void testDeleteSection() {
        var response = restTemplate.exchange(delete(SECTION_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedSection = restTemplate.exchange(get(SECTION_BASE_URL + "/1").build(), SECTION);

        assertThat(deletedSection.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateProject() {
        var body = new CreateProjectRequest("Project 1", "This is the first project", 1L, 1L);

        var response = restTemplate.exchange(post(PROJECT_BASE_URL).body(body), PROJECT);

        var createdProject = response.getBody();

        assertThat(createdProject).isNotNull();

        assertThat(createdProject.getProjectId()).isNotNull();
        assertThat(createdProject.getName()).isEqualTo("Project 1");
        assertThat(createdProject.getDescription()).isEqualTo("This is the first project");

        assertThat(createdProject.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(createdProject.getCourse().getName()).isEqualTo("Course 1");
        assertThat(createdProject.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(createdProject.getSection().getSectionId()).isEqualTo(1L);
        assertThat(createdProject.getSection().getName()).isEqualTo("Section 1");
        assertThat(createdProject.getSection().getDescription()).isEqualTo("This is the first section");
    }

    void testListProjects() {
        var response = restTemplate.exchange(get(PROJECT_BASE_URL).build(), PROJECT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var projects = response.getBody();

        assertThat(projects).isNotNull();
        assertThat(projects.size()).isEqualTo(1);

        assertThat(projects.get(0).getName()).isEqualTo("Project 1");
        assertThat(projects.get(0).getDescription()).isEqualTo("This is the first project");

        assertThat(projects.get(0).getCourse().getCourseId()).isEqualTo(1L);
        assertThat(projects.get(0).getCourse().getName()).isEqualTo("Course 1");
        assertThat(projects.get(0).getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(projects.get(0).getSection().getSectionId()).isEqualTo(1L);
        assertThat(projects.get(0).getSection().getName()).isEqualTo("Section 1");
        assertThat(projects.get(0).getSection().getDescription()).isEqualTo("This is the first section");
    }

    void testFindProject() {
        var response = restTemplate.exchange(get(PROJECT_BASE_URL + "/1").build(), PROJECT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var project = response.getBody();

        assertThat(project).isNotNull();

        assertThat(project.getProjectId()).isNotNull();
        assertThat(project.getName()).isEqualTo("Project 1");
        assertThat(project.getDescription()).isEqualTo("This is the first project");

        assertThat(project.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(project.getCourse().getName()).isEqualTo("Course 1");
        assertThat(project.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(project.getSection().getSectionId()).isEqualTo(1L);
        assertThat(project.getSection().getName()).isEqualTo("Section 1");
        assertThat(project.getSection().getDescription()).isEqualTo("This is the first section");
    }

    void testUpdateProject() {
        var body = new UpdateProjectRequest("Project 1 Updated", "This is the first project update", 1L, 1L);

        var response = restTemplate.exchange(put(PROJECT_BASE_URL + "/1").body(body), PROJECT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedProject = response.getBody();

        assertThat(updatedProject).isNotNull();

        assertThat(updatedProject.getName()).isEqualTo("Project 1 Updated");
        assertThat(updatedProject.getDescription()).isEqualTo("This is the first project update");

        assertThat(updatedProject.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(updatedProject.getCourse().getName()).isEqualTo("Course 1 Updated");
        assertThat(updatedProject.getCourse().getDescription()).isEqualTo("This is the first course update");

        assertThat(updatedProject.getSection().getSectionId()).isEqualTo(1L);
        assertThat(updatedProject.getSection().getName()).isEqualTo("Section 1 Updated");
        assertThat(updatedProject.getSection().getDescription()).isEqualTo("This is the first section update");
    }

    void testDeleteProject() {
        var response = restTemplate.exchange(delete(PROJECT_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedProject = restTemplate.exchange(get(PROJECT_BASE_URL + "/1").build(), PROJECT);

        assertThat(deletedProject.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateComment() {
        var body = new CreateCommentRequest("Comment 1", 1L, 1L);

        var response = restTemplate.exchange(post(COMMENT_BASE_URL).body(body), COMMENT);

        var createdComment = response.getBody();

        assertThat(createdComment).isNotNull();

        assertThat(createdComment.getCommentId()).isNotNull();
        assertThat(createdComment.getContent()).isEqualTo("Comment 1");

        assertThat(createdComment.getUser().getUserId()).isEqualTo(1L);
        assertThat(createdComment.getUser().getName()).isEqualTo("User 1");
        assertThat(createdComment.getUser().getEmail()).isEqualTo("user@email.com");

        assertThat(createdComment.getPost().getPostId()).isNotNull();
        assertThat(createdComment.getPost().getTitle()).isEqualTo("Post 1");
        assertThat(createdComment.getPost().getContent()).isEqualTo("This is the first post");
    }

    void testListComments() {
        var response = restTemplate.exchange(get(COMMENT_BASE_URL).build(), COMMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var comments = response.getBody();

        assertThat(comments).isNotNull();
        assertThat(comments.size()).isEqualTo(1);

        assertThat(comments.get(0).getContent()).isEqualTo("Comment 1");

        assertThat(comments.get(0).getUser().getUserId()).isEqualTo(1L);
        assertThat(comments.get(0).getUser().getName()).isEqualTo("User 1");
        assertThat(comments.get(0).getUser().getEmail()).isEqualTo("user@email.com");

        assertThat(comments.get(0).getPost().getPostId()).isNotNull();
        assertThat(comments.get(0).getPost().getTitle()).isEqualTo("Post 1");
        assertThat(comments.get(0).getPost().getContent()).isEqualTo("This is the first post");
    }

    void testFindComment() {
        var response = restTemplate.exchange(get(COMMENT_BASE_URL + "/1").build(), COMMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var comment = response.getBody();

        assertThat(comment).isNotNull();

        assertThat(comment.getCommentId()).isNotNull();
        assertThat(comment.getContent()).isEqualTo("Comment 1");

        assertThat(comment.getUser().getUserId()).isEqualTo(1L);
        assertThat(comment.getUser().getName()).isEqualTo("User 1");
        assertThat(comment.getUser().getEmail()).isEqualTo("user@email.com");

        assertThat(comment.getPost().getPostId()).isNotNull();
        assertThat(comment.getPost().getTitle()).isEqualTo("Post 1");
        assertThat(comment.getPost().getContent()).isEqualTo("This is the first post");
    }

    void testUpdateComment() {
        var body = new UpdateCommentRequest("Comment 1 Updated", 1L, 1L);

        var response = restTemplate.exchange(put(COMMENT_BASE_URL + "/1").body(body), COMMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedComment = response.getBody();

        assertThat(updatedComment).isNotNull();

        assertThat(updatedComment.getContent()).isEqualTo("Comment 1 Updated");

        assertThat(updatedComment.getUser().getUserId()).isEqualTo(1L);
        assertThat(updatedComment.getUser().getName()).isEqualTo("User 1 Updated");
        assertThat(updatedComment.getUser().getEmail()).isEqualTo("USER@EMAIL.COM");

        assertThat(updatedComment.getPost().getPostId()).isEqualTo(1L);
        assertThat(updatedComment.getPost().getTitle()).isEqualTo("Post 1 Updated");
        assertThat(updatedComment.getPost().getContent()).isEqualTo("This is the first post update");
    }

    void testDeleteComment() {
        var response = restTemplate.exchange(delete(COMMENT_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedComment = restTemplate.exchange(get(COMMENT_BASE_URL + "/1").build(), COMMENT);

        assertThat(deletedComment.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateExercise() {
        var body = new CreateExerciseRequest("Exercise 1", "This is the first exercise", 1L, 1L);

        var response = restTemplate.exchange(post(EXERCISE_BASE_URL).body(body), EXERCISE);

        var createdExercise = response.getBody();

        assertThat(createdExercise).isNotNull();

        assertThat(createdExercise.getExerciseId()).isNotNull();
        assertThat(createdExercise.getName()).isEqualTo("Exercise 1");
        assertThat(createdExercise.getDescription()).isEqualTo("This is the first exercise");

        assertThat(createdExercise.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(createdExercise.getCourse().getName()).isEqualTo("Course 1");
        assertThat(createdExercise.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(createdExercise.getChapter().getChapterId()).isNotNull();
        assertThat(createdExercise.getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(createdExercise.getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testListExercises() {
        var response = restTemplate.exchange(get(EXERCISE_BASE_URL).build(), EXERCISE_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var exercises = response.getBody();

        assertThat(exercises).isNotNull();
        assertThat(exercises.size()).isEqualTo(1);

        assertThat(exercises.get(0).getExerciseId()).isNotNull();
        assertThat(exercises.get(0).getName()).isEqualTo("Exercise 1");
        assertThat(exercises.get(0).getDescription()).isEqualTo("This is the first exercise");

        assertThat(exercises.get(0).getCourse().getCourseId()).isEqualTo(1L);
        assertThat(exercises.get(0).getCourse().getName()).isEqualTo("Course 1");
        assertThat(exercises.get(0).getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(exercises.get(0).getChapter().getChapterId()).isNotNull();
        assertThat(exercises.get(0).getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(exercises.get(0).getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testFindExercise() {
        var response = restTemplate.exchange(get(EXERCISE_BASE_URL + "/1").build(), EXERCISE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var exercise = response.getBody();

        assertThat(exercise).isNotNull();

        assertThat(exercise.getExerciseId()).isNotNull();
        assertThat(exercise.getName()).isEqualTo("Exercise 1");
        assertThat(exercise.getDescription()).isEqualTo("This is the first exercise");

        assertThat(exercise.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(exercise.getCourse().getName()).isEqualTo("Course 1");
        assertThat(exercise.getCourse().getDescription()).isEqualTo("This is the first course");

        assertThat(exercise.getChapter().getChapterId()).isNotNull();
        assertThat(exercise.getChapter().getName()).isEqualTo("Chapter 1");
        assertThat(exercise.getChapter().getDescription()).isEqualTo("This is the first chapter");
    }

    void testUpdateExercise() {
        var body = new UpdateExerciseRequest("Exercise 1 Updated", "This is the first exercise update", 1L, 1L);

        var response = restTemplate.exchange(put(EXERCISE_BASE_URL + "/1").body(body), EXERCISE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedExercise = response.getBody();

        assertThat(updatedExercise).isNotNull();

        assertThat(updatedExercise.getExerciseId()).isNotNull();
        assertThat(updatedExercise.getName()).isEqualTo("Exercise 1 Updated");
        assertThat(updatedExercise.getDescription()).isEqualTo("This is the first exercise update");

        assertThat(updatedExercise.getCourse().getCourseId()).isEqualTo(1L);
        assertThat(updatedExercise.getCourse().getName()).isEqualTo("Course 1 Updated");
        assertThat(updatedExercise.getCourse().getDescription()).isEqualTo("This is the first course update");

        assertThat(updatedExercise.getChapter().getChapterId()).isEqualTo(1L);
        assertThat(updatedExercise.getChapter().getName()).isEqualTo("Chapter 1 Updated");
        assertThat(updatedExercise.getChapter().getDescription()).isEqualTo("This is the first chapter update");
    }

    void testDeleteExercise() {
        var response = restTemplate.exchange(delete(EXERCISE_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedExercise = restTemplate.exchange(get(EXERCISE_BASE_URL + "/1").build(), EXERCISE);

        assertThat(deletedExercise.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateLesson() {
        var body = new CreateLessonRequest("Lesson 1", "This is the first lesson", 1L);

        var response = restTemplate.exchange(post(LESSON_BASE_URL).body(body), LESSON);

        var createdLesson = response.getBody();

        assertThat(createdLesson).isNotNull();

        assertThat(createdLesson.getLessonId()).isNotNull();
        assertThat(createdLesson.getName()).isEqualTo("Lesson 1");
        assertThat(createdLesson.getDescription()).isEqualTo("This is the first lesson");

        assertThat(createdLesson.getTopic().getTopicId()).isEqualTo(1L);
        assertThat(createdLesson.getTopic().getName()).isEqualTo("Topic 1");
        assertThat(createdLesson.getTopic().getDescription()).isEqualTo("This is the first topic");
    }

    void testListLessons() {
        var response = restTemplate.exchange(get(LESSON_BASE_URL).build(), LESSON_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var lessons = response.getBody();

        assertThat(lessons).isNotNull();
        assertThat(lessons.size()).isEqualTo(1);

        assertThat(lessons.get(0).getLessonId()).isNotNull();
        assertThat(lessons.get(0).getName()).isEqualTo("Lesson 1");
        assertThat(lessons.get(0).getDescription()).isEqualTo("This is the first lesson");

        assertThat(lessons.get(0).getTopic().getTopicId()).isEqualTo(1L);
        assertThat(lessons.get(0).getTopic().getName()).isEqualTo("Topic 1");
        assertThat(lessons.get(0).getTopic().getDescription()).isEqualTo("This is the first topic");
    }

    void testFindLesson() {
        var response = restTemplate.exchange(get(LESSON_BASE_URL + "/1").build(), LESSON);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var lesson = response.getBody();

        assertThat(lesson).isNotNull();

        assertThat(lesson.getLessonId()).isNotNull();
        assertThat(lesson.getName()).isEqualTo("Lesson 1");
        assertThat(lesson.getDescription()).isEqualTo("This is the first lesson");

        assertThat(lesson.getTopic().getTopicId()).isEqualTo(1L);
        assertThat(lesson.getTopic().getName()).isEqualTo("Topic 1");
        assertThat(lesson.getTopic().getDescription()).isEqualTo("This is the first topic");
    }

    void testUpdateLesson() {
        var body = new UpdateLessonRequest("Lesson 1 Updated", "This is the first lesson update", 1L);

        var response = restTemplate.exchange(put(LESSON_BASE_URL + "/1").body(body), LESSON);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedLesson = response.getBody();

        assertThat(updatedLesson).isNotNull();

        assertThat(updatedLesson.getLessonId()).isNotNull();
        assertThat(updatedLesson.getName()).isEqualTo("Lesson 1 Updated");
        assertThat(updatedLesson.getDescription()).isEqualTo("This is the first lesson update");

        assertThat(updatedLesson.getTopic().getTopicId()).isEqualTo(1L);
        assertThat(updatedLesson.getTopic().getName()).isEqualTo("Topic 1 Updated");
        assertThat(updatedLesson.getTopic().getDescription()).isEqualTo("This is the first topic update");
    }

    void testDeleteLesson() {
        var response = restTemplate.exchange(delete(LESSON_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedLesson = restTemplate.exchange(get(LESSON_BASE_URL + "/1").build(), LESSON);

        assertThat(deletedLesson.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateQuiz() {
        var body = new CreateQuizRequest("Quiz 1", "This is the first quiz", 1L);

        var response = restTemplate.exchange(post(QUIZ_BASE_URL).body(body), QUIZ);

        var createdQuiz = response.getBody();

        assertThat(createdQuiz).isNotNull();

        assertThat(createdQuiz.quizId()).isNotNull();
        assertThat(createdQuiz.name()).isEqualTo("Quiz 1");
        assertThat(createdQuiz.description()).isEqualTo("This is the first quiz");

        assertThat(createdQuiz.lessonId()).isEqualTo(1L);
    }

    void testListQuizzes() {
        var response = restTemplate.exchange(get(QUIZ_BASE_URL).build(), QUIZ_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var quizzes = response.getBody();

        assertThat(quizzes).isNotNull();
        assertThat(quizzes.size()).isEqualTo(1);

        assertThat(quizzes.get(0).quizId()).isNotNull();
        assertThat(quizzes.get(0).name()).isEqualTo("Quiz 1");
        assertThat(quizzes.get(0).description()).isEqualTo("This is the first quiz");

        assertThat(quizzes.get(0).lessonId()).isEqualTo(1L);
    }

    void testFindQuiz() {
        var response = restTemplate.exchange(get(QUIZ_BASE_URL + "/1").build(), QUIZ);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var quiz = response.getBody();

        assertThat(quiz).isNotNull();

        assertThat(quiz.quizId()).isNotNull();
        assertThat(quiz.name()).isEqualTo("Quiz 1");
        assertThat(quiz.description()).isEqualTo("This is the first quiz");

        assertThat(quiz.lessonId()).isEqualTo(1L);
    }

    void testUpdateQuiz() {
        var body = new UpdateQuizRequest("Quiz 1 Updated", "This is the first quiz update", 1L);

        var response = restTemplate.exchange(put(QUIZ_BASE_URL + "/1").body(body), QUIZ);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedQuiz = response.getBody();

        assertThat(updatedQuiz).isNotNull();

        assertThat(updatedQuiz.quizId()).isNotNull();
        assertThat(updatedQuiz.name()).isEqualTo("Quiz 1 Updated");
        assertThat(updatedQuiz.description()).isEqualTo("This is the first quiz update");

        assertThat(updatedQuiz.lessonId()).isEqualTo(1L);
    }

    void testDeleteQuiz() {
        var response = restTemplate.exchange(delete(QUIZ_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedQuiz = restTemplate.exchange(get(QUIZ_BASE_URL + "/1").build(), QUIZ);

        assertThat(deletedQuiz.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    //@formatter:off
    static final String FORUM_BASE_URL = "/api/forums";
    static final String COURSE_BASE_URL = "/api/courses";
    static final String TOPIC_BASE_URL = "/api/topics";
    static final String USER_BASE_URL = "/api/users";
    static final String ACHIEVEMENT_BASE_URL = "/api/achievements";
    static final String CHAPTER_BASE_URL = "/api/chapters";
    static final String POST_BASE_URL = "/api/posts";
    static final String SECTION_BASE_URL = "/api/sections";
    static final String PROJECT_BASE_URL = "/api/projects";
    static final String COMMENT_BASE_URL = "/api/comments";
    static final String EXERCISE_BASE_URL = "/api/exercises";
    static final String LESSON_BASE_URL = "/api/lessons";
    static final String QUIZ_BASE_URL = "/api/quizzes";

    static final ParameterizedTypeReference<Void> VOID = new ParameterizedTypeReference<>() {};

    static final ParameterizedTypeReference<Forum> FORUM = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Forum>> FORUM_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Course> COURSE = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Course>> COURSE_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Topic> TOPIC = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Topic>> TOPIC_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<User> USER = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<User>> USER_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Achievement> ACHIEVEMENT = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Achievement>> ACHIEVEMENT_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Chapter> CHAPTER = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Chapter>> CHAPTER_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Post> POST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Post>> POST_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Section> SECTION = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Section>> SECTION_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Project> PROJECT = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Project>> PROJECT_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Comment> COMMENT = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Comment>> COMMENT_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Exercise> EXERCISE = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Exercise>> EXERCISE_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Lesson> LESSON = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Lesson>> LESSON_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<QuizInfoResponse> QUIZ = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<QuizInfoResponse>> QUIZ_LIST = new ParameterizedTypeReference<>() {};
    //@formatter:on
}
