import edu.stanford.nlp.coref.data.Mention
import edu.stanford.nlp.ie.util.RelationTriple
import edu.stanford.nlp.pipeline.StanfordCoreNLP

class ResultFacade(private val nlp: StanfordCoreNLP, private val config: PipelineConfiguration) {
    fun getResults(text: String) : List<Result>{
        val document = Annotator(nlp).getAnnotation(text)
        val sentences = SentenceExtractor().getSentences(document)
        val entities = mutableListOf<EmbeddedToken>()
        val relations = mutableListOf<RelationTriple>()
        val mentions = mutableListOf<Mention>()
        if(config.ner)
        {
            entities += NERProcessor().processSentences(sentences)
        }
        if(config.extractTriples)
        {
            relations += TripleProcessor().processSentences(sentences)
        }
        if(config.mentions){

            mentions += MentionsProcessor().getCoreRefs(text,nlp)
        }
        if(config.relations){
            RelationsProcessor().processSentences(sentences)
        }
        return ResultResolver().getResults(entities, relations, mentions)
    }
}