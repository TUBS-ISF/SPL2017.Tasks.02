
public aspect Win {
	
	declare precedence: GUI, *, Win;
	
	pointcut run(): execution(void Basic.start());

	after():run(){
		System.out.println(" - Windwos version.");
	}
}
