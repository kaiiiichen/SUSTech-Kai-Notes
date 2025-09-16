import java.util.Arrays;

public class ScoreAnalyzer {
    public static void main(String[] args) {
        double[] scores = {-5, 89.5, 67.3, 22.4, 67.8, 90.2, 75.5, -1, 99};
        double[] validScores = filterInvalidScores(scores);
        System.out.println(Arrays.toString(validScores));
        System.out.println("max in valid scores = " + findMaxScore(validScores));
        System.out.println("min in valid scores = " + findMinScore(validScores));
        System.out.println("max in all scores = " + findMaxScore(scores));
        System.out.println("min in all scores = " + findMinScore(scores));
        System.out.println("Average = "+ calculateFinalAverage(scores));

        System.out.println(isValid(90));
        System.out.println(isValid(110));
        System.out.println(isValid(90,10,80));
        System.out.println(isValid(105,0,120));
    }

    public static boolean isValid(double score) {
        return score >= 0 && score <= 100;
    }

    public static boolean isValid(double score, int min, int max){
        return score >= min && score <= max;
    }

    public static double[] filterInvalidScores(double[] scores) {
        int validCount = 0;
        for (double score : scores) {
            if (isValid(score)) {
                validCount++;
            }
        }
        double[] valid = new double[validCount];
        int index = 0;
        for (double score : scores) {
            if (isValid(score)) {
                valid[index] = score;
                index++;
            }
        }
        return valid;
    }

    public static double findMaxScore(double[] scores) {
        double max = scores[0];
        for (double s : scores) {
            if (max < s) {
                max = s;
            }
        }
        return max;
    }

    public static double findMinScore(double[] scores) {
        double min = scores[0];
        for (double s : scores) {
            if (min > s) {
                min = s;
            }
        }
        return min;
    }

    public static double calculateFinalAverage(double[] scores) {
        double[] valid = filterInvalidScores(scores);
        double min = findMinScore(valid);
        double max = findMaxScore(valid);
        double sum = 0;
        for (double s : valid) {
            sum += s;
        }
        if (valid.length >= 3)
            return (sum - min - max) / (valid.length - 2);
        else
            return 0;
    }
}
