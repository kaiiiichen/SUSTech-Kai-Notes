public class Student {
    private static int count = 0;
    private int studentId;
    private int groupScore;
    private double personalScore;
    private int rate;
    private int groupSize;
    private char groupNumber;

    private boolean standard;

    static int[][] rates = {
            {2, 50, 90, 100, 125},
            {2, 90, 100, 125, 125},
            {2, 30, 50, 80, 100},
            {2, 10, 30, 40, 80},
            {2, 5, 10, 20, 40},
            {2, 0, 5, 20, 20},
            {3, 33, 75, 100, 122},
            {3, 75, 100, 122, 122},
            {3, 18, 33, 80, 100},
            {3, 5, 18, 15, 80},
            {3, 0, 5, 15, 15}
    };

    public Student(int groupScore, int rate, char groupNumber) {
        this.studentId = ++count;
        this.groupScore = groupScore;
        this.rate = rate;
        this.groupNumber = groupNumber;
        this.standard = false;
    }

    public Student(int groupScore, int rate, int groupSize) {
        this.studentId = ++count;
        this.groupSize = groupSize;
        this.groupScore = groupScore;
        this.rate = rate;
        this.standard = false;
    }


    public static double calculatePersonalScore(int groupScore, int rate, int groupSize) {
        int[] target = getTargetRow(rate, groupSize);
        if (target != null) {
            double scope = 1.0 * (target[4] - target[3]) / (target[2] - target[1]);
            return groupScore * ((target[3] + scope * (rate - target[1])) / 100);
        } else {
            return -1;
        }
    }

    private static int[] getTargetRow(int rate, int groupSize) {
        for (int[] target : rates) {
            if (target[0] == groupSize && rate >= target[1] && rate <= target[2])
                return target;
        }
        return null;
    }


    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Student.count = count;
    }

    public void updatePersonalScore() {
        this.personalScore = calculatePersonalScore(this.groupScore, this.rate, this.groupSize);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGroupScore() {
        return groupScore;
    }

    public void setGroupScore(int groupScore) {
        this.groupScore = groupScore;
    }

    public double getPersonalScore() {
        return personalScore;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public char getGroupNumber() {
        return groupNumber;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return String.format("%d group:%d personal:%d rate:%d", this.studentId, this.groupScore, Math.round(this.personalScore), this.rate);
    }
}
