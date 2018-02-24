import edu.stanford.nlp.ie.util.RelationTriple

class ResultResolver {

    fun getResults(entities: List<EmbeddedToken>, triples: List<RelationTriple>) : List<RawResult>{
        return entities.map { x-> resolve(x, triples) }
    }

    fun resolve(entity: EmbeddedToken, triples: List<RelationTriple>) : RawResult{
        val entityTriples = triples.filter { x-> x.subjectLemmaGloss() == entity.Value }
        return RawResult(entity.Value, entity.Key, entityTriples)
    }
}