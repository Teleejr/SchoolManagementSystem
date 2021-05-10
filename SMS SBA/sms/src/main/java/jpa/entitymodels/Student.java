package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {
    @Id@Column(columnDefinition = "varchar(50)", name = "Email")@NonNull
    String sEmail;
    @Column(columnDefinition = "varchar(50)", name = "Name")@NonNull
    String sName;
    @Column(columnDefinition = "varchar(50)", name = "Password")@NonNull
    String sPass;
    List<Course> sCourses;

}
