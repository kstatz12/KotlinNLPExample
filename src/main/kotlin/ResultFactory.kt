import edu.stanford.nlp.pipeline.StanfordCoreNLP

class ResultFactory(private val nlp: StanfordCoreNLP) {
    fun create(text: String) : List<Result>{
        val document = Annotator(nlp).getAnnotation(text)
        val sentences = SentenceExtractor().getSentences(document)
        val entities = NERProcessor().processSentences(sentences)
        val relations = TripleProcessor().processSentences(sentences)
        val mentions = MentionsProcessor().getCoreRefs(text,nlp)
        return ResultResolver().getResults(entities, relations, mentions)
    }
}