import edu.stanford.nlp.ie.machinereading.structure.MachineReadingAnnotations
import edu.stanford.nlp.util.CoreMap

class RelationsProcessor {
    fun processSentences(sentences : List<CoreMap>) : MutableList<RelationTriple> {
        var relations = mutableListOf<RelationTriple>()
        for(sentence in sentences) {
            var results = sentence[MachineReadingAnnotations.RelationMentionsAnnotation::class.java]
            for(r in results){
                if(r.type != "_NL")
                relations.add(RelationTriple(r.entityMentionArgs[0].value, r.type, r.entityMentionArgs[1].value))
            }
        }
        for(r in relations){
            println("${r.subject} \t ${r.predicate} \t ${r.obj}")
        }
        return relations
    }
}