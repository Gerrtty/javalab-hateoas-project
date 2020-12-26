package ru.itis.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.model.TestModel;
import ru.itis.hateoas.repository.TestRepo;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepo testRepo;

    @Override
    public String testMethod(String test) {
        return testRepo.save(TestModel.builder().text(test).build()).getText();
    }

}
