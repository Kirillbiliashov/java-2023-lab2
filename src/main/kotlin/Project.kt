
fun main() {
    print("Enter word length: ")
    var removeLength: Int
    while (true) {
        try {
            removeLength = readLine()!!.toInt()
            if (removeLength > 0) break
            else print("Enter valid word length (at least 1): ")
        } catch (e: Exception) {
            print("Incorrect format. Try again: ")
        }
    }
    print("Enter text: ")
    val text = readLine()!!
    TextModifier(text, removeLength).removeWords()
}
