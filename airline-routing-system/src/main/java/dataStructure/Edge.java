package dataStructure;

public class Edge {
	Vertex Node;
	Vertex nextNode;
     int weight;
     
	
     public Edge(Vertex Node, Vertex nextNode, int weight) {
         this.Node = Node;
         this.nextNode = nextNode;
         this.weight = weight;
     }
 
      public Vertex getNode() {
         return Node;
     }
 
      public void setNode(Vertex Node) {
         this.Node = Node;
     }
 
      public Vertex getnextNode() {
          return nextNode;
      }
  
       public void setnextNode(Vertex nextNode) {
          this.nextNode = nextNode;
      }
  
      public int getWeight() {
         return weight;
     }
 
      public void setWeight(int weight) {
         this.weight = weight;
     }

}
