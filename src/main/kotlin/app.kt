import com.amazonaws.services.s3.AmazonS3ClientBuilder
import edu.stanford.nlp.coref.CorefCoreAnnotations

fun main(args: Array<String>){

    var props = FluentPropertyBuilder().withBasics().withNamedEntityRecognition().withOpenInformationExtraction().withCoreReference().build()
    var nlp = StanfordCoreNLPFactory().create(props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("test-nlp-bucket", "Business Plan.pdf")
    val text = TextExtractor().extract(obj)
    val sentences = SentenceExtractor().extract(text, nlp)
    val entities = NERProcessor().processSentences(sentences)
    val relations = TripleProcessor().processSentences(sentences)
    val results = ResultResolver().getResults(entities, relations)
    CoreRefProcessor().getCoreRefs(text,nlp)
    for(result in results.distinctBy { x->x.entityValue }){
        println("Entity Found: ${result.entityValue} of type ${result.entityType}")
        if(result.triples.count() > 0){
            println("Found The Following Relations")
        }
        for(triple in result.triples){

            println("\t ${triple.objectLemmaGloss()} \t ${triple.subjectLemmaGloss()} \t ${triple.relationLemmaGloss()}")
        }
    }
}
