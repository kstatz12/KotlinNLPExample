import java.util.*

class FluentPipleLineConfiguration {
    private var propString = "tokenize,ssplit,pos,lemma, depparse"
    private var isNer = false
    private var isOpenIe = false
    private var isMentions = false
    private var isRelations = false

    fun buildDefault() : Properties{
        val prop = Properties()
        prop["annotators"] = propString
        return prop
    }
    fun withNamedEntityRecognition() : FluentPipleLineConfiguration{
        propString += ",ner"
        isNer = true
        return this
    }

    fun withRegExNamedEntityRecognition() : FluentPipleLineConfiguration{
        propString += ",ner, regexner"
        isNer = true
        return this
    }

    fun withOpenIe() : FluentPipleLineConfiguration {
        propString += ",natlog,openie"
        isOpenIe = true
        return this
    }

    fun withMentions() : FluentPipleLineConfiguration {
        propString += ",mention"
        isMentions = true
        return this
    }

    fun withRelations() : FluentPipleLineConfiguration {
        if(!propString.contains("ner", true)){
            propString+=",ner"
        }
        propString += ",parse,relation"
        isRelations = true
        isNer = true
        return this
    }

    fun build() : PipelineConfiguration{
        var prop = Properties()
        prop["annotators"] = propString
        if(isNer){
            prop.setProperty("ner.model", "edu/stanford/nlp/models/ner/english.conll.4class.distsim.crf.ser.gz")
        }
        return PipelineConfiguration(prop, isNer, isOpenIe, isMentions, isRelations)
    }


}