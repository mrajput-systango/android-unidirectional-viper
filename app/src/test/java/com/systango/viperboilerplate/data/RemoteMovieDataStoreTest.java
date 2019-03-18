package com.systango.viperboilerplate.data;

import com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore;
import com.systango.viperboilerplate.data.entity.MovieData;
import com.systango.viperboilerplate.data.entity.MovieDetailsData;
import com.systango.viperboilerplate.data.network.ApiCallInterface;
import com.systango.viperboilerplate.data.network.response.GetMoviesResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mohit Rajput on 17/3/19.
 * Unit test cases of {@link com.systango.viperboilerplate.data.datastore.RemoteMovieDataStore}
 */
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

    @Test
    public void testWhenRequestingPopularMovies_fromRemoteReturnEmptyResult() {
        List<MovieData> dummyMoviesData = movieDataTestUtils.generateMovieDataList(0);
        GetMoviesResponse getMoviesResponse = new GetMoviesResponse();
        getMoviesResponse.setMovies(dummyMoviesData);
        getMoviesResponse.setPage(1);

        when(apiCallInterface.getPopularMovies()).thenReturn(Observable.just(getMoviesResponse));

        remoteMovieDataStore.getPopularMovies().test().assertValue(List::isEmpty).assertComplete();
    }

    @Test
    public void testWhenRequestingMovie_fromRemoteReturnExpectedValue() {
        MovieData movieData = movieDataTestUtils.getTestMovieData(1);
        MovieDetailsData movieDetailsData = new MovieDetailsData();
        movieDetailsData.setId(movieData.getId());
        movieDetailsData.setTitle(movieData.getTitle());

        when(apiCallInterface.getMovieDetails(1)).thenReturn(Observable.just(movieDetailsData));

        remoteMovieDataStore.getMovieById(1).test().assertValue(result -> result.isPresent()
                && result.get().getTitle().equals("Movie1")
                && result.get().getId() == 1).assertComplete();
    }
}
