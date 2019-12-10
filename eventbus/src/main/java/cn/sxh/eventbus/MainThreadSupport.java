package cn.sxh.eventbus;

import android.os.Looper;

/**
 * @package-name: cn.sxh.songfox.eventbus
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/30 0030 : 20 :38
 * @project-name: songFox
 */
public interface MainThreadSupport {

    boolean isMainThread();

    Poster createPoster(EventBus eventBus);

    class AndroidHandlerMainThreadSupport implements MainThreadSupport {

        private final Looper looper;

        public AndroidHandlerMainThreadSupport(Looper looper) {
            this.looper = looper;
        }

        @Override
        public boolean isMainThread() {
            return looper == Looper.myLooper();
        }

        @Override
        public Poster createPoster(EventBus eventBus) {
            return new HandlerPoster(eventBus, looper, 10);
        }
    }

}
