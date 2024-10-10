package dev.nashe.pokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nashe.pokemon.data.api.PokemonService
import dev.nashe.pokemon.data.api.ServiceFactory
import dev.nashe.pokemon.data.mapper.PokemonResponseDetailMapper
import dev.nashe.pokemon.data.mapper.PokemonResponseMapper
import dev.nashe.pokemon.data.repositories.PokemonRepositoryImpl
import dev.nashe.pokemon.domain.repository.PokemonRepository
import dev.nashe.pokemon.domain.usecases.PokemonDetailUseCase
import dev.nashe.pokemon.domain.usecases.PokemonDetailUseCaseImpl
import dev.nashe.pokemon.domain.usecases.PokemonListUseCase
import dev.nashe.pokemon.domain.usecases.PokemonListUseCaseImpl
import dev.nashe.pokemon.domain.util.DefaultDispatcherProvider
import dev.nashe.pokemon.domain.util.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesPokemonService(): PokemonService {
        return ServiceFactory.makeRetrofitApiService(true)
    }

    @Provides
    @Singleton
    fun providesPokemonRepository(
        pokemonService: PokemonService,
        responseMapper: PokemonResponseMapper,
        responseDetailMapper: PokemonResponseDetailMapper
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            pokemonService = pokemonService,
            pokemonResponseMapper = responseMapper,
            pokemonResponseDetailMapper = responseDetailMapper
        )
    }

    @Provides
    fun providesPokemonResponseDetailMapper(): PokemonResponseDetailMapper {
        return PokemonResponseDetailMapper()
    }

    @Provides
    fun providesPokemonResponseMapper(): PokemonResponseMapper {
        return PokemonResponseMapper()
    }

    @Provides
    fun providesPokemonListUseCase(pokemonRepository: PokemonRepository): PokemonListUseCase {
        return PokemonListUseCaseImpl(pokemonRepository = pokemonRepository)
    }

    @Provides
    fun providesPokemonDetailUseCase(pokemonRepository: PokemonRepository): PokemonDetailUseCase {
        return PokemonDetailUseCaseImpl(pokemonRepository = pokemonRepository)
    }

    @Provides
    fun providesDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}
