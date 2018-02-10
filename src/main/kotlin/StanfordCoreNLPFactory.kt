import edu.stanford.nlp.pipeline.StanfordCoreNLP
import java.util.*

class StanfordCoreNLPFactory {
    fun create(props: Properties) : StanfordCoreNLP{
        return StanfordCoreNLP(props)
    }
}