package dev.nashe.pokemon.data.mapper

import dev.nashe.pokemon.data.models.PokemonDetailEntity
import dev.nashe.pokemon.domain.models.PokemonDetailModel

class PokemonResponseDetailMapper: ResponseMapper<PokemonDetailEntity, PokemonDetailModel> {
    override fun mapToDomain(entity: PokemonDetailEntity): PokemonDetailModel {
        return PokemonDetailModel(
            name = entity.name,
            height = entity.height,
            imageUrl = entity.sprites.frontDefault
        )
    }
}
