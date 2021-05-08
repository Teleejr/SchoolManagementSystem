package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Course {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED", name = "ID", nullable = false)
    Integer cId;
    @Column(columnDefinition = "varchar(50)", name = "Name", nullable = false)
    String cName;
    @Column(columnDefinition = "varchar(50)", name = "Instructor", nullable = false)
    String cInstructorName;
}
