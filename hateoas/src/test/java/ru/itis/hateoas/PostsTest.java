package ru.itis.hateoas;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.itis.hateoas.PostTestHelper.getPost;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.repository.PostsRepository;
import ru.itis.hateoas.service.PostsService;
import ru.itis.hateoas.service.PublishService;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PostsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublishService<Post> postPublishService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostsRepository postsRepository;

    @MockBean
    private PostsService postsService;

    @Test
    public void publishTest() {
        try {
            when(postsService.publish(getPost(true)))
                    .thenReturn(getPost(true));

            mockMvc.perform(post("/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(EntityModel.of(getPost(true))))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value(getPost(true).getTitle()))
                    .andExpect(jsonPath("$.body").value(getPost(true).getBody()))
                    .andDo(document("publish_post", responseFields(
                            fieldWithPath("title").description("Название поста"),
                            fieldWithPath("body").description("Содержание поста"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notPublishTest() {
        try {
            when(postPublishService.save(getPost(false), postsRepository))
                    .thenThrow(new IllegalAccessException("User not confirmed"));

            mockMvc.perform(post("/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(EntityModel.of(getPost(false))))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isForbidden());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
