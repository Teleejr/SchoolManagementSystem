package jpa.service;

import jpa.entitymodels.Student;
import org.apache.maven.surefire.shared.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @ParameterizedTest
    @CsvSource({"cjaulme9@bing.com, Cahra Jaulme, FnVklVgC6r6",
                "htaffley6@columbia.edu, Holmes Taffley, xowtOQ",
                "cstartin3@flickr.com, Clem Startin, XYHzJ1S",
                "qllorens2@howstuffworks.com, Quillan Llorens, W6rJuxd"})
    @DisplayName("Test true: getStudentByEmail")
    void getStudentByEmailTestTrue(ArgumentsAccessor args) {
        //Create student
        Student expected = new Student();
        //Get Student fields: name, email, password
        expected.setSEmail(args.getString(0));
        expected.setSName(args.getString(1));
        expected.setSPass(args.getString(2));

        //Create Student Service
        StudentService testStudentService = new StudentService();
        //Call method with the passed email
        Student actual = testStudentService.getStudentByEmail(args.getString(0));
        //Tests what is expected against what is given
        Assertions.assertEquals(expected.getSName(), actual.getSName(),"Expected: true");
        Assertions.assertEquals(expected.getSPass(), actual.getSPass(),"Expected: true");
    }//end getStudentByEmailTest

    @ParameterizedTest
    @CsvSource({"cjaulme9@bing.com, Cahra Jauleme, FnVklVgC6r6",
            "htaffley6@columbia.edu, Holmes Taffley, xowtOQl",
            "cstartin3@flicker.com, Clem Startin, XYHzJ1S",
            "qllorens2@howstuffworks.com, Qullan Llorens, W6rJuxd"})
    @DisplayName("Test false: getStudentByEmail")
    void getStudentByEmailTestFalse(ArgumentsAccessor args) throws NullPointerException {
        //Create student
        Student expected = new Student();
        //Get Student fields: name, email, password
        expected.setSEmail(args.getString(0));
        expected.setSName(args.getString(1));
        expected.setSPass(args.getString(2));

        //Create Student Service
        StudentService testStudentService = new StudentService();
        //Call method with the passed email
        Student actual = testStudentService.getStudentByEmail(args.getString(0));
        //Tests what is expected against what is given
        Assertions.assertEquals(expected.getSName(), actual.getSName(),"Expected: true");
        Assertions.assertEquals(expected.getSPass(), actual.getSPass(),"Expected: true");
    }//end getStudentByEmailTestF
}