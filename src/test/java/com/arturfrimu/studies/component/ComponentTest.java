package com.arturfrimu.studies.component;

import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.entity.Forum;
import com.arturfrimu.studies.repository.ForumRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    @Autowired
    ForumRepository forumRepository;

    @AfterEach
    void tearDown() {
        forumRepository.deleteAll();
    }

    @Test
    void testLifecycle() {
        testCreateForum();
        testGetAllForums();
        testGetForumById();
        testUpdateForum();
        testDeleteForum();

        testCreateCourse();
        testGetAllCourses();
        testGetCourseById();
        testUpdateCourse();
        testDeleteCourse();
    }

    void testCreateForum() {
        var body = new Forum();
        body.setName("Forum 1");
        body.setDescription("This is the first forum");

        var response = restTemplate.exchange(post(FORUM_BASE_URL).body(body), FORUM);

        var createdForum = response.getBody();

        assertThat(createdForum).isNotNull();
        assertThat(createdForum.getName()).isEqualTo("Forum 1");
        assertThat(createdForum.getDescription()).isEqualTo("This is the first forum");
    }

    void testGetAllForums() {
        var response = restTemplate.exchange(get(FORUM_BASE_URL).build(), FORUM_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var forums = response.getBody();

        assertThat(forums).isNotNull();
        assertThat(forums.size()).isEqualTo(1);
        assertThat(forums.get(0).getName()).isEqualTo("Forum 1");
        assertThat(forums.get(0).getDescription()).isEqualTo("This is the first forum");
    }

    void testGetForumById() {
        var response = restTemplate.exchange(get(FORUM_BASE_URL + "/1").build(), FORUM);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var forum = response.getBody();

        assertThat(forum).isNotNull();
        assertThat(forum.getName()).isEqualTo("Forum 1");
        assertThat(forum.getDescription()).isEqualTo("This is the first forum");
    }

    void testUpdateForum() {
        var body = new Forum();
        body.setName("Forum 1 Updated");
        body.setDescription("This is the first forum updated");

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

        assertThat(deletedForum.getBody()).isNull();
    }

    void testCreateCourse() {
        var body = new Course();
        body.setName("Course 1");
        body.setDescription("This is the first course");

        var response = restTemplate.exchange(post(COURSE_BASE_URL).body(body), COURSE);

        var createdCourse = response.getBody();

        assertThat(createdCourse).isNotNull();
        assertThat(createdCourse.getName()).isEqualTo("Course 1");
        assertThat(createdCourse.getDescription()).isEqualTo("This is the first course");
    }

    void testGetAllCourses() {
        var response = restTemplate.exchange(get(COURSE_BASE_URL).build(), COURSE_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var courses = response.getBody();

        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getName()).isEqualTo("Course 1");
        assertThat(courses.get(0).getDescription()).isEqualTo("This is the first course");
    }

    void testGetCourseById() {
        var response = restTemplate.exchange(get(COURSE_BASE_URL + "/1").build(), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var course = response.getBody();

        assertThat(course).isNotNull();
        assertThat(course.getName()).isEqualTo("Course 1");
        assertThat(course.getDescription()).isEqualTo("This is the first course");
    }

    void testUpdateCourse() {
        var body = new Course();
        body.setName("Course 1 Updated");
        body.setDescription("This is the first course updated");

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

    //@formatter:off
    static final String FORUM_BASE_URL = "/api/forums";
    static final String COURSE_BASE_URL = "/api/courses";

    static final ParameterizedTypeReference<Forum> FORUM = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Forum>> FORUM_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Course> COURSE = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Course>> COURSE_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Void> VOID = new ParameterizedTypeReference<>() {};
    //@formatter:on
}
