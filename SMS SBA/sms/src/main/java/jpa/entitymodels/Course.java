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

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(columnDefinition = "INT(11) UNSIGNED")
    int cId;
    String cName;
    String cInstructorName;
}
