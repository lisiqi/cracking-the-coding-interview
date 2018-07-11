package dataStructure.stacksAndQueues;

public class Queue<T> {
	private class QueueNode<T>{
		private T data;
		private QueueNode<T> next;
		
		public QueueNode(T data){
			this.data = data;
		}

	}
	private QueueNode<T> first;
	private QueueNode<T> last;
	
	public void add(T item){
		QueueNode<T> tmp = new QueueNode<T>(item);
		if(last!=null){
			last.next = tmp;		
		}
		last = tmp;
		
		if(first==null){
			first = last;
		}
		
	}
	
	public T remove(){
		if(first == null) {
			try {
				throw new EmptyException("Error");
			} catch (EmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		T data = first.data;
		first = first.next;
		if(first==null){
			last=null;
		}
		return data;
	}
	
	public T peek(){
		if(first == null) {
			try {
				throw new EmptyException("Error");
			} catch (EmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		return first.data;
	}
	
	public boolean isEmpty(){
		return first==null;
	}
	
	public static void main(String arg[]){
		Queue<Integer> queue = new Queue<Integer>();
		if(queue.isEmpty()){
			System.out.println("Empty");
		}
//		queue.peek();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6);
		System.out.println(queue.remove());
		System.out.println(queue.peek());
		
	}
}
