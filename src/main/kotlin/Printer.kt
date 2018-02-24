class Printer {
    fun print(results : List<Entity>){
        for(result in results.sortedByDescending { x->x.mentionCount }){
            println("Entity Found: ${result.entity} of type ${result.entityType} and was mentioned ${result.mentionCount} times")
            if(result.relationShips.count() > 0){
                println("Found The Following Relations")
            }
            for(triple in result.relationShips){
                println("\t ${triple.subjectLemmaGloss()} \t ${triple.relationLemmaGloss()} \t ${triple.objectLemmaGloss()}")
            }
        }
    }
}