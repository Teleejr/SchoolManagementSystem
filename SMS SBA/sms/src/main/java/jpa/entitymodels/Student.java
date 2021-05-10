package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import javax.persistence.*;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j
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
    //Join column for Student Courses table
    @OneToMany(orphanRemoval = true) @JoinColumn(name="student_email")
    @ToString.Exclude
    List<Course> sCourses;

    public Student(String sName, String sEmail, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }

    @Override
    public String toString() {
//        System.out.printf("Name %-20s | Email: %-25s | Password: %-15s", sName, sEmail, sPass);
        return "Name: " + sName + ", " + "Email: " + sEmail + ", " + "Password: " + sPass + "\n";
    }
}
