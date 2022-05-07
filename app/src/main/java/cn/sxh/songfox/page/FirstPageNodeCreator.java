package cn.sxh.songfox.page;

public class FirstPageNodeCreator implements FirstPageNodeConfigListener {

    public static final int KEY_ENTRY_LIST = 1;        // 九宫格
    public static final int KEY_ADS_LIST = 2;          // 运营位列表
    public static final int KEY_STOCK_FORUM = 3;       // 论股堂
    public static final int KEY_NEWS = 4;              // 新闻列表
    public static final int KEY_CHARGE_PLACE = 5;      // 收费产品入口
    public static final int KEY_I_WENCAI = 6;          // 爱问财
    public static final int KEY_I_CAIFUXIANFENG = 7;   // 财富先锋
    public static final int KEY_I_ADS = 8;             // 广告banner
    public static final int KEY_I_BK = 12;             // 博客直播
    public static final int KEY_DATACENTER = 13;       // 数据中心
    public static final int KEY_NOTICE_SLIDE = 50;     // 滑动公告
    public static final int KEY_GLOBAL_INDEX = 18;     //全球指数

    @Override
    public void notifyNodeConfigDataArrive(boolean isConfigChange) {
        if (isConfigChange) {
            createAllNode();
            notifyNodeUpdateData(true);
        } else {
            notifyNodeUpdateData(false);
        }
    }

    private void notifyNodeUpdateData(final boolean useCache) {

    }

    private void createAllNode() {

    }
}
