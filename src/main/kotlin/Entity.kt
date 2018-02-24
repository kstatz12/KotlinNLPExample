import edu.stanford.nlp.ie.util.RelationTriple
data class Entity(val entity: String, val entityType: String, val mentionCount: Int, val relationShips: List<RelationTriple>)