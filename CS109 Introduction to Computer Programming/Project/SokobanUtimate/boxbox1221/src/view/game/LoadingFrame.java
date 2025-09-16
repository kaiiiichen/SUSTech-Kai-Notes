package view.game;

import model.MapMatrix;
import util.AdvancedFileUtil;
import util.FileUtil;
import view.FrameUtil;
import view.level.LevelFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LoadingFrame extends JFrame {
    private JButton continueBtn;
    private JButton newBtn;
    private GameFrame gameFrame;
    private LevelFrame levelFrame;
    private LoadingFailureFrame loadingFailureFrame;

    FileUtil fileUtil = new AdvancedFileUtil();

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void setLoadingFailureFrame(LoadingFailureFrame loadingFailureFrame) {
        this.loadingFailureFrame = loadingFailureFrame;
    }

    public void setLevelFrame(LevelFrame levelFrame) {
        this.levelFrame = levelFrame;
    }

    public LoadingFrame(int width, int height) {
        this.setTitle("Loading Frame");
        this.setLayout(null);
        this.setSize(width, height);

        JLabel Label1 = FrameUtil.createJLabel(this, new Point(55, 20), 250, 40, "You didn't finish this level last time.");
        JLabel Label2 = FrameUtil.createJLabel(this, new Point(20, 40), 320, 40, "Would you like to continue or start a new game?");

        continueBtn = FrameUtil.createButton(this, "Continue", new Point(110, 80), 100, 40);
        newBtn = FrameUtil.createButton(this, "Start New", new Point(110, 120), 100, 40);

        continueBtn.addActionListener(e -> {

            // 继续之前我们要检查存档有没有被破坏
//            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
//            String username = lines00.getFirst();
//            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
//            String level = lines11.getFirst();

//            List<String> lines = fileUtil.readFileToList("UserMap/" + username + "/" + "level" + level);
//            int steps = Integer.parseInt(lines.getFirst());
//            int time = Integer.parseInt(lines.get(1));
//            String status = lines.get(2);
//            int[][] userMapNow = new int[lines.size() - 3][];
//            for (int i = 3; i < lines.size(); i++) {
//                String[] pieces = lines.get(i).split(",");
//                userMapNow[i - 3] = new int[pieces.length];
//                for (int j = 0; j < pieces.length; j++) {
//                    userMapNow[i - 3][j] = Integer.parseInt(pieces[j]);
//                }
//            }

            List<String> lines00 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines00.getFirst();
            List<String> lines11 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines11.getFirst();

            // 我们要读取我们存档的user0001中的数据，把原初的mapMatrix传到我们这个frame里面，给下面做判断文件是否损坏使用
            List<String> lines0001 = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
            int[][] userMapNow0 = new int[lines0001.size() - 3][];
            for (int i = 3; i < lines0001.size(); i++) {
                String[] pieces = lines0001.get(i).split(",");
                userMapNow0[i - 3] = new int[pieces.length];
                for (int j = 0; j < pieces.length; j++) {
                    userMapNow0[i - 3][j] = Integer.parseInt(pieces[j]);
                }
            }
            MapMatrix mapMatrix = new MapMatrix(userMapNow0);
            // 把user0001中的矩阵传到了mapMatrix中，下面可以使用了！

            int n1 = 0;
            int n2 = 0;
            int n22 = 0;
            int n12 = 0;
            int n10 = 0;
            int n20 = 0;
            int n0 = 0;
            for (int[] ints : mapMatrix.getMatrix()) {
                for (int i : ints) {
                    if (i == 1) {
                        n1++;
                    } else if (i == 2) {
                        n2++;
                    } else if (i == 22) {
                        n22++;
                    } else if (i == 12) {
                        n12++;
                    } else if (i == 10) {
                        n10++;
                    } else if (i == 20) {
                        n20++;
                    } else if (i == 0) {
                        n0++;
                    }
                }
            }
            // 这边我们记录下了原初的各种类型的东西的数量，用于下面判断有没有出现数据篡改的破坏文件问题


            List<String> lines = fileUtil.readFileToList("UserMap/" + username + "/" + "level" + level);
            int steps = Integer.parseInt(lines.getFirst());
            int time = Integer.parseInt(lines.get(1));
            String status = lines.get(2);
            int[][] userMapNow = new int[lines.size() - 3][];
            for (int i = 3; i < lines.size(); i++) {
                String[] pieces = lines.get(i).split(",");
                userMapNow[i - 3] = new int[pieces.length];
                for (int j = 0; j < pieces.length; j++) {
                    userMapNow[i - 3][j] = Integer.parseInt(pieces[j]);
                }
            }

            if (!status.equals("processing")) {
                System.out.println("the status in the file is neither new nor processing");
                this.loadingFailureFrame.setVisible(true);
                this.setVisible(false);
            } else {

//                 这边我们想要每次按一个level的按钮都先读取了用户的存档，然后下面我们对存档是否被破坏进行初步检查
//                 如果是进行中的游戏，我们检查存档破坏没，然后加载
//                 如果是新的游戏，我们直接加载新的mapMatrix，不管他这个存档有没有被破坏，直接后面在写入一遍
//                 这边检查存档破坏有这样两个点：
//                 1.我们检查损坏的部份在于简单地检查有没有乱窜改我们的记录的mapMatrixNow，如果有窜改就有问题；
//                 2.我们检查没有cover的情况是：
//                   如果文档中显示游戏进度是new还是processing被恶意篡改了，比如本来在进行中的游戏，被篡改为了状态为new，
//                   那我们是不会有一个措施来把他的状态再调回processing的。
//                   我们这样做的原因是，就算我们发现了，现在的地图和初始的不一样，但状态显示的是new，
//                   我们也无法判断现在的地图是否有被篡改游戏状态的人所修改。
//                   所以我们最后的结论都只能说明文档被篡改了，但是篡改成什么样我们不知道。
//                   所以这种状态被篡改的文件破坏，我们还是对他进行重新new的操作。
//                   那这样其实和我们检测到是new的状态之后，直接读入新的mapMatrix进去，后面再写入、覆盖原文件，结果是一样的。
//                   所以我们直接把他给重新初始化一遍地图，这样操作就行了。


                // here, for status equaling processing, check whether there is a breaking part and gon on
                boolean broken = false;
                for (int[] value : userMapNow) {
                    for (int j = 0; j < userMapNow[0].length; j++) {
                        if (value[j] != 0 && value[j] != 1 && value[j] != 2
                                && value[j] != 10 && value[j] != 12 && value[j] != 20 && value[j] != 22) {
                            broken = true; // do not make change of the numbers into other numbers
                            break;
                        }
                    }
                }

                // 如果没有其他数字的异常，我们再多检查有没有修改到其他错误，下面检查的错误是有没有把hero搞没了
                // 如果已经检测到有异常数字，我们就跳过下面这段更细致的检测，直接到下面执行被破坏要执行的程序
                if (!broken) {
                    int nn1 = 0;
                    int nn2 = 0;
                    int nn22 = 0;
                    int nn12 = 0;
                    int nn10 = 0;
                    int nn20 = 0;
                    int nn0 = 0;
                    for (int[] ints : userMapNow) {
                        for (int i : ints) {
                            if (i == 1) {
                                nn1++;
                            } else if (i == 2) {
                                nn2++;
                            } else if (i == 22) {
                                nn22++;
                            } else if (i == 12) {
                                nn12++;
                            } else if (i == 10) {
                                nn10++;
                            } else if (i == 20) {
                                nn20++;
                            } else if (i == 0) {
                                nn0++;
                            }
                        }
                    }
                    if (nn1 != n1 || ((n0 + n2 + n10 + n20 + n12 + n22) != (nn0 + nn2 + nn10 + nn20 + nn12 + nn22))) {
                        broken = true; // broken
                        System.out.println("broken problem 1");
                    }
                    if (n22 == 0 && n2 == 0) {
                        broken = true; // broke because no left target place now
                        System.out.println("broken problem 2");
                    }
                }

                // 如果破坏了，重新刷新存档，并且启动loadingFailureFrame
                if (broken) {
                    System.out.println("this is a broken file with status processing, we will rewrite it");
                    List<String> linesnow = new ArrayList<>();
                    linesnow.add(Integer.toString(0));
                    linesnow.add(Integer.toString(0));
                    linesnow.add("new");
                    StringBuilder sb = new StringBuilder();
                    for (int[] ints : mapMatrix.getMatrix()) {
                        sb.setLength(0);
                        for (int anInt : ints) {
                            sb.append(anInt).append(",");
                        }
                        sb.setLength(sb.length() - 1);
                        linesnow.add(sb.toString());
                    }
                    fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, linesnow);

                    this.loadingFailureFrame.setVisible(true);
                    this.setVisible(false);
                } else {
                    // not broken, just loading the file
                    // we load the game frame now!
                    System.out.println("the file is complete, we just load it");
                    MapMatrix mapMatrixNow = new MapMatrix(userMapNow);
                    GameFrame gameFrame = new GameFrame(550, 620, mapMatrixNow, 1);
                    gameFrame.setLevelFrame(this.levelFrame);
                    this.setVisible(false);
                    gameFrame.setVisible(true);
                }
            }


        });

        newBtn.addActionListener(e -> {
            // 当选择重启一个新的游戏时，我们这边读取backup user：user0001中的原初数据，然后把其写入当前玩家的游戏记录文档
            // 不会影响我们的排行榜，排行榜是会再使用另一套系统记录


            List<String> lines0 = fileUtil.readFileToList("CurrentPlayer/CurrentPlayer.txt");
            String username = lines0.getFirst();
            List<String> lines1 = fileUtil.readFileToList("CurrentPlayer/CurrentLevel.txt");
            String level = lines1.getFirst();

            List<String> lines = fileUtil.readFileToList("UserMap/" + "user0001" + "/" + "level" + level);
//            int steps = Integer.parseInt(lines.getFirst());
//            int time = Integer.parseInt(lines.get(1));
//            String status = lines.get(2);
//            int[][] userMapNow = new int[lines.size() - 3][];
//            for (int i = 3; i < lines.size(); i++) {
//                String[] pieces = lines.get(i).split(",");
//                userMapNow[i - 3] = new int[pieces.length];
//                for (int j = 0; j < pieces.length; j++) {
//                    userMapNow[i - 3][j] = Integer.parseInt(pieces[j]);
//                }
//            }
            fileUtil.writeFileFromList("UserMap/" + username + "/level" + level, lines);
            // 到这边我们重新set up写完了当前玩家的这个存档
            // 我们需要level frame每次都读取一遍玩家的当前存档，看看是不是新存档，还是正在进行中的存档

            levelFrame.setVisible(true);
            this.setVisible(false);
        });

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
