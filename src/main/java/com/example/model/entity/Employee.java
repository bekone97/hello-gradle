package com.example.model.entity;

import com.example.listener.AuditEmployeeListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDef(name = "postgreSqlEnumType", typeClass = PostgreSqlEnumType.class)
@SQLDelete(sql="UPDATE employee SET deleted = true WHERE employee_id=?")
@Where(clause = "deleted=false")
@Entity(name = "employee")
@EntityListeners({AuditingEntityListener.class, AuditEmployeeListener.class})
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

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Version
    private long version;
}
