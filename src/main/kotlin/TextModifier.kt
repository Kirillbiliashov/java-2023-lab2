
val CONSONANT_LETTERS = listOf("B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
    "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z")

class TextModifier(text: String, private val removeLength: Int) {

    private val sb = StringBuffer(text)
    private var startSpaceIdx = -1
    var nextSpaceIdx = 0
    var substr = ""
    private val substringStartsWithConsonant: Boolean
        get() = CONSONANT_LETTERS.contains(substr.first().toString().uppercase())
    private val substringMatches: Boolean
        get() = substr.length == removeLength &&
                !substringContainsNonChars &&
                substringStartsWithConsonant

    private val substringContainsNonChars: Boolean
    get() = substr.contains(".") || substr.contains(",")
            || substr.contains("!") || substr.contains("!")

    fun removeWords() {
        while (nextSpaceIdx != -1) {
            nextSpaceIdx = sb.indexOf(" ", startSpaceIdx + 1)
            if (nextSpaceIdx == -1 && startSpaceIdx == -1) {
                handleOneWordCase(removeLength)
                break
            }
            substr = if (nextSpaceIdx != -1) sb.substring(startSpaceIdx + 1, nextSpaceIdx)
            else sb.substring(startSpaceIdx + 1)
            startSpaceIdx = if (substringMatches) {
                getStartSpaceIdxForFoundMatch()
            } else {
                if (substringContainsNonChars) getNextIdxForNonChar()
                else nextSpaceIdx
            }
        }
        println("final string: $sb")
    }

    private fun getNextIdxForNonChar(): Int {
        var endOffset = 0
        while (substringContainsNonChars) {
            substr = substr.dropLast(1)
            endOffset++
        }
        return if (substr.length == removeLength && substringStartsWithConsonant)
            getStartSpaceIdxForFoundMatch(endOffset = endOffset)
        else nextSpaceIdx
    }


    private fun getStartSpaceIdxForFoundMatch(endOffset: Int = 0): Int {
        val endIdx = if (nextSpaceIdx == -1) sb.length else nextSpaceIdx - endOffset
        sb.replace(startSpaceIdx + 1, endIdx, "")
        return sb.indexOf(" ", startSpaceIdx + 1)
    }


    private fun handleOneWordCase(removeLength: Int) {
        if (sb.length == removeLength) sb.replace(0, sb.length, "")
    }

}

