package single;

/**
 * 用枚举实现单例 
 * 
 * 将枚举的静态成员变量作为单例的实例
 * 
 * 用户只能直接访问实例EnumSingle.SINGLE，只是牺牲了静态工厂方法的有点，无法实现懒加载
 *
 * @author lt
 *
 */
public enum EnumSingle {

	SINGLE;
	
	public EnumSingle getInstance() {
		return SINGLE;
	}
	
	
}
