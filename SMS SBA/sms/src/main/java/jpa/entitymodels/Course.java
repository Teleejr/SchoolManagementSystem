package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Course {

    //Primary Key: ID column
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED", name = "ID", nullable = false)
    Integer cId;
    //Name column
    @Column(columnDefinition = "varchar(50)", name = "Name", nullable = false)
    String cName;
    //Instructor Name column
    @Column(columnDefinition = "varchar(50)", name = "Instructor", nullable = false)
    String cInstructorName;
}
