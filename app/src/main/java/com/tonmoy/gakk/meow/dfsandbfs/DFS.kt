package com.tonmoy.gakk.meow.dfsandbfs
import java.util.*
fun main(args: Array<String>) {
    val g = Graph(4)
    g.addEdge(0, 1)
    g.addEdge(0, 2)
    g.addEdge(1, 2)
    g.addEdge(2, 0)
    g.addEdge(2, 3)
    g.addEdge(3, 3)
    println("Following is Depth First Traversal")
    g.dfs(2)
}

class Graph(vertices: Int) {
    private val adjLists: Array<LinkedList<Int>> = Array(vertices){ LinkedList() }
    private val visited: BooleanArray = BooleanArray(vertices)

    init {
        for (i in 0 until vertices) adjLists[i] = LinkedList()
    }
    fun addEdge(src: Int, dest: Int) {
        adjLists[src].add(dest)
    }

    fun dfs(vertex: Int) {
        visited[vertex] = true
        print("$vertex ")
        val ite: Iterator<Int> = adjLists[vertex].listIterator()
        while (ite.hasNext()) {
            val adj = ite.next()
            if (!visited[adj]) dfs(adj)
        }
    }

}