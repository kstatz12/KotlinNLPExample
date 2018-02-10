import java.util.*

class FluentPropertyBuilder {
    val props = mutableListOf<String>()

    fun withBasics() : FluentPropertyBuilder{
        props.add("tokenize, ssplit, pos, lemma")
        return this
    }
    fun withNamedEntityRecognition() : FluentPropertyBuilder{
        props.add("ner, regexner")
        return this
    }

    fun withOpenInformationExtraction() : FluentPropertyBuilder{
        props.add("depparse, natlog, openie")
        return this
    }

    fun withCoreReference() : FluentPropertyBuilder{
        props.add("parse, mention, coref")
        return this
    }

    fun build() : Properties {
        val p = props.toTypedArray().joinToString<String>()
        val properties = Properties()
        properties["annotators"] = p
        return properties
    }
}