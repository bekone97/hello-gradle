package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDef(name = "postgreSqlEnumType", typeClass = PostgreSqlEnumType.class)
@SQLDelete(sql="UPDATE employee SET deleted = true WHERE employee_id=?")
@FilterDef(name = "deletedEmployeeFilter",parameters = @ParamDef(name = "isDeleted",type = "boolean"))
@Filter(name = "deletedEmployeeFilter",condition = "deleted = :isDeleted")
@Entity(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "job_tittle")
    private String jobTittle;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "gender_type")
    @Type(type = "postgreSqlEnumType")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private boolean deleted ;
}
