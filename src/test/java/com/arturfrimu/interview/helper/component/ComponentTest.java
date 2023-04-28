package com.arturfrimu.interview.helper.component;

import com.arturfrimu.interview.helper.dto.request.Requests;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateCommentRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateCourseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateExerciseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateLessonRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreatePostRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateProjectRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateQuizRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateSectionRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateUserRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateCommentRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateCourseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateExerciseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateLessonRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdatePostRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateProjectRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateQuizRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateSectionRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.interview.helper.dto.response.Response.CourseInfoResponse;
import com.arturfrimu.interview.helper.dto.response.Response.LessonInfoResponse;
import com.arturfrimu.interview.helper.dto.response.Response.ProjectDetailsResponse;
import com.arturfrimu.interview.helper.dto.response.Response.ProjectInfoResponse;
import com.arturfrimu.interview.helper.dto.response.Response.QuizInfoResponse;
import com.arturfrimu.interview.helper.dto.response.Response.SectionInfoResponse;
import com.arturfrimu.interview.helper.dto.response.Response.TopicInfoResponse;
import com.arturfrimu.interview.helper.entity.Achievement;
import com.arturfrimu.interview.helper.entity.Comment;
import com.arturfrimu.interview.helper.entity.Exercise;
import com.arturfrimu.interview.helper.entity.Forum;
import com.arturfrimu.interview.helper.entity.Post;
import com.arturfrimu.interview.helper.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.arturfrimu.interview.helper.dto.request.Requests.CreateAchievementRequest;
import static com.arturfrimu.interview.helper.dto.request.Requests.UpdateAchievementRequest;
import static com.arturfrimu.interview.helper.dto.request.Requests.UpdateUserRequest;
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
        var createdProjectId = testCreateProject();
        var foundProjectId = testFindProject(createdProjectId);
        testListProjects();


        var createdTopicId = testCreateTopic(foundProjectId);
        var foundTopicId = testFindTopic(createdTopicId);


        var createdCourseId = testCreateCourse(foundTopicId);
        var foundCourseId = testFindCourse(createdCourseId);


        var createdSectionId = testCreateSection(foundCourseId);
        var foundSectionId = testFindSection(createdSectionId, foundCourseId);


        var createdLessonId = testCreateLesson(foundSectionId);
        var foundLessonId = testFindLesson(createdLessonId, foundSectionId);


        var updatedProjectId = testUpdateProject(foundProjectId);
        var updatedTopicId = testUpdateTopic(foundTopicId, updatedProjectId);
        var updatedCourseId = testUpdateCourse(foundCourseId, updatedTopicId);
        var updateSectionId = testUpdateSection(foundSectionId, updatedCourseId);
        var updateLessonId = testUpdateLesson(foundLessonId, foundSectionId);
    }

    Long testCreateProject() {
        var body = new CreateProjectRequest("PROJECT NAME", "PROJECT DESCRIPTION");

        var response = restTemplate.exchange(post(PROJECT_BASE_URL).body(body), PROJECT_INFO);

        var createdProject = response.getBody();

        assertThat(createdProject).isNotNull();

        assertThat(createdProject.projectId()).isNotNull();
        assertThat(createdProject.name()).isEqualTo("PROJECT NAME");
        assertThat(createdProject.description()).isEqualTo("PROJECT DESCRIPTION");

        return createdProject.projectId();
    }

    void testListProjects() {
        var response = restTemplate.exchange(get(PROJECT_BASE_URL).build(), PROJECT_INFO_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var projects = response.getBody();

        assertThat(projects).isNotNull();
        assertThat(projects.size()).isGreaterThan(0);
    }

    Long testFindProject(Long projectId) {
        var response = restTemplate.exchange(get(PROJECT_BASE_URL + "/" + projectId).build(), PROJECT_DETAILS);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var project = response.getBody();

        assertThat(project).isNotNull();

        assertThat(project.projectId()).isNotNull();
        assertThat(project.name()).isEqualTo("PROJECT NAME");
        assertThat(project.description()).isEqualTo("PROJECT DESCRIPTION");
        assertThat(project.topics()).isNotNull();

        return project.projectId();
    }

    Long testUpdateProject(Long projectId) {
        var body = new UpdateProjectRequest("PROJECT NAME UPDATE", "PROJECT DESCRIPTION UPDATE");

        var response = restTemplate.exchange(put(PROJECT_BASE_URL + "/" + projectId)
                .body(body), PROJECT_INFO);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedProject = response.getBody();

        assertThat(updatedProject).isNotNull();

        assertThat(updatedProject.projectId()).isNotNull();
        assertThat(updatedProject.name()).isEqualTo("PROJECT NAME UPDATE");
        assertThat(updatedProject.description()).isEqualTo("PROJECT DESCRIPTION UPDATE");

        return updatedProject.projectId();
    }

    void testDeleteProject(Long projectId) {
        var response = restTemplate.exchange(delete(PROJECT_BASE_URL + "/" + projectId).build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedProject = restTemplate.exchange(get(PROJECT_BASE_URL + "/1").build(), PROJECT_INFO);

        assertThat(deletedProject.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    Long testCreateTopic(Long projectId) {
        var body = new CreateTopicRequest("TOPIC NAME", "TOPIC DESCRIPTION", projectId);

        var response = restTemplate.exchange(post(TOPIC_BASE_URL).body(body), TOPIC);

        var createdTopic = response.getBody();

        assertThat(createdTopic).isNotNull();

        assertThat(createdTopic.topicId()).isNotNull();
        assertThat(createdTopic.name()).isEqualTo("TOPIC NAME");
        assertThat(createdTopic.description()).isEqualTo("TOPIC DESCRIPTION");

        return createdTopic.topicId();
    }

    void testListTopics() {
        var response = restTemplate.exchange(get(TOPIC_BASE_URL).build(), TOPIC_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var topics = response.getBody();

        assertThat(topics).isNotNull();
        assertThat(topics.size()).isGreaterThan(0);
    }

    Long testFindTopic(Long topicId) {
        var response = restTemplate.exchange(get(TOPIC_BASE_URL + "/" + topicId).build(), TOPIC);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var topic = response.getBody();

        assertThat(topic).isNotNull();

        assertThat(topic.topicId()).isNotNull();
        assertThat(topic.name()).isEqualTo("TOPIC NAME");
        assertThat(topic.description()).isEqualTo("TOPIC DESCRIPTION");

        return topic.topicId();
    }

    Long testUpdateTopic(Long topicId, Long expectedProjectId) {
        var body = new UpdateTopicRequest("TOPIC NAME UPDATED", "TOPIC DESCRIPTION UPDATED", expectedProjectId);

        var response = restTemplate.exchange(put(TOPIC_BASE_URL + "/" + topicId).body(body), TOPIC);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedTopic = response.getBody();

        assertThat(updatedTopic).isNotNull();

        assertThat(updatedTopic.topicId()).isNotNull();
        assertThat(updatedTopic.name()).isEqualTo("TOPIC NAME UPDATED");
        assertThat(updatedTopic.description()).isEqualTo("TOPIC DESCRIPTION UPDATED");

        return updatedTopic.topicId();
    }

    void testDeleteTopic(Long topicId) {
        var response = restTemplate.exchange(delete(TOPIC_BASE_URL + "/" + topicId).build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedTopic = restTemplate.exchange(get(TOPIC_BASE_URL + "/1").build(), TOPIC);

        assertThat(deletedTopic.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    Long testCreateCourse(Long topicId) {
        var body = new CreateCourseRequest("COURSE NAME", "COURSE DESCRIPTION", topicId);

        var response = restTemplate.exchange(post(COURSE_BASE_URL).body(body), COURSE);

        var createdCourse = response.getBody();

        assertThat(createdCourse).isNotNull();

        assertThat(createdCourse.courseId()).isNotNull();
        assertThat(createdCourse.name()).isEqualTo("COURSE NAME");
        assertThat(createdCourse.description()).isEqualTo("COURSE DESCRIPTION");

        return createdCourse.courseId();
    }

    void testListCourses() {
        var response = restTemplate.exchange(get(COURSE_BASE_URL).build(), COURSE_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var courses = response.getBody();

        assertThat(courses).isNotNull();

        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).name()).isEqualTo("COURSE NAME");
        assertThat(courses.get(0).description()).isEqualTo("COURSE DESCRIPTION");
    }

    Long testFindCourse(Long courseId) {
        var response = restTemplate.exchange(get(COURSE_BASE_URL + "/" + courseId).build(), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var course = response.getBody();

        assertThat(course).isNotNull();

        assertThat(course.courseId()).isNotNull();
        assertThat(course.name()).isEqualTo("COURSE NAME");
        assertThat(course.description()).isEqualTo("COURSE DESCRIPTION");

        return course.courseId();
    }

    Long testUpdateCourse(Long courseId, Long newTopicId) {
        var body = new UpdateCourseRequest("COURSE NAME UPDATED", "COURSE DESCRIPTION UPDATED", newTopicId);

        var response = restTemplate.exchange(put(COURSE_BASE_URL + "/" + courseId).body(body), COURSE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedCourse = response.getBody();

        assertThat(updatedCourse).isNotNull();

        assertThat(updatedCourse.courseId()).isNotNull();
        assertThat(updatedCourse.name()).isEqualTo("COURSE NAME UPDATED");
        assertThat(updatedCourse.description()).isEqualTo("COURSE DESCRIPTION UPDATED");

        return updatedCourse.courseId();
    }

    void testDeleteCourse(Long courseId) {
        var URL = COURSE_BASE_URL + "/" + courseId;
        var response = restTemplate.exchange(delete(URL).build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedCourse = restTemplate.exchange(get(URL).build(), COURSE);

        assertThat(deletedCourse.getStatusCode()).isEqualTo(NOT_FOUND);
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

    Long testCreateSection(Long chapterId) {
        var body = new CreateSectionRequest("SECTION NAME", "SECTION DESCRIPTION", chapterId);

        var response = restTemplate.exchange(post(SECTION_BASE_URL).body(body), SECTION);

        var createdSection = response.getBody();

        assertThat(createdSection).isNotNull();

        assertThat(createdSection.sectionId()).isNotNull();
        assertThat(createdSection.name()).isEqualTo("SECTION NAME");
        assertThat(createdSection.description()).isEqualTo("SECTION DESCRIPTION");

        return createdSection.sectionId();
    }

    void testListSections() {
        var response = restTemplate.exchange(get(SECTION_BASE_URL).build(), SECTION_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var sections = response.getBody();

        assertThat(sections).isNotNull();
        assertThat(sections.size()).isEqualTo(1);

        assertThat(sections.get(0).name()).isEqualTo("SECTION NAME");
        assertThat(sections.get(0).description()).isEqualTo("SECTION DESCRIPTION");
    }

    Long testFindSection(Long sectionId, Long chapterId) {
        var response = restTemplate.exchange(get(SECTION_BASE_URL + "/" + sectionId).build(), SECTION);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var section = response.getBody();

        assertThat(section).isNotNull();

        assertThat(section.sectionId()).isEqualTo(sectionId);
        assertThat(section.name()).isEqualTo("SECTION NAME");
        assertThat(section.description()).isEqualTo("SECTION DESCRIPTION");

        return section.sectionId();
    }

    Long testUpdateSection(Long sectionId, Long chapterId) {
        var body = new UpdateSectionRequest("SECTION NAME UPDATE", "SECTION DESCRIPTION UPDATE", sectionId);

        var response = restTemplate.exchange(put(SECTION_BASE_URL + "/1").body(body), SECTION);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedSection = response.getBody();

        assertThat(updatedSection).isNotNull();

        assertThat(updatedSection.sectionId()).isEqualTo(sectionId);
        assertThat(updatedSection.name()).isEqualTo("SECTION NAME UPDATE");
        assertThat(updatedSection.description()).isEqualTo("SECTION DESCRIPTION UPDATE");

        return updatedSection.sectionId();
    }

    void testDeleteSection(Long sectionId) {
        var URL = SECTION_BASE_URL + "/" + sectionId;
        var response = restTemplate.exchange(delete(URL).build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedSection = restTemplate.exchange(get(URL).build(), SECTION);

        assertThat(deletedSection.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    Long testCreateLesson(Long sectionId) {
        var body = new CreateLessonRequest("LESSON NAME", "LESSON DESCRIPTION", sectionId);

        var response = restTemplate.exchange(post(LESSON_BASE_URL).body(body), LESSON);

        var createdLesson = response.getBody();

        assertThat(createdLesson).isNotNull();

        assertThat(createdLesson.lessonId()).isNotNull();
        assertThat(createdLesson.name()).isEqualTo("LESSON NAME");
        assertThat(createdLesson.description()).isEqualTo("LESSON DESCRIPTION");

        return createdLesson.lessonId();
    }

    void testListLessons() {
        var response = restTemplate.exchange(get(LESSON_BASE_URL).build(), LESSON_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var lessons = response.getBody();

        assertThat(lessons).isNotNull();
        assertThat(lessons.size()).isEqualTo(1);

        assertThat(lessons.get(0).lessonId()).isNotNull();
        assertThat(lessons.get(0).name()).isEqualTo("LESSON NAME");
        assertThat(lessons.get(0).description()).isEqualTo("LESSON DESCRIPTION");

    }

    Long testFindLesson(Long lessonId, Long sectionId) {
        var response = restTemplate.exchange(get(LESSON_BASE_URL + "/" + lessonId).build(), LESSON);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var lesson = response.getBody();

        assertThat(lesson).isNotNull();

        assertThat(lesson.lessonId()).isNotNull();
        assertThat(lesson.name()).isEqualTo("LESSON NAME");
        assertThat(lesson.description()).isEqualTo("LESSON DESCRIPTION");

        return lesson.lessonId();
    }

    Long testUpdateLesson(Long lessonId, Long sectionId) {
        var body = new UpdateLessonRequest("LESSON NAME UPDATE", "LESSON DESCRIPTION UPDATE", sectionId);

        var response = restTemplate.exchange(put(LESSON_BASE_URL + "/" + lessonId).body(body), LESSON);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedLesson = response.getBody();

        assertThat(updatedLesson).isNotNull();

        assertThat(updatedLesson.lessonId()).isNotNull();
        assertThat(updatedLesson.name()).isEqualTo("LESSON NAME UPDATE");
        assertThat(updatedLesson.description()).isEqualTo("LESSON DESCRIPTION UPDATE");

        return updatedLesson.lessonId();
    }

    void testDeleteLesson(Long lessonId) {
        var URL = LESSON_BASE_URL + "/" + lessonId;
        var response = restTemplate.exchange(delete(URL).build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedLesson = restTemplate.exchange(get(URL).build(), LESSON);

        assertThat(deletedLesson.getStatusCode()).isEqualTo(NOT_FOUND);
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

    void testCreateComment() {
        var body = new CreateCommentRequest("Comment 1", 1L, 1L);

        var response = restTemplate.exchange(post(COMMENT_BASE_URL).body(body), COMMENT);

        var createdComment = response.getBody();

        assertThat(createdComment).isNotNull();

        assertThat(createdComment.getCommentId()).isNotNull();
        assertThat(createdComment.getContent()).isEqualTo("Comment 1");
    }

    void testListComments() {
        var response = restTemplate.exchange(get(COMMENT_BASE_URL).build(), COMMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var comments = response.getBody();

        assertThat(comments).isNotNull();
        assertThat(comments.size()).isEqualTo(1);

        assertThat(comments.get(0).getContent()).isEqualTo("Comment 1");
    }

    void testFindComment() {
        var response = restTemplate.exchange(get(COMMENT_BASE_URL + "/1").build(), COMMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var comment = response.getBody();

        assertThat(comment).isNotNull();

        assertThat(comment.getCommentId()).isNotNull();
        assertThat(comment.getContent()).isEqualTo("Comment 1");
    }

    void testUpdateComment() {
        var body = new UpdateCommentRequest("Comment 1 Updated", 1L, 1L);

        var response = restTemplate.exchange(put(COMMENT_BASE_URL + "/1").body(body), COMMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedComment = response.getBody();

        assertThat(updatedComment).isNotNull();

        assertThat(updatedComment.getContent()).isEqualTo("Comment 1 Updated");
    }

    void testDeleteComment() {
        var response = restTemplate.exchange(delete(COMMENT_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedComment = restTemplate.exchange(get(COMMENT_BASE_URL + "/1").build(), COMMENT);

        assertThat(deletedComment.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateAchievement() {
        var body = new CreateAchievementRequest("New Achievement", 1L);

        var response = restTemplate.exchange(post(ACHIEVEMENT_BASE_URL).body(body), ACHIEVEMENT);

        var createdAchievement = response.getBody();

        assertThat(createdAchievement).isNotNull();

        assertThat(createdAchievement.getAchievementId()).isNotNull();
        assertThat(createdAchievement.getDescription()).isEqualTo("New Achievement");
    }

    void testListAchievements() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL).build(), ACHIEVEMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievements = response.getBody();

        assertThat(achievements).isNotNull();
        assertThat(achievements.size()).isEqualTo(1);

        assertThat(achievements.get(0).getAchievementId()).isEqualTo(1L);
        assertThat(achievements.get(0).getDescription()).isEqualTo("New Achievement");
    }

    void testFindAchievement() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/1").build(), ACHIEVEMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievement = response.getBody();

        assertThat(achievement).isNotNull();

        assertThat(achievement.getAchievementId()).isEqualTo(1L);
        assertThat(achievement.getDescription()).isEqualTo("New Achievement");
    }

    void testFindAchievementsByUserId() {
        var response = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/users/1").build(), ACHIEVEMENT_LIST);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var achievements = response.getBody();

        assertThat(achievements).isNotNull();
        assertThat(achievements.size()).isEqualTo(1);

        assertThat(achievements.get(0).getAchievementId()).isEqualTo(1L);
        assertThat(achievements.get(0).getDescription()).isEqualTo("New Achievement");
    }

    void testUpdateAchievement() {
        var body = new UpdateAchievementRequest("Updated Achievement", 1L);

        var response = restTemplate.exchange(put(ACHIEVEMENT_BASE_URL + "/1").body(body), ACHIEVEMENT);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var updatedAchievement = response.getBody();

        assertThat(updatedAchievement).isNotNull();

        assertThat(updatedAchievement.getAchievementId()).isEqualTo(1L);
        assertThat(updatedAchievement.getDescription()).isEqualTo("Updated Achievement");
    }

    void testDeleteAchievement() {
        var response = restTemplate.exchange(delete(ACHIEVEMENT_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedAchievement = restTemplate.exchange(get(ACHIEVEMENT_BASE_URL + "/1").build(), ACHIEVEMENT);

        assertThat(deletedAchievement.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateExercise() {
        var body = new CreateExerciseRequest("Exercise 1", "This is the first exercise", 1L, 1L);

        var response = restTemplate.exchange(post(EXERCISE_BASE_URL).body(body), EXERCISE);

        var createdExercise = response.getBody();

        assertThat(createdExercise).isNotNull();

        assertThat(createdExercise.getExerciseId()).isNotNull();
        assertThat(createdExercise.getName()).isEqualTo("Exercise 1");
        assertThat(createdExercise.getDescription()).isEqualTo("This is the first exercise");
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
    }

    void testFindExercise() {
        var response = restTemplate.exchange(get(EXERCISE_BASE_URL + "/1").build(), EXERCISE);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var exercise = response.getBody();

        assertThat(exercise).isNotNull();

        assertThat(exercise.getExerciseId()).isNotNull();
        assertThat(exercise.getName()).isEqualTo("Exercise 1");
        assertThat(exercise.getDescription()).isEqualTo("This is the first exercise");
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
    }

    void testDeleteExercise() {
        var response = restTemplate.exchange(delete(EXERCISE_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedExercise = restTemplate.exchange(get(EXERCISE_BASE_URL + "/1").build(), EXERCISE);

        assertThat(deletedExercise.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    void testCreateQuiz() {
        var body = new CreateQuizRequest("Quiz 1", "This is the first quiz", 1L);

        var response = restTemplate.exchange(post(QUIZ_BASE_URL).body(body), QUIZ);

        var createdQuiz = response.getBody();

        assertThat(createdQuiz).isNotNull();

        assertThat(createdQuiz.quizId()).isNotNull();
        assertThat(createdQuiz.name()).isEqualTo("Quiz 1");
        assertThat(createdQuiz.description()).isEqualTo("This is the first quiz");
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
    }

    void testFindQuiz() {
        var response = restTemplate.exchange(get(QUIZ_BASE_URL + "/1").build(), QUIZ);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var quiz = response.getBody();

        assertThat(quiz).isNotNull();

        assertThat(quiz.quizId()).isNotNull();
        assertThat(quiz.name()).isEqualTo("Quiz 1");
        assertThat(quiz.description()).isEqualTo("This is the first quiz");
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
    }

    void testDeleteQuiz() {
        var response = restTemplate.exchange(delete(QUIZ_BASE_URL + "/1").build(), VOID);

        assertThat(response.getStatusCode()).isEqualTo(OK);

        var deletedQuiz = restTemplate.exchange(get(QUIZ_BASE_URL + "/1").build(), QUIZ);

        assertThat(deletedQuiz.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    //@formatter:off
    static final String PROJECT_BASE_URL = "/api/projects";
    static final String TOPIC_BASE_URL = "/api/topics";
    static final String FORUM_BASE_URL = "/api/forums";
    static final String COURSE_BASE_URL = "/api/courses";
    static final String USER_BASE_URL = "/api/users";
    static final String ACHIEVEMENT_BASE_URL = "/api/achievements";
    static final String POST_BASE_URL = "/api/posts";
    static final String SECTION_BASE_URL = "/api/sections";
    static final String COMMENT_BASE_URL = "/api/comments";
    static final String EXERCISE_BASE_URL = "/api/exercises";
    static final String LESSON_BASE_URL = "/api/lessons";
    static final String QUIZ_BASE_URL = "/api/quizzes";

    static final ParameterizedTypeReference<Void> VOID = new ParameterizedTypeReference<>() {};

    static final ParameterizedTypeReference<ProjectInfoResponse> PROJECT_INFO = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<ProjectDetailsResponse> PROJECT_DETAILS = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<ProjectInfoResponse>> PROJECT_INFO_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<TopicInfoResponse> TOPIC = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<TopicInfoResponse>> TOPIC_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<CourseInfoResponse> COURSE = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<CourseInfoResponse>> COURSE_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<SectionInfoResponse> SECTION = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<SectionInfoResponse>> SECTION_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<LessonInfoResponse> LESSON = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<LessonInfoResponse>> LESSON_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Forum> FORUM = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Forum>> FORUM_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<User> USER = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<User>> USER_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Achievement> ACHIEVEMENT = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Achievement>> ACHIEVEMENT_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Post> POST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Post>> POST_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Comment> COMMENT = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Comment>> COMMENT_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Exercise> EXERCISE = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Exercise>> EXERCISE_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<QuizInfoResponse> QUIZ = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<QuizInfoResponse>> QUIZ_LIST = new ParameterizedTypeReference<>() {};
    //@formatter:on
}
