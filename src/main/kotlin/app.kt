import com.amazonaws.services.s3.AmazonS3ClientBuilder

fun main(args: Array<String>){

    val config = FluentPipleLineConfiguration().withNamedEntityRecognition().withOpenIe().build()
    val nlp = StanfordCoreNLPFactory().create(config.props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("test-nlp-bucket", "ECHO_News_2017_11_3_Corporate_Releases.pdf")
    val text = TextExtractor().extract(obj)
    val result = ResultFacade(nlp, config).getResults(text)
    ResultPrinter().print(result)
}
