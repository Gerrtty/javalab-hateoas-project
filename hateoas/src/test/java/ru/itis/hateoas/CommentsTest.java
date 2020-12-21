package ru.itis.hateoas;

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
import ru.itis.hateoas.model.Comment;
import ru.itis.hateoas.service.CommentsService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CommentsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentsService commentsService;

    @Test
    public void publishCommentTest() {
        try {
            when(commentsService.publish(getComment(true)))
                    .thenReturn(getComment(true));

            mockMvc.perform(post("/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(EntityModel.of(getComment(true))))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.text").value(getComment(true).getText()))
                    .andDo(document("publish_comment", responseFields(
                            fieldWithPath("text").description("Содержание комментария"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notPublishTest() {
        try {
            when(commentsService.publish(getComment(false)))
                    .thenThrow(new IllegalAccessException("User not confirmed"));

            mockMvc.perform(post("/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(EntityModel.of(getComment(false))))
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isForbidden());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Comment getComment(boolean userIsConfirmed) {
        return Comment.builder()
                .author(UserTestHelper.getUser(userIsConfirmed))
                .text("Test text")
                .build();
    }

}
