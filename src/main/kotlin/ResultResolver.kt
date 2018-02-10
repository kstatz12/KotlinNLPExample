import edu.stanford.nlp.ie.util.RelationTriple

class ResultResolver {

    fun getResults(entities: List<EmbeddedToken>, triples: List<RelationTriple>) : List<Result>{
        return entities.map { x-> resolve(x, triples) }
    }

    fun resolve(entity: EmbeddedToken, triples: List<RelationTriple>) : Result{
        var entityTriples = triples.filter { x-> x.subjectLemmaGloss() == entity.Value }
        return Result(entity.Value, entity.Key, entityTriples)
    }
}