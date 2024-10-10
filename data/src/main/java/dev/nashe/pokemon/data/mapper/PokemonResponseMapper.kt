package dev.nashe.pokemon.data.mapper

import dev.nashe.pokemon.data.models.PokemonEntity
import dev.nashe.pokemon.domain.models.PokemonModel
import javax.inject.Inject

class PokemonResponseMapper @Inject constructor(): ResponseMapper<PokemonEntity, PokemonModel> {
    override fun mapToDomain(entity: PokemonEntity): PokemonModel {
        return PokemonModel(
            name = entity.name,
            url = entity.url
        )
    }
}
