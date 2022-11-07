package gst.trainingcourse.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gst.trainingcourse.data.repositories.SchoolRepositoryImpl
import gst.trainingcourse.domain.repositories.SchoolRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindSchoolRepository(schoolRepositoryImpl: SchoolRepositoryImpl): SchoolRepository
}