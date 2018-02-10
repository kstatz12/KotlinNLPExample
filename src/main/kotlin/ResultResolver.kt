import edu.stanford.nlp.coref.data.Mention
import edu.stanford.nlp.ie.util.RelationTriple

class ResultResolver {

    fun getResults(entities: List<EmbeddedToken>, triples: List<RelationTriple>, mentions: List<Mention>) : List<Result>{
        return entities.map { x-> resolve(x, triples, mentions) }
    }

    fun resolve(entity: EmbeddedToken, triples: List<RelationTriple>, mentions: List<Mention>) : Result{
        var entityTriples = triples.filter { x-> x.subjectLemmaGloss() == entity.Value }
        val entityMentions = mentions.filter { x->x.spanToString() == entity.Value }
        return Result(entity.Value, entity.Key, entityTriples, entityMentions)
    }
}