import edu.stanford.nlp.pipeline.StanfordCoreNLP

class ResultFactory(private val nlp: StanfordCoreNLP) {
    fun create(text: String) : List<Result>{
        val sentences = SentenceExtractor().extract(text, nlp)
        val entities = NERProcessor().processSentences(sentences)
        val relations = TripleProcessor().processSentences(sentences)
        val mentions = MentionsProcessor().getCoreRefs(text,nlp)
        return ResultResolver().getResults(entities, relations, mentions)
    }
}