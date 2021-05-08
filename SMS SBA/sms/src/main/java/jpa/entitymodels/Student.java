package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
//import lombok.extern.log4j.Log4j;
//import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {
    //Email column also the primary key
    @Id@Column(columnDefinition = "varchar(50)", name = "Email", nullable = false)
    String sEmail;
    //Name column
    @Column(columnDefinition = "varchar(50)", name = "Name", nullable = false)
    String sName;
    //Password column
    @Column(columnDefinition = "varchar(50)", name = "Password", nullable = false)
    String sPass;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name="student_email")
    @ToString.Exclude
    List<Course> sCourses;

}
