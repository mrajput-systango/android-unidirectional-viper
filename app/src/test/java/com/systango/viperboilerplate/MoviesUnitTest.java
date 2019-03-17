package com.systango.viperboilerplate;

import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.common.DomainTestUtils;
import com.systango.viperboilerplate.domain.interactor.GetPopularMoviesInteractor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import io.reactivex.Observable;

@RunWith(JUnit4.class)
public class MoviesUnitTest {
    private DomainTestUtils domainTestUtils = new DomainTestUtils();

    @Test
    void getPopularMovies() {
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.getPopularMovies()).thenReturn(Observable.just(domainTestUtils.generateMovieEntityList()));
        GetPopularMoviesInteractor getPopularMoviesInteractor = new GetPopularMoviesInteractor(movieRepository);
        getPopularMoviesInteractor.fetchPopularMovies();
    }
}