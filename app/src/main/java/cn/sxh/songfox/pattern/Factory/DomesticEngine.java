package cn.sxh.songfox.pattern.Factory;

/**
 * @package-name: cn.sxh.songfox.pattern.Factory
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/17 0017 : 16 :55
 * @project-name: songFox
 */
public class DomesticEngine implements IEngine {
    @Override
    public void engine() {
        System.out.println("国产发动机");
    }
}
