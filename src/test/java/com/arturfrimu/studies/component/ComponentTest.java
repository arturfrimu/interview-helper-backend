package com.arturfrimu.studies.component;

import com.arturfrimu.studies.dto.request.Requests;
import com.arturfrimu.studies.dto.request.Requests.CreateChapterRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateCourseRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateUserRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateChapterRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.entity.Chapter;
import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.entity.Forum;
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


        testUpdateForum();
        testUpdateCourse();
        testUpdateTopic();
        testUpdateUser();
        testUpdateAchievement();
        testUpdateChapter();


        testDeleteForum();
        testDeleteChapter();
        testDeleteCourse();
        testDeleteTopic();
        testDeleteAchievement();
        testDeleteUser();
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
        var body = new Requests.UpdateForumRequest("Forum 1 Updated", "This is the first forum updated");

        var response = restTemplate.exchange(put(FORUM_BASE_URL + "/1").body(body), FORUM);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedForum = response.getBody();

        assertThat(updatedForum).isNotNull();

        assertThat(updatedForum.getName()).isEqualTo("Forum 1 Updated");
        assertThat(updatedForum.getDescription()).isEqualTo("This is the first forum updated");
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
        var body = new Requests.UpdateCourseRequest("Course 1 Updated", "This is the first course updated");

        var response = restTemplate.exchange(put(COURSE_BASE_URL + "/1").body(body), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedCourse = response.getBody();

        assertThat(updatedCourse).isNotNull();

        assertThat(updatedCourse.getName()).isEqualTo("Course 1 Updated");
        assertThat(updatedCourse.getDescription()).isEqualTo("This is the first course updated");
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
        var body = new UpdateTopicRequest("Topic 1 Updated", "This is the first topic updated");

        var response = restTemplate.exchange(put(TOPIC_BASE_URL + "/1").body(body), TOPIC);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedTopic = response.getBody();

        assertThat(updatedTopic).isNotNull();

        assertThat(updatedTopic.getName()).isEqualTo("Topic 1 Updated");
        assertThat(updatedTopic.getDescription()).isEqualTo("This is the first topic updated");
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    //@formatter:off
    static final String FORUM_BASE_URL = "/api/forums";
    static final String COURSE_BASE_URL = "/api/courses";
    static final String TOPIC_BASE_URL = "/api/topics";
    static final String USER_BASE_URL = "/api/users";
    static final String ACHIEVEMENT_BASE_URL = "/api/achievements";
    static final String CHAPTER_BASE_URL = "/api/chapters";

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
    //@formatter:on
}
