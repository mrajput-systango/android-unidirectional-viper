package com.systango.viperboilerplate.domain;

import com.systango.viperboilerplate.data.repository.MovieRepository;
import com.systango.viperboilerplate.domain.common.DomainTestUtils;
import com.systango.viperboilerplate.domain.entity.MovieEntity;
import com.systango.viperboilerplate.domain.interactor.GetPopularMoviesInteractor;
import com.systango.viperboilerplate.presentation.presenter.base.MoviesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

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