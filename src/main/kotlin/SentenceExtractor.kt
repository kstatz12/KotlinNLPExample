import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.util.CoreMap
import java.util.*

class SentenceExtractor {
    var properties = Properties()
    fun extract(content: String, nlp: StanfordCoreNLP) : List<CoreMap>{
        var annotation = getAnnotation(content, nlp)
        return getSentences(annotation)
    }

    private fun getAnnotation(content: String, nlp: StanfordCoreNLP) : Annotation{
        var document = Annotation(content)
        nlp.annotate(document)
        return document
    }

    fun getSentences(annotation: Annotation): List<CoreMap> {
        return annotation.get(CoreAnnotations.SentencesAnnotation::class.java)
    }
}