package com.app.resqlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.resqlink.model.TestTable;

@Repository
public interface TestTableRepository extends JpaRepository<TestTable, Long> {
}
