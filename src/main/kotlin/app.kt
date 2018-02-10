import com.amazonaws.services.s3.AmazonS3ClientBuilder

fun main(args: Array<String>){

    var props = FluentPropertyBuilder().withBasics()
            .withNamedEntityRecognition()
            .withOpenInformationExtraction()
            .withCoreReference()
            .build()
    var nlp = StanfordCoreNLPFactory().create(props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("test-nlp-bucket", "Business Plan.pdf")
    val text = TextExtractor().extract(obj)
    val results = ResultFactory(nlp).create(text)
    ResultPrinter().print(results)
}
