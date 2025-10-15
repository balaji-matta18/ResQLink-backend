package com.app.resqlink.controller;

import com.app.resqlink.model.TestTable;
import com.app.resqlink.service.TestTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestTableController {

    private final TestTableService service;

    public TestTableController(TestTableService service) {
        this.service = service;
    }

    @GetMapping
    public List<TestTable> getAllTestData() {
        return service.getAllData();
    }
}
