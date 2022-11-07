package gst.trainingcourse.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gst.trainingcourse.data.database.SchoolDatabase
import gst.trainingcourse.data.database.dao.SchoolDao
import gst.trainingcourse.data.database.dao.StudentDao
import gst.trainingcourse.data.utils.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideSchoolDao(schoolDatabase: SchoolDatabase): SchoolDao {
        return schoolDatabase.schoolDao()
    }

    @Provides
    fun provideStudentDao(schoolDatabase: SchoolDatabase): StudentDao {
        return schoolDatabase.studentDao()
    }

    @Provides
    @Singleton
    fun provideSchoolDatabase(@ApplicationContext context: Context): SchoolDatabase {
        return Room
            .databaseBuilder(
            context,
            SchoolDatabase::class.java, Constants.Data.DATABASE)
            .build()
    }
}