import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.util.CoreMap


class NERProcessor {

    fun processSentences(sentences: List<CoreMap>): List<EmbeddedToken> {
        val tokens = mutableListOf<EmbeddedToken>()
        val sb = StringBuilder()
        for (sentence in sentences) {

            processSentence(sentence, tokens, sb)
        }
        return tokens
    }

    private fun processSentence(sentence: CoreMap, tokens: MutableList<EmbeddedToken>, sb: StringBuilder) {

        var previousToken = "O"
        var currentToken : String
        var newToken = true
        for (token in sentence.get(CoreAnnotations.TokensAnnotation::class.java)) {
            currentToken = token.get(CoreAnnotations.NamedEntityTagAnnotation::class.java)
            val word = token.get(CoreAnnotations.TextAnnotation::class.java)
            if (currentToken == "O") {
                if (previousToken != "O" && sb.isNotEmpty()) {
                    handle(previousToken, sb, tokens)
                    newToken = true
                }
                continue
            }

            if (newToken) {
                previousToken = currentToken
                newToken = false
                sb.append(word)
                continue
            }

            if (currentToken == previousToken) {
                sb.append(" " + word)
            } else {
                handle(previousToken, sb, tokens)
                newToken = true
            }
            previousToken = currentToken
        }
    }

    private fun handle(key: String, stringBuilder: StringBuilder, tokens: MutableList<EmbeddedToken>) {
        tokens += EmbeddedToken(key, stringBuilder.toString())
        //clear out stringBuilder
        stringBuilder.setLength(0)
    }
}

