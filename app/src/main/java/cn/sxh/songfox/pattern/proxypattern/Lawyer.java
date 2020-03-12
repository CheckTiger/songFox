package cn.sxh.songfox.pattern.proxypattern;

/**
 * @package-name: cn.sxh.songfox.pattern.proxypattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2020/1/8 0008 : 17 :14
 * @project-name: songFox
 */
public class Lawyer implements ILawsuit {
    private ILawsuit mLawsuit;

    public Lawyer(ILawsuit mLawsuit) {
        this.mLawsuit = mLawsuit;
    }

    @Override
    public void submit() {
        mLawsuit.submit();
    }

    @Override
    public void burden() {
        mLawsuit.burden();
    }

    @Override
    public void defend() {
        mLawsuit.defend();
    }

    @Override
    public void finish() {
        mLawsuit.finish();
    }
}
