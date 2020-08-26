import java.lang.Math;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnionFind {

    // TODO - Add instance variables?
    int[] uset;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        uset = new int[n];
        for(int i = 0; i < uset.length; i++){
            uset[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex >= uset.length || vertex < 0){
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return uset[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if(!(connected(v1, v2) || v1 == v2)){
            if(sizeOf(v1) > sizeOf(v2)){
                uset[find(v1)] += uset[find(v2)];
                uset[find(v2)] = find(v1);
            }else{
                uset[find(v2)] += uset[find(v1)];
                uset[find(v1)] = find(v2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        //find root
        int root = vertex;
        while(parent(root) > -1){
            root = parent(root);
        }
        //path compression
        int currentVertex = vertex;
        while(parent(currentVertex) != root && parent(currentVertex) > -1){
            vertex = parent(vertex);
            uset[currentVertex] = root;
            currentVertex = vertex;
        }
        return root;
    }

    public static void main(String[] args){
        UnionFind uf = new UnionFind(6);
        int[] array = {2 , 0, -4, -1, 2, -1};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);

        int a = uf.find(2);
//        boolean res = uf.connected(1, 2);
//        System.out.println(res);
//        System.out.println(uf.connected(1, 4));
//        System.out.println(uf.connected(1, 3));
//        System.out.println(uf.connected(1, 5));
    }

}
