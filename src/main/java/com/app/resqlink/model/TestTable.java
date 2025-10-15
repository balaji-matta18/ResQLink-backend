package com.app.resqlink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
