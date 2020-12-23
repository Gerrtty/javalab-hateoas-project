package ru.itis.hateoas.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String testMethod(String test) {
        return "kek!!!";
    }

}
