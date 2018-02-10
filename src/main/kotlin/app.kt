import com.amazonaws.services.s3.AmazonS3ClientBuilder
fun main(args: Array<String>){

    var props = FluentPropertyBuilder().withBasics().withNamedEntityRecognition().withOpenInformationExtraction().build()
    var nlp = StanfordCoreNLPFactory().create(props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("", "")
    val text = TextExtractor().extract(obj)
    val sentences = SentenceExtractor().extract(text, nlp)
    val entities = NERProcessor().processSentences(sentences)
    val relations = TripleProcessor().processSentences(sentences)
    val processedEntities = PostProcessor().removeDuplicates(entities)

    val results = ResultResolver().getResults(processedEntities, relations)

    for(result in results){
        println("Entity Found: ${result.entityValue} of type ${result.entityType}")
        if(result.triples.count() > 0){
            println("Found The Following Relations")
        }
        for(triple in result.triples){

            println("\t ${triple.objectLemmaGloss()} \t ${triple.subjectLemmaGloss()} \t ${triple.relationLemmaGloss()}")
        }
    }
}
