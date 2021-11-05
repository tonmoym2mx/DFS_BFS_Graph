package com.tonmoy.gakk.meow.dfsandbfs

import java.util.*
import kotlin.collections.ArrayList


/*create a queue Q
mark v as visited and put v into Q
while Q is non-empty
remove the head u of Q
mark and enqueue all (unvisited) neighbours of u*/

fun main(){

    val graph = Graph(
        Vertex(0) to Vertex(1),
        Vertex(0) to Vertex(2),
        Vertex(1) to Vertex(2),
        Vertex(2) to Vertex(0),
        Vertex(2) to Vertex(3),
        Vertex(3) to Vertex(3),

    )

    val bfs = BreadthFirstSearch(graph)
    bfs.breadthFirstSearch(Vertex(2))




}

class BreadthFirstSearch<T>(val graph: Graph<T>){
    private val queue:Queue<Vertex<T>> = LinkedList()
    val visitedVertex:MutableMap<Vertex<T>,Boolean> = HashMap()

    private fun setVisited(vertex: Vertex<T>){
        visitedVertex[vertex] = true
    }
    private fun isVisited(vertex: Vertex<T>): Boolean {
        return visitedVertex[vertex] == true
    }

    fun breadthFirstSearch(start:Vertex<T>){
        var startVertex = start
        setVisited(startVertex)
        queue.add(startVertex)

        println("Following is Breadth First Traversal (starting from vertex ${start.data})")
        while (queue.isNotEmpty()){
            startVertex = queue.poll()?:break
            print("$startVertex ")
            graph.getNeighbors(startVertex).forEach { vertex ->
                if(!isVisited(vertex)){
                   setVisited(vertex)
                   queue.add(vertex)
                }
            }
        }


        println("\nVisited Vertex are : ${visitedVertex.map { it.key.data }}")
    }
}
class Graph<T>(private vararg val edgeList: Edge<T> ){
    fun getNeighbors(vertex: Vertex<T>): List<Vertex<T>> {
        return edgeList.filter { it.source == vertex }.map { it.destination }
    }
}

data class Vertex<T>(val data:T){
    infix fun to(destination:Vertex<T>) = Edge(this,destination)
    override fun toString() = data.toString()
}
data class Edge<T>(
    val source: Vertex<T>,
    val destination: Vertex<T>
)
