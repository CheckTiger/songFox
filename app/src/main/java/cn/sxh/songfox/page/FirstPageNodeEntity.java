package cn.sxh.songfox.page;

public class FirstPageNodeEntity {

    /** 模版ID
     **/
    public int tid;
    /** 支持的客户端版本号
     **/
    public int version;
    /** 模块入口的url
     **/
    public String url;
    /** 模块开关 4个参数，allOpen,allClose,open,close
     * */
    public String op;
    /** 针对用户的权限 用| 隔开
     **/
    public String sid;
    /** 模块内容
     **/
    private String data;
    /** 标题
     **/
    private String title;
    /** 模块所在的位置
     **/
    public int position;
    /**
     * 左上放的图标url
     */
    public String iconurl;
    /**
     * 模块的统计ID
     */
    public String tjID;
    /**
     * 模块title跳转链接地址
     */
    public String titleurl;
    /**
     * 模块起始版本
     */
    public int startversion;
    /**
     * 模块结束版本
     */
    public int endversion;
    /**
     * 模块显示类型
     */
    public int runtype;
    /**
     * 是否为收费快捷方式运营位
     */
    public boolean isShutCut = false;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return tid + "_" + version + "_" + url + "_" + op + "_" + sid + "_" + data + "_"  + position + "_" + iconurl + "_" + tjID;
    }
}
