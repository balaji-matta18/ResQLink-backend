package com.app.resqlink.service;

import com.app.resqlink.model.TestTable;
import com.app.resqlink.repository.TestTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTableService {
    private final TestTableRepository repository;

    public TestTableService(TestTableRepository repository) {
        this.repository = repository;
    }

    public List<TestTable> getAllData() {
        return repository.findAll();
    }
}
