package cn.sxh.songfox.pattern.prototypepattern;

import java.util.ArrayList;

/**
 * @package-name: cn.sxh.songfox.pattern.prototypepattern
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/11/29 0029 : 10 :14
 * @project-name: songFox
 * 原型模式
 */
public class WordDocument implements Cloneable {

    private String mText;
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument() {
        System.out.println("------->WordDocument的构造函数");
    }

    @Override
    public WordDocument clone() {
        try {
            WordDocument document = (WordDocument) super.clone();
            document.mText = this.mText;
            document.mImages = this.mImages;//浅拷贝

            // TODO: 2019/11/29 0029 原型模式核心问题就是对原始对象进行拷贝，
            //  注意点是深浅拷贝的问题，开发过程中，为了减少错误，建议采取深拷贝，避免操作副本时影响原始对象的问题
//            document.mImages = (ArrayList<String>) this.mImages.clone();此方式即为深拷贝
            return document;
        } catch (Exception e) {

        }
        return null;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void addImages(String mImages) {
        this.mImages.add(mImages);
    }

    public void showDocument() {
        System.out.println("----WordDocument Start---");
        System.out.println("Text :" + mText);
        for (String name : mImages) {
            System.out.println("Image name :" + name);
        }
        System.out.println("----WordDocument End---");
    }
}
