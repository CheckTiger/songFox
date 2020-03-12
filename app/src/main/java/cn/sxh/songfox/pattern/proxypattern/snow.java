package cn.sxh.songfox.pattern.proxypattern;

/**
 * @package-name: cn.sxh.songfox.pattern.proxypattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/1/8 0008 : 17 :07
 * @project-name: songFox
 */
public class snow implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("老板拖欠工资|申请仲裁");
    }

    @Override
    public void burden() {
        System.out.println("这是合同书和一年的银行流水");
    }

    @Override
    public void defend() {
        System.out.println("证据确凿，无需论辩");
    }

    @Override
    public void finish() {
        System.out.println("诉讼成功！判决老板七日之内结算工资");
    }
}
