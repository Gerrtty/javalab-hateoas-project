package ru.itis.hateoas;

import org.junit.jupiter.api.Test;
import ru.itis.hateoas.service.TestService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TestServiceTest {

    @MockBean
    public TestService testService;

    @BeforeEach
    public void before() {
        when(testService.testMethod("kek")).thenReturn("lol");
    }

    @Test
    public void myTest() {
        System.out.println(testService);
        String kek = testService.testMethod("kek");
        System.out.println(kek);
    }

}
