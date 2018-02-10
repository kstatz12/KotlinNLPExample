import me.xdrop.fuzzywuzzy.FuzzySearch
import kotlin.collections.HashMap

class PostProcessor {
    fun removeDuplicates(tokens: List<EmbeddedToken>): List<EmbeddedToken>{
        val rawOrgs = filter(tokens, "ORGANIZATION")
        val rawPeople = filter(tokens,"PERSON")
        val rawLocations = filter(tokens, "LOCATION").toMutableList()
        val orgs = getSubset(rawOrgs)
        val people = getSubset(rawPeople)
        val locations = getSubset(rawLocations)

        val ret = mutableListOf<EmbeddedToken>()
        orgs.mapTo(ret) { EmbeddedToken("ORGANIZATION", it) }
        people.mapTo(ret) { EmbeddedToken("PERSON", it) }
        locations.mapTo(ret) { EmbeddedToken("LOCATION", it) }
        return ret
    }


    private fun filter(tokens: List<EmbeddedToken>, type: String) : List<String>{
       return tokens.filter { x-> x.Key == type } .map { x->x.Value }
    }

    private fun getSubset(enitities: List<String>) : List<String>{
         return toHashMap(enitities).map { x-> x.key }
    }

    private fun toHashMap(entities: List<String>) : HashMap<String, Int>{
        val items = hashMapOf<String, Int>()
        for(e in entities){
            for(j in entities){
                val ratio = FuzzySearch.partialRatio(e, j)
                //if they are a close match add one of them
                if(ratio > 70){
                    //if one is longer than the other, choose the longer
                    //because it is "probably" more accurate
                    if(e.length > j.length){
                        addToHashMap(items, e, ratio)
                    }
                    else{
                        addToHashMap(items, j, ratio)
                    }
                }
            }
        }
        return items
    }

    private fun addToHashMap(map: HashMap<String, Int>, key: String, ratio: Int){
       if(map[key] == null){
           map[key] = ratio
       }
    }
}