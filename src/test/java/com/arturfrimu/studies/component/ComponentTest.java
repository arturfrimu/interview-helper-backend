package com.arturfrimu.studies.component;

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

    //@formatter:off
    static final String FORUM_BASE_URL = "/api/forums";

    static final ParameterizedTypeReference<Forum> FORUM = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<List<Forum>> FORUM_LIST = new ParameterizedTypeReference<>() {};
    static final ParameterizedTypeReference<Void> VOID = new ParameterizedTypeReference<>() {};
    //@formatter:on
}
