package ru.itis.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.repository.BlogUsersRepository;
import ru.itis.hateoas.service.SignInServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class RegistrationTest {

    @MockBean
    public SignInServiceImpl signInService;

    @MockBean
    public BlogUsersRepository repository;

    @BeforeEach
    public void saveU() {
        when(repository.save(UserTestHelper.getUser(true)))
                .thenReturn(UserTestHelper.getUser(true));
    }

    @Test
    public void testReg() {

//        BlogUser user = repository.save(UserTestHelper.getUser(true));
//        System.out.println(user);

        BlogUser user = signInService.signUp(EntityModel.of(UserTestHelper.getUser(true)));
        System.out.println(user);
    }

}
