package cn.sxh.songfox.pattern.Factory;

/**
 * @package-name: cn.sxh.songfox.pattern.Factory
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/12/17 0017 : 16 :52
 * @project-name: songFox
 */
public class NormalTire implements ITire {
    @Override
    public void tire() {
        System.out.println("普通轮胎");
    }
}
