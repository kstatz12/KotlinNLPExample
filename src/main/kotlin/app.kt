import com.amazonaws.services.s3.AmazonS3ClientBuilder
fun main(args: Array<String>){
    val config = FluentPipleLineConfiguration().withNamedEntityRecognition().withOpenIe().build()
    println("")
    val nlp = StanfordCoreNLPFactory().create(config.props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("test-nlp-bucket", "manafort-gates_edva_indictment.pdf")
    val text = TextExtractor().extract(obj)
    val result = ResultFacade(nlp, config).getResults(text)
    val entities= EntityResolver().resolve(result)
    Printer().print(entities)
}