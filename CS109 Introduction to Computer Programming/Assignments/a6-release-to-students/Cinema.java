import java.util.List;

public interface Cinema {

    /**
     * This method is used to add a movie hall with {@code capacity} seats to the cinema.<br>
     * the id of the hall starts from {@code 1} and increases one by one.<br>
     * <ul>
     * <li>There are one parameter need to be passed.</li>
     * <li>There is no value need to be returned.</li>
     * </ul>
     *
     * @param capacity the capacity of the hall.<br>
     */
    public void addMovieHall(int capacity);

    /**
     * This method is used to get all movie halls with capacities in the cinema.<br>
     * <ul>
     * <li>There are no parameters needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @return a {@code List<String>} in ascending order of the number of movie hall,
     * which contains the information of each hall.<br>
     * the format of each String element in the list is
     * "(index of the hall)-(capacity of the hall)"<br>
     * Example: "1-17" in {"1-17", "2-13", "3-23"} 1 represents the hall number, 17 represents the capacity<br>
     */
    public List<String> getAllMovieHallsCapacity();

    /**
     * This method is used to add movie to the cinema.<br>
     * Each movie added successfully has its own id starting from 1 and increasing one by one.<br>
     * Caution:<br>
     * The movie hall needs {@code 10} minutes to prepare before playing a movie
     * and also needs {@code 10} minutes to clean after playing a movie,<br>
     * so there should be at least {@code 20} minutes between the two movies.<br>
     * (ending time of last movie + {@code 20}min) &lt;= (starting time of adding movie)<br>
     * And<br>
     * (ending time of adding movie + {@code 20}min) &lt;= (starting time of next movie)<br>
     * If the adding fails, you do not need to deal with this adding, just discard it.<br>
     * After successfully adding a movie, the number of tickets of this movie equals the {@code capacity} of the movie hall.<br>
     * <ul>
     * <li>There are six parameters need to be passed.</li>
     * <li>There is no value need to be returned.</li>
     * </ul>
     *
     * @param name       the name of the movie<br>
     * @param runtime    the movie duration counted in minutes<br>
     * @param hallNumber the id of the movie hall used to play films<br>
     * @param price      the price of the movie<br>
     * @param type       an Integer (0 or 1) representing the type of the movie<br>
     *                   -- 0 --&gt; OrdinaryMovie<br>
     *                   -- 1 --&gt; ThreeDMovie<br>
     * @param startTime  a {@code Time} type of the start time of the movie<br>
     */
    public void addMovie(String name, int runtime, int hallNumber, double price, int type, Time startTime);

    /**
     * This method is used to get all movies in the cinema.<br>
     * <ul>
     * <li>There are no parameters needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @return a {@code List<Movie>} in ascending order of the id<br>
     */
    public List<Movie> getAllMovies();

    /**
     * This method is used to get all movies in specific movie hall sorted by starting time of the movies.<br>
     * <ul>
     * <li>There is one parameter needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @param hallNumber the id of the movie hall<br>
     * @return a {@code List<Movie>} sorted by starting time in ascending order<br>
     */
    public List<Movie> getMoviesFromMovieHallOrderByStartTime(int hallNumber);

    /**
     * This method is used to reserve movie.<br>
     * <ul>
     * <li>There is two parameters needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @param movieId the id of the movie<br>
     * @param arg     an Integer of the reservation number or whether need 3D glasses.<br>
     *                <ul>
     *                <li>
     *                    the movie is ordinary movie, arg is the reservation number.<br>
     *                    If there are no enough tickets, it will buy all the tickets left,
     *                    remaining orders will not be considered.
     *                </li>
     *                <li>
     *                    the movie is 3D movie, arg (0 or 1) represents whether need 3D glasses,
     *                    and only reserve 1 ticket.
     *                    <ul>
     *                    <li>0 --&gt; do not need glasses</li>
     *                    <li>1 --&gt; need glasses</li>
     *                    </ul>
     *                </li>
     *                </ul>
     * @return the price needed to pay for reservation<br>
     *         If there are insufficient tickets of this movie, return 0.<br>
     */
    public double reserveMovie(int movieId, int arg);

    /**
     * This method is used to get the movie by its id.<br>
     * <ul>
     * <li>There is one parameter needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @param movieId the id of the movie<br>
     * @return a {@code Movie} type of specific movie of given id<br>
     */
    public Movie getMovieById(int movieId);

    /**
     * This method is used to get total income of specific movie.<br>
     * <ul>
     * <li>There is one parameter needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @param movieId the id of the movie<br>
     * @return the income of specific movie<br>
     */
    public double getOneMovieIncome(int movieId);

    /**
     * This method is used to get total income of the cinema.<br>
     * <ul>
     * <li>There is no parameter needed to be passed.</li>
     * <li>There is one value need to be returned.</li>
     * </ul>
     *
     * @return the total income of the current cinema<br>
     */
    public double getTotalIncome();

    /**
     * This method is used to get the movie which can be reserved.<br>
     * The same movie could be shown at different times.<br>
     * If the start time of a movie is after {@code currentTime} and rest of the ticket is larger than 0,
     * the movie will be reserved in the list.<br>
     * <ul>
     * <li>There is two parameters needed to be passed.</li>
     * <li>There is no value need to be returned.</li>
     * </ul>
     *
     * @param currentTime {@code Time} type representing current time<br>
     * @param name        the movie name<br>
     * @return a {@code List<Movie>} of available movies, sorted by its startTime in ascending order<br>
     */
    public List<Movie> getAvailableMoviesByName(Time currentTime, String name);
}
