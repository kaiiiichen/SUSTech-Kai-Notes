package view.signup;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String[][] userRecord;
    private int n; // n is the number of current users whose info has been recorded.

    public int getN() {
        return n;
    }

    public String[][] getUserRecord() {
        return userRecord;
    }

    public void convertToUserRecord(List<String> readlines) {
        this.userRecord = new String[readlines.size() + 100][2];
        this.n = readlines.size();

        for (int i = 0; i < readlines.size(); i++) {
            String[] pieces = readlines.get(i).split(" ");
            this.userRecord[i][0] = pieces[0];
            this.userRecord[i][1] = pieces[1];
        }
    }

    public void signUp(String username, String password) {
        this.userRecord[n][0] = username;
        this.userRecord[n][1] = password;
        n++;
    }

    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (String[] strings : this.userRecord) {
            counter++;
            if (counter > n) {
                break;
            }
            sb.setLength(0);
            for (String aChar : strings) {
                sb.append(aChar).append(" ");
            }
            sb.setLength(sb.length() - 1);
            lines.add(sb.toString());
        }
        return lines;
    }
}
