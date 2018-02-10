import edu.stanford.nlp.ie.util.RelationTriple

data class Result(val entityValue: String, val entityType: String, val triples: List<RelationTriple>)
