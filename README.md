# Android Unidirectional VIPER
The repository contains source code to start an Android application in VIPER clean architecture. The architecture is modified from the original Uncle Bob's clean architecture. The source code contains different layers of VIPER architecture which is demonstrated through the movie app. This is the Android version of clean swift explained in [this blog](https://rubygarage.org/blog/clean-swift-pros-and-cons).

# Architecture Flow
Here, the architecture flow is unidirectional i.e.
`View -> Interactor -> Presenter -> View`

Each layer of VIPER architecture and data flow are described below-

## **View**
The view starts and ends the VIPER cycle. Here `View` is an interface which contains all data methods to show data in the view.
The aim of view is to request data from interactors and show it to UI. It gets data from presenter but cannot send anything to the presenter. It has unidirectional interaction from Interactor and Presenter both.

```java
public interface MoviesView extends BaseView {
    void showMovieList(List<Movie> movies);
}
 ```
 
In Android, Activity or Fragment implements the View. Below is the example of `MovieListActivity`-

```java
public class MovieListActivity extends BaseActivity implements MoviesView, OnMovieItemClickedListener {

    public MoviesRouter router;
    public GetPopularMoviesInteractor interactor;
    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        setup();
        loadMovies();
    }

    private void setup() {
        MovieConfigurator.shared.configure(this);
    }

    private void loadMovies() {
        interactor.fetchPopularMovies();
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        // Populate movie list here
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtils.showLongToast(context, errorMessage);
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        router.goToMovieDetails(movie.getId());
    }
```

The activity contains an instance of router and interactor. Also, this is the place where we will configure everything using configurator.

## Interactor-
Interactors are also called Use Cases. This is the place to write core business logic of application which can be tested independently without the dependency on platform/framework specific things i.e UI, Database. The job of an interactor is to get query data from view and send the response data to the presenter.

```java
public class GetPopularMoviesInteractor {

    private final MovieRepository movieRepository;
    public MoviesPresenter presenter;

    public GetPopularMoviesInteractor(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void fetchPopularMovies() {
        Observable<List<MovieEntity>> popularMovies = movieRepository.getPopularMovies();
        presenter.presentMovies(popularMovies);
    }
}
```
Here you can see that interactor is containing instance of `MoviesPresenter`. The description of `MovieRepository` is explained later in this document.

## Presenter-
The Presenter is responsible for presentation logic. It decides how data will be presented to the user. The Presenter organizes the response sent by the Interactor into a suitable format to show in UI.

```java
public class MoviesPresenterImpl extends BasePresenter implements MoviesPresenter {
    public MoviesView view;
    private MovieEntityMovieMapper movieEntityMovieMapper = new MovieEntityMovieMapper();

    @Override
    public void presentMovies(Observable<List<MovieEntity>> popularMovies) {
        addDisposable(popularMovies.map(results -> movieEntityMovieMapper.mapFrom(results))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        view::showMovieList,
                        throwable -> view.showError(throwable.getMessage())));
    }
}
```

## Entity-
Entities are enterprise-wide business rules that encapsulate the most general business rules and also contain Data Transfer Objects (DTOs). When external changes, these rules are the least likely to change.

They are not only models that do not move, but they are also rules that make the hearth of your business. In a way, Entities define clearly the intent of your business. For example, an app about booking a movie(a movie ticket booking app) should have an entity called `Movie` and another entity called `Ticket`. Because the core of this app is "user books a movie ticket".

```java
@Data
@Accessors(chain = true)
public class MovieEntity {
    private String releaseDate;
    private String overview;
    private boolean adult;
    private String backdropPath;
    private List<Integer> genreIds;
    private String originalTitle;
    private String originalLanguage;
    private String posterPath;
    private double popularity;
    private String title;
    private double voteAverage;
    private boolean video;
    private int id;
    private int voteCount;
}
```

## Router-
The router handles the navigation between screens. A person can tap on one of the movies from movie list and navigates to the details page of the movie.

```java
public class MoviesRouterImpl implements MoviesRouter {

    private Activity activity;
    
    public MoviesRouterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void goToMovieDetails(int movieId) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(Constants.IntentExtras.MOVIE_ID, movieId);
        activity.startActivity(intent);
    }
}
```


