package structures.linked_list.singly_linked_list.circular

open class DanilCircularSingleLinkedList<T> : CircularSingleLinkedList<T> {
    protected val data = CurrentNodeLink<T>()
    protected var mutableSize = 0
    final override val size
        get() = mutableSize

    override fun addNext(item: T, index: Int) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            var i = index % size
            while (i > 0) {
                i--
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun add(item: T) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            while (current.next!=data.link) {
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun addPrevious(item: T, index: Int) {
        if (isEmpty()) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            var localIndex = index % size - 1
            if (localIndex <= 0) localIndex += size
            while (localIndex > 0) {
                localIndex--
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun removeAt(index: Int) = if (isEmpty()) {
        throw IllegalArgumentException()
    } else {
        mutableSize--
        if (size == 1) {
            data.link = null
        } else {
            var current = data.link!!
            var i = 0
            while (i < index % size) {
                i++
                current = current.next
            }
            current.value = current.next.value.apply {
                if (current.next == data.link) data.link = current.next.next
                current.next = current.next.next
            }
        }
    }

    override fun remove(item: T) = if (isEmpty()) {
        false
    } else {
        var current = data.link!!
        var i = size
        var deleted = false
        while (i > 0 && !deleted) {
            i--
            if (current.value == item) {
                if (size == 1) data.link = null
                else current.value = current.next.value.apply {
                    if (current.next == data.link) data.link = current.next.next
                    current.next = current.next.next
                    deleted = true
                }
                mutableSize--
            } else current = current.next
        }
        deleted
    }

    final override fun isEmpty() = size == 0

    final override val current
        get() = data.link?.value ?: throw IllegalArgumentException()

    override fun toList() = with(receiver = mutableListOf<T>()) {
        data.link?.let {
            var current = it
            do {
                add(current.value)
                current = current.next
            } while (current != it)
        }
        toList()
    }

    override fun goForward(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = steps % size
        while (i > 0) {
            i--
            data.link = data.link!!.next
        }
    }

    override fun goBack(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = size - steps % size
        while (i > 0) {
            i--
            data.link = data.link!!.next
        }
    }

    override fun clear() {
        if (!isEmpty()) with(receiver = data) {
            // Разомкнуть кольцо ссылок, чтобы сборщик мусора мог освободить память
            link!!.next = link!!
            link = null
            mutableSize = 0
        }
    }

    override fun get(index: Int) = if (isEmpty()) {
        throw IllegalArgumentException()
    } else {
            var current = data.link!!
            var i = 0
            while (i<index) {
                i++
                current = current.next
            }
            current.value
        }

    protected interface Node<T> {
        var value: T
        var next: Node<T>
    }

    protected class DanilNode<T>(override var value: T) : Node<T> {
        constructor(value: T, next: Node<T>) : this(value) {
            this.next = next
        }

        override var next: Node<T> = this
    }

    protected class CurrentNodeLink<T>(var link: Node<T>? = null)
}
