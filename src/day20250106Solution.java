import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: carl
 * @date: 2025/1/6
 */

public class day20250106Solution {

    public int[] dijkstra(int start, Graph graph) {
        int V = graph.size();
        int[] distTo = new int[V];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        Queue<State> queue = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        queue.offer(new State(start,0));
        while(!queue.isEmpty()){
            State curState = queue.poll();
            int curNodeId = curState.getNodeId();
            int curDisFromStart = curState.getDistFromStart();
            if (curDisFromStart>distTo[curNodeId]){
                continue;
            }
            for (int nextNodeId: graph.neighbors(curNodeId)){
                int distToNextNodeId=curDisFromStart+graph.weight(curNodeId,nextNodeId);
                if (distToNextNodeId<distTo[nextNodeId]){
                    distTo[nextNodeId]=distToNextNodeId;
                    queue.offer(new State(nextNodeId,distToNextNodeId));
                }
            }
        }
        return distTo;
    }

    private class State {
        private int nodeId;
        private int distFromStart;

        public State(int nodeId, int distFromStart) {
            this.nodeId = nodeId;
            this.distFromStart = distFromStart;
        }

        public State() {
        }

        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        public int getDistFromStart() {
            return distFromStart;
        }

        public void setDistFromStart(int distFromStart) {
            this.distFromStart = distFromStart;
        }
    }

}
