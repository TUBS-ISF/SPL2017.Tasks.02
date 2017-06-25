public aspect Linux {
	
	declare precedence: GUI, Linux;
	
	pointcut run(): execution(void Basic.start());

	after():run(){
		System.out.println(" - Linux version.");
	}
}