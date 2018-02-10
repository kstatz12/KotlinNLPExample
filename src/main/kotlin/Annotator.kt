import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.pipeline.Annotation

class Annotator(private val nlp: StanfordCoreNLP) {
    fun getAnnotation(content: String) : Annotation{
        val document = Annotation(content)
        nlp.annotate(document)
        return document
    }
}