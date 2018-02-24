import edu.stanford.nlp.ie.util.RelationTriple

data class RawResult(val entityValue: String, val entityType: String, val triples: List<RelationTriple>)
