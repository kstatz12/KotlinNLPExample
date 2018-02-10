import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.coref.data.Mention
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP

class MentionsProcessor {
    fun getCoreRefs(content: String, nlp: StanfordCoreNLP) : List<Mention>{
        val document = Annotation(content)
        nlp.annotate(document)
        val mentions = mutableListOf<Mention>()
        for (sentence in document.get(CoreAnnotations.SentencesAnnotation::class.java)) {
            mentions += sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation::class.java)
        }
        return mentions.toList()
    }
}