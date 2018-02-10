import edu.stanford.nlp.ie.util.RelationTriple
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations
import edu.stanford.nlp.util.CoreMap

class TripleProcessor {
    fun processSentences(sentences: List<CoreMap>): MutableList<RelationTriple> {
        var relations = mutableListOf<RelationTriple>()
        sentences
                .map { it.get(NaturalLogicAnnotations.RelationTriplesAnnotation::class.java).toMutableList() }
                .forEach { relations.addAll(it) }
        return relations
    }
}