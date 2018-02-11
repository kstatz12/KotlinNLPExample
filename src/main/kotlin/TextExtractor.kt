import com.amazonaws.services.s3.model.S3Object
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.sax.BodyContentHandler
import java.io.InputStream

class TextExtractor {
    fun extract(obj: S3Object): String {
        val handler = BodyContentHandler()
        val metaData = Metadata()
        AutoDetectParser().parse(obj.objectContent, handler, metaData)
        return handler.toString()
    }
}