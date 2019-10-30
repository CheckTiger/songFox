package cn.sxh.songfox.eventbus;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 17 :07
 * @project-name: songFox
 */
public class EventBusException extends RuntimeException {

    private static final long serialVersionUID = -2912559384646531479L;

    public EventBusException(String detailMessage) {
        super(detailMessage);
    }

    public EventBusException(Throwable cause) {
        super(cause);
    }

    public EventBusException(String message, Throwable cause) {
        super(message, cause);
    }
}
