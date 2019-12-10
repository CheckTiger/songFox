package cn.sxh.songfox.pattern.prototypepattern;

/**
 * @package-name: cn.sxh.songfox.pattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/28 0028 : 17 :20
 * @project-name: songFox
 */
public class pattern {

    public static void main(String[] args) {
        WordDocument document = new WordDocument();
        document.setText("原型模式");
        document.addImages("稷下学社");
        document.addImages("东林党");
        document.addImages("新派学士");
        document.addImages("三国争霸");
        document.showDocument();
        System.out.println("===================================");
        WordDocument doc = document.clone();
        doc.showDocument();
        doc.setText("修改原型模式");
        System.out.println("===================================");

        doc.showDocument();
        System.out.println("===================================");
        document.showDocument();
    }
}
