package sorting

fun <T : Comparable<T>> MutableList<T>.combSort() {
    var gap = size
    var swapped = true
    while (gap > 1 || swapped) {
        if (gap > 1) gap = (gap / 1.247330950103979).toInt()
        var index = 0
        swapped = false
        while (index + gap < this.size) {
            if (this[index] > this[index + gap]) {
                this[index] = this[index + gap].also { this[index + gap] = this[index] }
                swapped = true
            }
            index++
        }
    }
}
fun <T : Comparable<T>> Array<T>.combSort() {
    var gap = size
    var swapped = true
    while (gap > 1 || swapped) {
        if (gap > 1) gap = (gap / 1.247330950103979).toInt()
        var index = 0
        swapped = false
        while (index + gap < this.size) {
            if (this[index] > this[index + gap]) {
                this[index] = this[index + gap].also { this[index + gap] = this[index] }
                swapped = true
            }
            index++
        }
    }
}
