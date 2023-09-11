package com.limelight.gl;

import android.opengl.GLSurfaceView;
import android.os.Build;

import com.limelight.LimeLog;
import com.limelight.PcView;
import com.limelight.preferences.GlPreferences;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ShaderRenderer implements GLSurfaceView.Renderer {
    final private GlPreferences glPrefs;
    final private PcView view;

    public ShaderRenderer(PcView view, GlPreferences glPrefs) {
        this.view = view;
        this.glPrefs = glPrefs;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // Save the GLRenderer string so we don't need to do this next time
        glPrefs.glRenderer = gl10.glGetString(GL10.GL_RENDERER);
        glPrefs.savedFingerprint = Build.FINGERPRINT;
        glPrefs.writePreferences();

        LimeLog.info("Fetched GL Renderer: " + glPrefs.glRenderer);

        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.completeOnCreate();
            }
        });
    }
    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
    }
}