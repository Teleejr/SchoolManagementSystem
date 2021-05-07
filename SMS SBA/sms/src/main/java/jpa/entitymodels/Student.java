package jpa.entitymodels;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

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
    @Id@Column(columnDefinition = "varchar(50)", name = "Email")@NonNull
    String sEmail;
    //Name column
    @Column(columnDefinition = "varchar(50)", name = "Name")@NonNull
    String sName;
    //Password column
    @Column(columnDefinition = "varchar(50)", name = "Password")@NonNull
    String sPass;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name="eId")
    @ToString.Exclude
    List<Course> sCourses;

//    public static Logger getLog() {
//        return log;
//    }
//
//    public String getSEmail() {
//        return sEmail;
//    }
//
//    public void setSEmail(String sEmail) {
//        this.sEmail = sEmail;
//    }
//
//    public String getSName() {
//        return sName;
//    }
//
//    public void setSName(String sName) {
//        this.sName = sName;
//    }
//
//    public String getSPass() {
//        return sPass;
//    }
//
//    public void setSPass(String sPass) {
//        this.sPass = sPass;
//    }
}
