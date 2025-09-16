
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LocalJudgeA6Test {

    StringBuilder builder;
    String ls = System.lineSeparator();
    String[] fieldsExpected;
    String[] methodsExpected;
    List<String> fields;
    List<String> methods;
    static Cinema cinema = new ConcreteCinema();

    @BeforeEach
    void BeforeEach() {
        builder = new StringBuilder();
    }

    @Test
    void testCinema_01_ClassInfo_Time() {
        int wrongCount = 0;
        try {
            Class<?> time = Class.forName("Time");
            fieldsExpected = new String[]{"private int Time.hour", "private int Time.minute"};
            methodsExpected = new String[]{"public java.lang.String Time.toString()"};
            fields = Arrays.stream(time.getDeclaredFields()).map(Field::toString).collect(Collectors.toCollection(ArrayList::new));
            methods = Arrays.stream(time.getDeclaredMethods()).map(Method::toString).collect(Collectors.toCollection(ArrayList::new));
            for (String s : fieldsExpected) {
                if (!fields.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
            for (String s : methodsExpected) {
                if (!methods.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }

        } catch (ClassNotFoundException e) {
            builder.append(e.toString());
            wrongCount++;
        }
        assertEquals(0, wrongCount, builder.toString());
    }

    @Test
    void testCinema_02_ClassInfo_Movie() {
        int wrongCount = 0;
        try {
            Class<?> movie = Class.forName("Movie");
            fieldsExpected = new String[]{"private int Movie.id", "private java.lang.String Movie.name", "private Time Movie.startTime",
                    "private int Movie.runtime", "private double Movie.price", "protected int Movie.ticketsLeft"};
            methodsExpected = new String[]{"public java.lang.String Movie.toString()", "public abstract double Movie.purchase(int)"};
            fields = Arrays.stream(movie.getDeclaredFields()).map(Field::toString).collect(Collectors.toCollection(ArrayList::new));
            methods = Arrays.stream(movie.getDeclaredMethods()).map(Method::toString).collect(Collectors.toCollection(ArrayList::new));
            for (String s : fieldsExpected) {
                if (!fields.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
            for (String s : methodsExpected) {
                if (!methods.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
        } catch (ClassNotFoundException e) {
            builder.append(e.toString());
            wrongCount++;
        }
        assertEquals(0, wrongCount, builder.toString());
    }

    @Test
    void testCinema_03_ClassInfo_MovieSubclasses() {
        int wrongCount = 0;
        try {
            Class<?> ordinaryMovie = Class.forName("OrdinaryMovie");
            methodsExpected = new String[]{"public java.lang.String OrdinaryMovie.toString()", "public double OrdinaryMovie.purchase(int)"};
            methods = Arrays.stream(ordinaryMovie.getDeclaredMethods()).map(Method::toString).collect(Collectors.toCollection(ArrayList::new));
            for (String s : methodsExpected) {
                if (!methods.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
        } catch (ClassNotFoundException e) {
            builder.append(e.toString());
            wrongCount++;
        }
        assertEquals(0, wrongCount, builder.toString());
        wrongCount = 0;
        try {
            Class<?> threeDMovie = Class.forName("ThreeDMovie");
            fieldsExpected = new String[]{"private final int ThreeDMovie.GLASS_PRICE"};
            methodsExpected = new String[]{"public java.lang.String ThreeDMovie.toString()", "public double ThreeDMovie.purchase(int)"};
            fields = Arrays.stream(threeDMovie.getDeclaredFields()).map(Field::toString).collect(Collectors.toCollection(ArrayList::new));
            methods = Arrays.stream(threeDMovie.getDeclaredMethods()).map(Method::toString).collect(Collectors.toCollection(ArrayList::new));
            for (String s : fieldsExpected) {
                if (!fields.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
            for (String s : methodsExpected) {
                if (!methods.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
        } catch (ClassNotFoundException e) {
            builder.append(e.toString());
            wrongCount++;
        }
        assertEquals(0, wrongCount, builder.toString());
    }


    @Test
    void testCinema_04_ClassInfo_ConcreteCinema() {
        int wrongCount = 0;
        try {
            Class<?> concreteCinema = Class.forName("ConcreteCinema");
            fieldsExpected = new String[]{"java.util.List ConcreteCinema.movies"};
            fields = Arrays.stream(concreteCinema.getDeclaredFields()).map(Field::toString).collect(Collectors.toCollection(ArrayList::new));
            for (String s : fieldsExpected) {
                if (!fields.contains(s)) {
                    wrongCount++;
                    builder.append(ls).append(s).append(" is missing!");
                }
            }
        } catch (ClassNotFoundException e) {
            builder.append(e.toString());
            wrongCount++;
        }
        assertEquals(0, wrongCount, builder.toString());
    }

    @Test
    void testCinema_05_time() {
        String expected = "12:05" + ls
                + "04:11";
        builder.append(new Time(12, 5).toString()).append(ls);
        builder.append(new Time(4, 11).toString());
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_06_addMovieHall() {
        String expected = "1-10" + ls +
                "2-15" + ls +
                "3-4" + ls;
        cinema.addMovieHall(10);
        cinema.addMovieHall(15);
        cinema.addMovieHall(4);
        cinema.getAllMovieHallsCapacity().forEach(s -> {
            builder.append(s).append(ls);
        });
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_07_addMovieWithoutConflict() {
        String expected = "id=1, name='Da Vinci Code', startTime:01:15, runtime=120, price=50.4, ticketsLeft=10 OrdinaryMovie" + ls +
                "id=2, name='She diao ying xiong zhuan', startTime:06:25, runtime=125, price=60.0, ticketsLeft=10 OrdinaryMovie" + ls +
                "id=3, name='Hong jian', startTime:16:15, runtime=125, price=58.5, ticketsLeft=4 ThreeDMovie" + ls;
        cinema.addMovie("Da Vinci Code", 120, 1, 50.4, 0, new Time(1, 15));
        cinema.addMovie("She diao ying xiong zhuan", 125, 1, 60, 0, new Time(6, 25));
        cinema.addMovie("Hong jian", 125, 3, 58.5, 1, new Time(16, 15));
        cinema.getAllMovies().forEach(s -> {
            builder.append(s).append(ls);
        });
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_08_addMovieWithConflict() {
        String expected = "id=1, name='Da Vinci Code', startTime:01:15, runtime=120, price=50.4, ticketsLeft=10 OrdinaryMovie" + ls +
                "id=2, name='She diao ying xiong zhuan', startTime:06:25, runtime=125, price=60.0, ticketsLeft=10 OrdinaryMovie" + ls +
                "id=3, name='Hong jian', startTime:16:15, runtime=125, price=58.5, ticketsLeft=4 ThreeDMovie" + ls +
                "id=4, name='Nian shou da zuo zhan', startTime:03:40, runtime=120, price=35.5, ticketsLeft=10 ThreeDMovie" + ls;
        cinema.addMovie("Nian shou da zuo zhan", 120, 1, 35.5, 1, new Time(3, 40));
        cinema.addMovie("Bu xiu", 180, 1, 60.5, 1, new Time(3, 35));
        cinema.addMovie("Da mo zu shi", 100, 1, 45, 1, new Time(5, 39));
        cinema.addMovie("Ling de jiao dian", 100, 1, 45, 1, new Time(5, 49));
        cinema.getAllMovies().forEach(s -> {
            builder.append(s).append(ls);
        });
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_09_movieHallOrderByStartTime() {
        String expected = "id=1, name='Da Vinci Code', startTime:01:15, runtime=120, price=50.4, ticketsLeft=10 OrdinaryMovie" + ls +
                "id=4, name='Nian shou da zuo zhan', startTime:03:40, runtime=120, price=35.5, ticketsLeft=10 ThreeDMovie" + ls +
                "id=2, name='She diao ying xiong zhuan', startTime:06:25, runtime=125, price=60.0, ticketsLeft=10 OrdinaryMovie" + ls;
        cinema.getMoviesFromMovieHallOrderByStartTime(1).forEach(s -> {
            builder.append(s.toString()).append(ls);
        });
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_10_reserveMovie() {
        String expected = "403" + ls +
                "100" + ls +
                "78" + ls +
                "58" + ls;
        builder.append((int) cinema.reserveMovie(1, 8)).append(ls);
        builder.append((int) cinema.reserveMovie(1, 5)).append(ls);
        builder.append((int) cinema.reserveMovie(3, 1)).append(ls);
        builder.append((int) cinema.reserveMovie(3, 0)).append(ls);
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_11_getMovieById() {
        String expected = "Movie 1 ticket Left: 0" + ls +
                "Movie 3 ticket Left: 2" + ls;
        builder.append("Movie 1 ticket Left: ").append(cinema.getMovieById(1).ticketsLeft).append(ls);
        builder.append("Movie 3 ticket Left: ").append(cinema.getMovieById(3).ticketsLeft).append(ls);
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_12_oneMovieIncome() {
        String expected = "Income of Movie 1: 504" + ls +
                "Income of Movie 3: 137" + ls;
        builder.append("Income of Movie 1: ").append((int) cinema.getOneMovieIncome(1)).append(ls);
        builder.append("Income of Movie 3: ").append((int) cinema.getOneMovieIncome(3)).append(ls);
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_13_totalIncome() {
        String expected = "total income:641";
        builder.append("total income:").append((int) cinema.getTotalIncome());
        assertEquals(expected, builder.toString());
    }

    @Test
    void testCinema_14_getAvailableMoviesByName() {
        String expected = "id=5, name='name5', startTime:11:35, runtime=110, price=60.5, ticketsLeft=4 ThreeDMovie" + ls +
                "id=6, name='name5', startTime:19:35, runtime=110, price=60.5, ticketsLeft=4 ThreeDMovie" + ls +
                "id=6, name='name5', startTime:19:35, runtime=110, price=60.5, ticketsLeft=4 ThreeDMovie";
        cinema.addMovie("name5", 110, 3, 60.5, 1, new Time(16, 35));
        cinema.addMovie("name5", 110, 3, 60.5, 1, new Time(11, 35));
        cinema.addMovie("name5", 110, 3, 60.5, 1, new Time(19, 35));
        cinema.getAvailableMoviesByName(new Time(11, 20), "name5").forEach(s -> {
            builder.append(s.toString()).append(ls);
        });
        cinema.getAvailableMoviesByName(new Time(16, 35), "name5").forEach(s -> {
            builder.append(s.toString());
        });
        assertEquals(expected.trim(), builder.toString().trim());
    }
}