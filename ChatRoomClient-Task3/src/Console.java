/**
 * A console mode to save resource.
 * Still thinking about how to combine this with {@link Basic} without making output in {@link Basic}.
 * Reserved.
 * 
 * @author Longxin Li
 *
 */
public class Console {

	/**
	 * Basic functions, deliver message(response) to CommandLine.
	 */
	Basic basic;
	
	public Console(){
		basic = new Basic();
	}
}
