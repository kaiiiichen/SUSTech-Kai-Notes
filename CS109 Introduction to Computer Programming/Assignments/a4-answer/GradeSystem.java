import java.util.*;

public class GradeSystem {


    public static void generateStudentGroupSize(Student[] students) {
//        Map<Character, Integer> map = new HashMap<>();
//        for (Student s : students) {
//            if (!map.containsKey(s.getGroupNumber())) {
//                map.put(s.getGroupNumber(), 1);
//            } else {
//                int count = map.get(s.getGroupNumber());
//                map.replace(s.getGroupNumber(), count, count + 1);
//            }
//        }
//        for (Student s: students){
//            s.setGroupSize(map.get(s.getGroupNumber()));
//            System.out.println(s.getGroupSize());
//        }
        int[] groups = new int[26];
        for (Student s : students) {
            groups[s.getGroupNumber() - 'A']++;
        }
        for (Student s : students) {
            s.setGroupSize(groups[s.getGroupNumber() - 'A']);
        }
    }

    public static void standardizedScores(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            if (!students[i].isStandard()) {
                Student[] sameGroup = new Student[3];
                int index = 0;
                students[i].setStandard(true);
                sameGroup[index++] = students[i];
                for (int j = i + 1; j < students.length; j++) {
                    if (students[j].getGroupNumber() == students[i].getGroupNumber()) {
                        sameGroup[index++] = students[j];
                        students[j].setStandard(true);
                    }
                }
                if (index == 2) {
                    if (sameGroup[0].getGroupScore() != sameGroup[1].getGroupScore()) {
                        sameGroup[0].setGroupScore(60);
                        sameGroup[1].setGroupScore(60);
                    }
                }
                if (index == 3) {
                    if (sameGroup[0].getGroupScore() != sameGroup[1].getGroupScore() || sameGroup[1].getGroupScore() != sameGroup[2].getGroupScore()) {
                        sameGroup[0].setGroupScore(60);
                        sameGroup[1].setGroupScore(60);
                        sameGroup[2].setGroupScore(60);
                    }
                }
            }
        }
    }

    public static void generatePersonalScore(Student[] students) {
        for (Student s : students) {
            s.updatePersonalScore();
        }
    }
}
