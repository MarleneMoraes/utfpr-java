class UseMain { 
	public static void main(String args[]) {
		Thread thrd;
		thrd = Thread.currentThread();
		System.out.println("Main thread is called: " + thrd.getName());
		System.out.println("Priority: " + thrd.getPriority()); System.out.println();
		System.out.println("Setting name and priority.\n"); 
		thrd.setName("Thread #1"); 
		thrd.setPriority(Thread.NORM_PRIORITY+3);
		System.out.println("Main thread is now called: " + thrd.getName());
		System.out.println("Priority is now: " + thrd.getPriority());
	}
}