# Configurator-
Configurator is a class that wires everything. It initializes all layers of VIPER.
```java
public class MovieConfigurator implements BaseConfigurator<MovieListActivity> {
    public static MovieConfigurator shared = new MovieConfigurator();

    @Override
    public void configure(MovieListActivity view) {
        GetPopularMoviesInteractor interactor = new GetPopularMoviesInteractor(new MovieRepository(new    RemoteMovieDataStore(), new LocalMovieDataStore()));
        MoviesRouter router = new MoviesRouterImpl(view);
        MoviesPresenter presenter = new MoviesPresenterImpl();
        ((MoviesPresenterImpl) presenter).view = view;
        interactor.presenter = presenter;
        view.interactor = interactor;
        view.router = router;
    }
}
```

# Data Layer-
We have used the repository to provide data for each feature. `MovieRepository` provides all movie related data. It contains data stores which provide data from REST API or database.

```java
public class MovieRepository {
    private RemoteMovieDataStore remoteMovieDataStore;

    private LocalMovieDataStore localMovieDataStore;

    @Inject
    public MovieRepository(RemoteMovieDataStore remoteMovieDataStore, LocalMovieDataStore localMovieDataStore) {
        this.remoteMovieDataStore = remoteMovieDataStore;
        this.localMovieDataStore = localMovieDataStore;
    }

    public Observable<List<MovieEntity>> getPopularMovies() {
        return remoteMovieDataStore.getPopularMovies();
    }

    public Observable<Optional<MovieEntity>> getMovieDetails(int movieId) {
        return remoteMovieDataStore.getMovieById(movieId);
    }
}
```
Here you can checks if data is present in local storage or when to fetch data from remote data store. Interactor doesn't know where data is coming.


## Data Store
Generic data store for movies-
```java
public interface MoviesDataStore {
    Observable<Optional<MovieEntity>> getMovieById(int movieId);

    Observable<List<MovieEntity>> getPopularMovies();

    Observable<List<MovieEntity>> search(String query);
}
```

Remote data store which implements `MovieDataStore` and fetches movies by making REST calls.

```java
public class RemoteMovieDataStore implements MoviesDataStore {
    @Inject
    ApiCallInterface apiCallInterface;
    private MovieDataEntityMapper movieDataEntityMapper = new MovieDataEntityMapper();

    public RemoteMovieDataStore() {
        ViperApplication.getApp().getMainComponent().inject(this);
    }

    @Override
    public Observable<Optional<MovieEntity>> getMovieById(int movieId) {
        // implements the method
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> getPopularMovies() {
        return apiCallInterface.getPopularMovies().map(results -> movieDataEntityMapper.mapFrom(results.getMovies()));
    }

    @Override
    public Observable<List<MovieEntity>> search(String query) {
        // implements the method
        return null;
    }
}
```

Local data store fetches movies data from local storage which can be Room database.

```java
public class LocalMovieDataStore implements MoviesDataStore {

    // Fetch data from database 
    
    @Override
    public Observable<Optional<MovieEntity>> getMovieById(int movieId) {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> getPopularMovies() {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> search(String query) {
        return null;
    }
}
```

## Mappers
Mappers are used to convert entity of one layer to another layer.
For example, REST API is returning list of MovieData objects which will be modified in domain layer to perform some operations on it. Again it will be modified to show in the UI.
Following is the one mapper class which converts `MovieEntity` to `Movie`-

```java
public class MovieEntityMovieMapper implements Mapper<MovieEntity, Movie> {
    private final static String POSTER_BASE_URL = "https://image.xyz.org/abc/123";

    @Override
    public Movie mapFrom(MovieEntity from) {
        Movie movie = new Movie();
        movie.setId(from.getId())
                .setVoteCount(from.getVoteCount())
                .setVoteAverage(from.getVoteAverage())
                .setPopularity(from.getPopularity())
                .setAdult(from.isAdult())
                .setTitle(from.getTitle())
                .setPosterPath(POSTER_BASE_URL + from.getPosterPath())
                .setOriginalLanguage(from.getOriginalLanguage())
                .setBackdropPath(from.getBackdropPath())
                .setOriginalTitle(from.getOriginalTitle())
                .setReleaseDate(from.getReleaseDate())
                .setOverview(from.getOverview());
        return movie;
    }

    public List<Movie> mapFrom(List<MovieEntity> from) {
        return from.stream().map(this::mapFrom).collect(Collectors.toList());
    }
}
```


