class EntityResolver {

    fun resolve(results: List<RawResult>): List<Entity> {
        return results.toTypedArray()
                .groupBy { x-> x.entityValue }
                .map { y-> Entity(y.key, y.value.first().entityType, y.value.count(), y.value.first().triples.toList()) }
    }
}