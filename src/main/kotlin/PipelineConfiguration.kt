import java.util.*

data class PipelineConfiguration(val props: Properties, val ner: Boolean, val extractTriples: Boolean, val mentions: Boolean, val relations: Boolean)

