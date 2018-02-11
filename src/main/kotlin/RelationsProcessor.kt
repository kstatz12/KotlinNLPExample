import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations
import edu.stanford.nlp.util.CoreMap

class RelationsProcessor {
    fun processSentences(sentences : List<CoreMap>) : MutableList<RelationTriple> {
        val relations = mutableListOf<RelationTriple>()
        sentences
                .map { it[MachineReadingAnnotations.RelationMentionsAnnotation::class.java] }
                .flatMap { it }
                .filter { it.type != "_NL" }
                .mapTo(relations) { RelationTriple(it.entityMentionArgs[0].value, it.type, it.entityMentionArgs[1].value) }
        for(r in relations){
            println("${r.subject} \t ${r.predicate} \t ${r.obj}")
        }
        return relations
    }
}