package cn.sxh.songfox.pattern.Factory;

/**
 * @package-name: cn.sxh.songfox.pattern.Factory
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/17 0017 : 20 :23
 * @project-name: songFox
 */
public class SeniorBrake implements IBrake {
    @Override
    public void brake() {
        System.out.println("高级制动");
    }
}
