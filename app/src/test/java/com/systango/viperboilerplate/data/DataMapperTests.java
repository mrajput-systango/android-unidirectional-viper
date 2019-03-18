package com.systango.viperboilerplate.data;

import com.systango.viperboilerplate.data.entity.MovieData;
import com.systango.viperboilerplate.data.mapper.MovieDataEntityMapper;
import com.systango.viperboilerplate.domain.entity.MovieEntity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by Mohit Rajput on 18/3/19.
 */
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
