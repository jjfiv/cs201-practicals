package week11.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DenseDirectedGraph<T> {
    /** List of rows; so index .get(col).get(row) */
    public ArrayList<ArrayList<Boolean>> hasEdge;
    /** Mapping from node indexes to their values. */
    private ArrayList<T> nodesByIndex;
    /** Mapping from node values to their index. */
    private HashMap<T, Integer> nodesByValue;

    public DenseDirectedGraph() {
    }

    public Node<T> lookupOrCreateNode(T value) {
        int next_id = nodesByIndex.size();
        int actual_id = this.nodesByValue.getOrDefault(value, next_id);
        if (actual_id == next_id) {
            // create a new node:
            this.nodesByIndex.add(value);
            this.nodesByValue.put(value, next_id);
        }
        return new Node<T>(this, actual_id, nodesByIndex.get(actual_id));
    }

    public void createEdge(Node<T> n1, Node<T> n2) {

    }

    public static class Node<T> implements GraphNode<T> {
        DenseDirectedGraph<T> graph;
        int index;
        T value;

        public Node(DenseDirectedGraph<T> graph, int index, T value) {
            this.graph = graph;
            this.index = index;
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            // hashCode and equals are about T by proxy;
            // since we keep our T entries unique by graph.nodesByValue;
            return Integer.hashCode(this.index);
        }

        @Override
        public boolean equals(Object anything) {
            if (anything instanceof Node<?>) {
                Node<?> other = (Node<?>) anything;
                return this.graph == other.graph && this.index == other.index;
            }
            return false;
        }

        public Set<GraphNode<T>> neighbors() {
            HashSet<GraphNode<T>> output = new HashSet<>();
            // for (int node_id : graph.edges.getOrDefault(this.index,
            // Collections.emptySet())) {
            // output.add(new Node<>(graph, node_id, graph.nodesByIndex.get(node_id)));
            // }
            return output;
        }
    }
}