# Unit Testing
Project contains unit test cases of different layers. Following are some examples of test cases-
## Remote Data Store
```java
@RunWith(JUnit4.class)
public class RemoteMovieDataStoreTest {
    private ApiCallInterface apiCallInterface;
    private RemoteMovieDataStore remoteMovieDataStore;
    private MovieDataTestUtils movieDataTestUtils = new MovieDataTestUtils();

    @Before
    public void setup() {
        apiCallInterface = mock(ApiCallInterface.class);
        remoteMovieDataStore = new RemoteMovieDataStore(apiCallInterface);
    }

    @Test
    public void testWhenRequestingPopularMovies_fromRemoteReturnExpectedSize() {
        List<MovieData> dummyMoviesData = movieDataTestUtils.generateMovieDataList(5);
        GetMoviesResponse getMoviesResponse = new GetMoviesResponse();
        getMoviesResponse.setMovies(dummyMoviesData);
        getMoviesResponse.setPage(1);

        when(apiCallInterface.getPopularMovies()).thenReturn(Observable.just(getMoviesResponse));

        remoteMovieDataStore.getPopularMovies().test().assertValue(movieDataList -> movieDataList.size() == 5).assertComplete();
    }

    @Test
    public void testWhenRequestingPopularMovies_fromRemoteReturnExpectedMovieName() {
        List<MovieData> dummyMoviesData = movieDataTestUtils.generateMovieDataList(5);
        GetMoviesResponse getMoviesResponse = new GetMoviesResponse();
        getMoviesResponse.setMovies(dummyMoviesData);
        getMoviesResponse.setPage(1);

        when(apiCallInterface.getPopularMovies()).thenReturn(Observable.just(getMoviesResponse));

        remoteMovieDataStore.getPopularMovies().test().assertValue(movieDataList -> movieDataList.get(0).getTitle().equals("Movie1")).assertComplete();
    }
```

## Mappers
```java
@RunWith(JUnit4.class)
public class DataMapperTests {
    private MovieDataTestUtils movieDataTestUtils = new MovieDataTestUtils();

    @Test
    public void movieDataEntityMapper_testExpectedResult() {
        MovieData movieData = movieDataTestUtils.getTestMovieData(1);
        MovieEntity movieEntity = new MovieDataEntityMapper().mapFrom(movieData);
        Assert.assertEquals(movieData.getId(), movieEntity.getId());
        Assert.assertEquals(movieData.getTitle(), movieEntity.getTitle());
        Assert.assertEquals(movieData.getOriginalTitle(), movieEntity.getOriginalTitle());
        Assert.assertEquals(movieData.getBackdropPath(), movieEntity.getBackdropPath());
        Assert.assertEquals(movieData.getOriginalLanguage(), movieEntity.getOriginalLanguage());
        Assert.assertEquals(movieData.getReleaseDate(), movieEntity.getReleaseDate());
    }
}
```

## Interactors

```java
@RunWith(JUnit4.class)
public class PopularMoviesInteractorUnitTest {
    private DomainTestUtils domainTestUtils = new DomainTestUtils();
    private GetPopularMoviesInteractor getPopularMoviesInteractor;
    private MockMoviesPresenter mockMoviesPresenter;
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        movieRepository = Mockito.mock(MovieRepository.class);
        getPopularMoviesInteractor = new GetPopularMoviesInteractor(movieRepository);
        mockMoviesPresenter = new MockMoviesPresenter();
        getPopularMoviesInteractor.presenter = mockMoviesPresenter;
    }

    @Test
    public void getPopularMovies() {
        int listSize = 4;
        when(movieRepository.getPopularMovies()).thenReturn(Observable.just(domainTestUtils.generateMovieEntityList(listSize)));

        getPopularMoviesInteractor.fetchPopularMovies();
        mockMoviesPresenter.popularMovies.test().assertValue(movieDataList -> movieDataList.size() == listSize).assertComplete();
    }

    private class MockMoviesPresenter implements MoviesPresenter {

        private Observable<List<MovieEntity>> popularMovies;

        @Override
        public void presentMovies(Observable<List<MovieEntity>> popularMovies) {
            this.popularMovies = popularMovies;
        }
    }
}
```

# Package Structure
There are four packages:
1. **data -** The package contains data stores i.e. `MoviesDataStore` which is implemented by `RemoteMoviesDataStore` and `LocalMoviesDataStore`. These data stores provide movies data to `MovieRepository` which provides movies data to the domain layer. 
2. **domain -** This is the core logical layer in which main part is interactors(also called use cases). `GetPopularMoviesInteractor` is the interactor which gets data from `MovieRepository` and provides it to the presentation layer.
3. **presentation -** It contains `MoviesPresenter` and `MoviesView`. `MoviesListActivity` implements `MoviesView`. `MoviesPresenter` gets movies list from `GetPopularMoviesInteractor`. `MoviesRounter` handles navigations from movies list.
4. **common -** It contains configurators, common base classes, constants etc..


# Technologies
1. RxAndroid
2. Retrofit
3. Dagger2
4. Lombok
