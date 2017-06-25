public aspect MacOS {
	
	declare precedence: GUI, MacOS;
	
	pointcut run(): execution(void Basic.start());

	after():run(){
		System.out.println(" - MacOS version.");
	}
}