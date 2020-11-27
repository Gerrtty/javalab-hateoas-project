package ru.itis.hateoas;

import ru.itis.hateoas.model.BlogUser;

public class UserTestHelper {

    public static BlogUser getUser(boolean userIsConfirmed) {
        return BlogUser.builder()
                .login("TestLogin")
                .password("TestPass")
                .firstName("TestFirstName")
                .secondName("TestSecondName")
                .isConfirmed(userIsConfirmed)
                .build();
    }

}
