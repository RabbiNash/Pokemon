package dev.nashe.pokemon.data.mapper

interface ResponseMapper<in E, out D> {
    fun mapToDomain(entity : E): D

    fun mapToDomainList(list: List<E>) : List<D> = list.mapTo(mutableListOf(), ::mapToDomain)
}
