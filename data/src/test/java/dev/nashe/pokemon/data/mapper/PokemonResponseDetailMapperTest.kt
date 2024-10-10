package dev.nashe.pokemon.data.mapper

import dev.nashe.pokemon.data.models.PokemonDetailEntity
import dev.nashe.pokemon.data.models.Sprites
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PokemonResponseDetailMapperTest {
    private val pokemonResponseDetailMapper = PokemonResponseDetailMapper()

    @Test
    fun testMapPokemonDetailEntityToDomainModel() {
        // Given
        val pokemonDetailEntity = PokemonDetailEntity(
            name = "gotham",
            height = 7,
            sprites = Sprites(frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
        )

        // When
        val pokemonDetailModel = pokemonResponseDetailMapper.mapToDomain(pokemonDetailEntity)

        // Then
        assertEquals(pokemonDetailEntity.name, pokemonDetailModel.name)
        assertEquals(pokemonDetailEntity.height, pokemonDetailModel.height)
        assertEquals(pokemonDetailEntity.sprites.frontDefault, pokemonDetailModel.imageUrl)
    }
}
