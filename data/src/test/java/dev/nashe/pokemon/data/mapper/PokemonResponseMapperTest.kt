package dev.nashe.pokemon.data.mapper

import dev.nashe.pokemon.data.models.PokemonEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PokemonResponseMapperTest {

    private val pokemonResponseMapper = PokemonResponseMapper()

    @Test
    fun testMapPokemonEntityToDomainModel() {
        // Given
        val pokemonEntity = PokemonEntity(
            name = "venusaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )

        // When
        val pokemonModel = pokemonResponseMapper.mapToDomain(pokemonEntity)

        // Then
        assertEquals(pokemonEntity.name, pokemonModel.name)
        assertEquals(pokemonEntity.url, pokemonModel.url)
    }
}
