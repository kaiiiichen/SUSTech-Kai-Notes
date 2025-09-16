import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A4Q3LocalTest {

    @BeforeEach
    public void resetId(){
        try {
            Field fieldCount= Student.class.getDeclaredField("count");
            fieldCount.setAccessible(true);
            fieldCount.set(null, 0);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @Test

    public void TestCalculatePersonalScore() {
        assertEquals(28, Math.round(Student.calculatePersonalScore(70, 10, 3)));
        assertEquals(58, Math.round(Student.calculatePersonalScore(70, 20, 3)));
        assertEquals(67, Math.round(Student.calculatePersonalScore(70, 30, 3)));
        assertEquals(73, Math.round(Student.calculatePersonalScore(70, 40, 3)));
        assertEquals(76, Math.round(Student.calculatePersonalScore(70, 50, 3)));
        assertEquals(85, Math.round(Student.calculatePersonalScore(70, 100, 3)));
        assertEquals(32, Math.round(Student.calculatePersonalScore(80, 10, 2)));
        assertEquals(48, Math.round(Student.calculatePersonalScore(80, 20, 2)));
        assertEquals(64, Math.round(Student.calculatePersonalScore(80, 30, 2)));
        assertEquals(72, Math.round(Student.calculatePersonalScore(80, 40, 2)));
        assertEquals(80, Math.round(Student.calculatePersonalScore(80, 50, 2)));
        assertEquals(85, Math.round(Student.calculatePersonalScore(80, 60, 2)));
        assertEquals(90, Math.round(Student.calculatePersonalScore(80, 70, 2)));
        assertEquals(100, Math.round(Student.calculatePersonalScore(80, 100, 2)));
    }


    @Test
    public void TestStudentPersonalScore() {
        Student s1 = new Student(90, 25, 2);
        Student s2 = new Student(90, 45, 2);
        Student s3 = new Student(90, 65, 2);
        Student s4 = new Student(90, 80, 2);
        Student s5 = new Student(90, 30, 3);
        Student s6 = new Student(90, 60, 3);

        s1.updatePersonalScore();
        s2.updatePersonalScore();
        s3.updatePersonalScore();
        s4.updatePersonalScore();
        s5.updatePersonalScore();
        s6.updatePersonalScore();

        assertEquals("1 group:90 personal:63 rate:25", s1.toString());
        assertEquals("2 group:90 personal:86 rate:45", s2.toString());
        assertEquals("3 group:90 personal:98 rate:65", s3.toString());
        assertEquals("4 group:90 personal:107 rate:80", s4.toString());
        assertEquals("5 group:90 personal:86 rate:30", s5.toString());
        assertEquals("6 group:90 personal:103 rate:60", s6.toString());
    }

    @Test
    public void TestSystemGenerateGroupSize() {
        Student[] students = new Student[10];
        students[0] = new Student(90, 10, 'D');
        students[1] = new Student(90, 30, 'D');
        students[2] = new Student(90, 60, 'A');
        students[3] = new Student(90, 90, 'D');
        students[4] = new Student(90, 0, 'A');
        students[5] = new Student(90, 100, 'C');
        students[6] = new Student(90, 80, 'C');
        students[7] = new Student(90, 70, 'C');
        students[8] = new Student(90, 30, 'B');
        students[9] = new Student(90, 20, 'B');
        GradeSystem.generateStudentGroupSize(students);
        assertEquals(3, students[0].getGroupSize());
        assertEquals(3, students[1].getGroupSize());
        assertEquals(2, students[2].getGroupSize());
        assertEquals(3, students[3].getGroupSize());
        assertEquals(2, students[4].getGroupSize());
        assertEquals(3, students[5].getGroupSize());
        assertEquals(3, students[6].getGroupSize());
        assertEquals(3, students[7].getGroupSize());
        assertEquals(2, students[8].getGroupSize());
        assertEquals(2, students[9].getGroupSize());
    }

    @Test
    public void TestSystemStandardizedScores() {
        Student[] students = new Student[10];
        students[0] = new Student(80, 50, 'H');
        students[1] = new Student(75, 50, 'D');
        students[2] = new Student(99, 25, 'C');
        students[3] = new Student(30, 34, 'Z');
        students[4] = new Student(20, 33, 'Z');
        students[5] = new Student(99, 25, 'C');
        students[6] = new Student(10, 33, 'Z');
        students[7] = new Student(99, 50, 'C');
        students[8] = new Student(76, 50, 'D');
        students[9] = new Student(80, 50, 'H');
        GradeSystem.standardizedScores(students);
        assertEquals("1 group:80 personal:0 rate:50", students[0].toString());
        assertEquals("2 group:60 personal:0 rate:50", students[1].toString());
        assertEquals("3 group:99 personal:0 rate:25", students[2].toString());
        assertEquals("4 group:60 personal:0 rate:34", students[3].toString());
        assertEquals("5 group:60 personal:0 rate:33", students[4].toString());
        assertEquals("6 group:99 personal:0 rate:25", students[5].toString());
        assertEquals("7 group:60 personal:0 rate:33", students[6].toString());
        assertEquals("8 group:99 personal:0 rate:50", students[7].toString());
        assertEquals("9 group:60 personal:0 rate:50", students[8].toString());
        assertEquals("10 group:80 personal:0 rate:50", students[9].toString());
    }

    @Test
    public void TestSystemGeneratePersonalScore() {
        Student[] students = new Student[5];
        students[0] = new Student(80, 35, 3);
        students[1] = new Student(80, 35, 3);
        students[2] = new Student(80, 30, 3);
        students[3] = new Student(80, 30, 2);
        students[4] = new Student(80, 70, 2);
        GradeSystem.generatePersonalScore(students);

        assertEquals("1 group:80 personal:81 rate:35", students[0].toString());
        assertEquals("2 group:80 personal:81 rate:35", students[1].toString());
        assertEquals("3 group:80 personal:77 rate:30", students[2].toString());
        assertEquals("4 group:80 personal:64 rate:30", students[3].toString());
        assertEquals("5 group:80 personal:90 rate:70", students[4].toString());
    }

    @Test
    public void TestSystemAllMethods() {
        Student[] students = new Student[10];
        students[0] = new Student(90, 10, 'D');
        students[1] = new Student(90, 30, 'D');
        students[2] = new Student(90, 60, 'A');
        students[3] = new Student(90, 60, 'D');
        students[4] = new Student(95, 40, 'A');
        students[5] = new Student(90, 30, 'C');
        students[6] = new Student(89, 40, 'C');
        students[7] = new Student(90, 30, 'C');
        students[8] = new Student(90, 50, 'B');
        students[9] = new Student(90, 50, 'B');
        GradeSystem.generateStudentGroupSize(students);
        GradeSystem.standardizedScores(students);
        GradeSystem.generatePersonalScore(students);

        assertEquals("1 group:90 personal:36 rate:10", students[0].toString());
        assertEquals("2 group:90 personal:86 rate:30", students[1].toString());
        assertEquals("3 group:60 personal:64 rate:60", students[2].toString());
        assertEquals("4 group:90 personal:103 rate:60", students[3].toString());
        assertEquals("5 group:60 personal:54 rate:40", students[4].toString());
        assertEquals("6 group:60 personal:58 rate:30", students[5].toString());
        assertEquals("7 group:60 personal:62 rate:40", students[6].toString());
        assertEquals("8 group:60 personal:58 rate:30", students[7].toString());
        assertEquals("9 group:90 personal:90 rate:50", students[8].toString());
        assertEquals("10 group:90 personal:90 rate:50", students[9].toString());
    }
}
