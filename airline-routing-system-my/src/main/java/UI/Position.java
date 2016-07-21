package UI;


public class Position{
	public int x;
	public int y;
	public Position(int x,int y){
		this.x = x;
		this.y = y;
	}
	public Position(){
		
	}
	public void getPosition(int orderOfV,int width,int height, int size){
		
		x = (orderOfV%size) *(width/size)+width/size/2;
		y = (orderOfV/size) *(height/size)+height/size/2;
		
		System.out.println(x+" oooooooooooooooooo"+y);
		return;		
	}

}