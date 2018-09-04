package com.wildma.androidfastdevelop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wildma.androidfastdevelop.R;

/**
 * Author       wildma
 * Date         2017/10/5
 * Desc	        ${模拟多线程并发操作同一数据出现线程安全demo}
 */
public class MultiThreadActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);
        multiThread();
    }

    /**
     * 多线程
     */
    private void multiThread() {
        //开启2条线程上传图片
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "线程1").start();
        new Thread(myRunnable, "线程2").start();
    }

    public class MyRunnable implements Runnable {
        private int imgNum = 9;//图片数量

        @Override
        public void run() {
            while (true) {
                synchronized (MyRunnable.class) { //加上synchronized进行同步，保证在同一时刻只能有一个线程能够访问。
                    if (imgNum == 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);//模拟上传一张图片需要1秒钟的时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, Thread.currentThread().getName() + "正在上传图片...，还剩" + imgNum-- + "张图片未上传");
                }
            }
        }
    }

}
