import edu.stanford.nlp.coref.CorefCoreAnnotations
import edu.stanford.nlp.dcoref.CorefChain
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP

class CoreRefProcessor {
    fun getCoreRefs(content: String, nlp: StanfordCoreNLP){
        var document = Annotation(content)
        nlp.annotate(document)
        for (cc in document.get(CorefCoreAnnotations.CorefChainAnnotation::class.java).values) {
            println("\t" + cc)
        }
        for (sentence in document.get(CoreAnnotations.SentencesAnnotation::class.java)) {
            println("---")
            println("mentions")
            for (m in sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation::class.java)) {
                println("\t" + m)
            }

        }
    }

}