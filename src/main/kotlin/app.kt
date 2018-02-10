import com.amazonaws.services.s3.AmazonS3ClientBuilder
import java.util.*

fun main(args: Array<String>){

    val props = Properties()
    props["annotators"] = "tokenize, ssplit, pos, lemma, ner, regexner, depparse, natlog, openie, parse, mention"
    var nlp = StanfordCoreNLPFactory().create(props)
    val obj = AmazonS3ClientBuilder.defaultClient()
            .getObject("test-nlp-bucket", "Partnership Agreement.pdf")
    val text = TextExtractor().extract(obj)
    val result = ResultFactory(nlp).create(text)
    ResultPrinter().print(result)

}
