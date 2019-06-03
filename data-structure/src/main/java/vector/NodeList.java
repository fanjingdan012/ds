package vector;

class NodeList implements List{
	private DLNode header, trailer;
	int size(){}
	boolean isEmpty(){}
	Position first(){}
	Position last(){}
	boolean isFirst(Position p) 
		throws InvalidPositionException
	{}
	boolean isLast(Position p) 
		throws InvalidPositionException
	{}
	Position before(Position p) 
		throws InvalidPositionException
	{}
	Position after(Position p)
			throws InvalidPositionException
	{}
	Position insertFirst(Object e)
	{}
	Position insertLast(Object e)
	{}
	Position insertBefore(Position p,Object e)
			throws InvalidPositionException
	{}
	Position insertAfter(Position p, Object e)
			throws InvalidPositionException
	{}
	Object remove(Position p)
			throws InvalidPositionException
	{}
}
