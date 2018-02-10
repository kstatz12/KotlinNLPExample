class ResultPrinter {
    fun print(results : List<Result>){
        for(result in results.distinctBy { x->x.entityValue }.sortedByDescending { x->x.mentions.count() }){
            println("Entity Found: ${result.entityValue} of type ${result.entityType}")
            if(result.triples.count() > 0){
                println("Found The Following Relations")
            }
            for(triple in result.triples){
                println("\t ${triple.subjectLemmaGloss()} \t ${triple.relationLemmaGloss()} \t ${triple.objectLemmaGloss()}")
            }
            if(result.mentions.count() > 0){
                println("${result.entityValue} was mentioned ${result.mentions.count()} times")
            }
        }
    }
}