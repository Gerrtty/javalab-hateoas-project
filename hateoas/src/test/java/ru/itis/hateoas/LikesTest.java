package ru.itis.hateoas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import ru.itis.hateoas.model.PostLike;
import ru.itis.hateoas.repository.LikesRepository;
import ru.itis.hateoas.service.LikesService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.itis.hateoas.PostTestHelper.getPost;
import static ru.itis.hateoas.UserTestHelper.getUser;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class LikesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LikesService likesService;

    @MockBean
    private LikesRepository likesRepository;

    @BeforeEach
    public void setUp() {
        PostLike postLike = getPostLike();
        when(likesRepository.save(any(PostLike.class))).thenReturn(postLike);
        doNothing().when(likesRepository).delete(any(PostLike.class));
    }

    @Test
    public void set() {
        System.out.println("Set test");
        EntityModel<PostLike> postLikeEntityModel = EntityModel.of(getPostLike());
        System.out.println(postLikeEntityModel.getContent());
        System.out.println(likesService.set(postLikeEntityModel.getContent()));
    }

    @Test
    public void testLikes() {

        try {

            mockMvc.perform(post("/postLikes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(EntityModel.of(getPostLike())))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.author").value(getPostLike().getAuthor()))
                    .andExpect(jsonPath("$.post").value(getPostLike().getPost()))
                    .andDo(document("set_like", responseFields(
                            fieldWithPath("author").description("Логин автора"),
                            fieldWithPath("post").description("Пост"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PostLike getPostLike() {
        return PostLike.builder()
                .post(getPost(true))
                .author(getUser(true))
                .build();
    }

}
