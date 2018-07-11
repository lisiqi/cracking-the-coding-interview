package dataStructure.stacksAndQueues;

public class MyStack<T> {
	private class StackNode<T>{
		private T data;
		private StackNode<T> next;
		
		public StackNode(T data){
			this.data = data;
		}
	}
		
	private StackNode<T> top;
	public T pop(){
		if(top == null)
			try {
				throw new EmptyException("Error");
			} catch (EmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		top = top.next;
		return top.data;
	}
	public T peek(){
		if(top == null)
			try {
				throw new EmptyException("Error");
			} catch (EmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return top.data;
	}
	
	public void push(T item){
		StackNode<T> tmp = top;
		top = new StackNode<T>(item);
		top.next = tmp;	
	}
	
	public boolean isEmpty(){
		return top==null;
	}
	
	public static void main(String arg[]){
		MyStack<Integer> stack = new MyStack<Integer>();
		if(stack.isEmpty()){
			System.out.println("Empty");
		}
//		stack.pop();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.pop();
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
	}

}
