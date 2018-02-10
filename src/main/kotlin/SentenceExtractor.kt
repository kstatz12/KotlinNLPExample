import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.util.CoreMap

class SentenceExtractor {
    fun getSentences(annotation: Annotation): List<CoreMap> {
        return annotation.get(CoreAnnotations.SentencesAnnotation::class.java)
    }
}