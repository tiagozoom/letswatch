package com.example.tgzoom.letswatch;

import android.app.Application;
import com.example.tgzoom.letswatch.data.source.DaggerMoviesRepositoryComponent;
import com.example.tgzoom.letswatch.data.source.MoviesRepository;
import com.example.tgzoom.letswatch.data.source.MoviesRepositoryComponent;
import com.example.tgzoom.letswatch.data.source.MoviesRepositoryModule;
import com.example.tgzoom.letswatch.network.ServiceModule;

/**
 * Created by tgzoom on 12/27/16.
 */

public class App extends Application {

    private MoviesRepositoryComponent mMoviesRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mMoviesRepositoryComponent = DaggerMoviesRepositoryComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .serviceModule(new ServiceModule("http://api.themoviedb.org"))
                .moviesRepositoryModule(new MoviesRepositoryModule())
                .build();

        MoviesRepository moviesRepository = mMoviesRepositoryComponent.getMoviesRepository();
        moviesRepository.getMovies("popularity.desc",1);
    }

    public MoviesRepositoryComponent getMoviesRepositoryComponent(){
        return mMoviesRepositoryComponent;
    }
}
