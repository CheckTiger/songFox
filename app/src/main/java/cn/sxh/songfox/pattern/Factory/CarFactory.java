package cn.sxh.songfox.pattern.Factory;

/**
 * @package-name: cn.sxh.songfox.pattern.Factory
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/17 0017 : 16 :45
 * @project-name: songFox
 * 抽象工厂模式
 */
public abstract class CarFactory {
    /**
     * 轮胎
     * @return
     */
    public abstract ITire createTire();
    /**
     * 发动机
     * @return
     */
    public abstract IEngine createEngine();
    /**
     * 制动系统
     * @return
     */
    public abstract IBrake createBrake();
}
