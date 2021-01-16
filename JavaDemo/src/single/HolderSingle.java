package single;

/**
 * 
 * 静态内部类的方式
 * 
 * 如果即希望利用饿汉模式中静态变量的方便和线程安全，又希望通过懒加载规避资源浪费，
 * Holder模式满足了这两点要求：核心仍然是静态变量，足够方便和线程安全；通过静态的Holder类持有真正实例，间接实现了懒加载 
 *
 * @author lt
 *
 */
public class HolderSingle {
	
	private static class HolderSingleHodler {
		
		private static HolderSingle holderSingle = new HolderSingle();
		
		private HolderSingleHodler() {
			
		}
	}
	
	private HolderSingle() {
		
	}
	
	public HolderSingle getInstance() {
		return HolderSingleHodler.holderSingle;
	}
}
