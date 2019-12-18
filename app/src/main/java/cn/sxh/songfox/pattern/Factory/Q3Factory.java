package cn.sxh.songfox.pattern.Factory;

/**
 * @package-name: cn.sxh.songfox.pattern.Factory
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/17 0017 : 20 :24
 * @project-name: songFox
 */
public class Q3Factory extends CarFactory {
    @Override
    public ITire createTire() {
        return new NormalTire();
    }

    @Override
    public IEngine createEngine() {
        return new DomesticEngine();
    }

    @Override
    public IBrake createBrake() {
        return new NormalBrake();
    }
}
