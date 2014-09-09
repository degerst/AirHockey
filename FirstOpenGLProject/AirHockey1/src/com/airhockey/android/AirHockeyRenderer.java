package com.airhockey.android;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;

import android.opengl.GLSurfaceView.Renderer;

public class AirHockeyRenderer implements Renderer {

	private static final int POSITION_COMPONENT_COUNT = 2;
	
	private static final int BYTES_PER_FLOAT = 4;
	
	private final FloatBuffer vertexData;
	
	public AirHockeyRenderer() {
		float[] tableVerticesWithTriangles = {
				// Triangle 1
				0f,  0f,
				9f, 14f,
				0f, 14f,
				
				// Triangle 2
				0f,  0f,
				9f,  0f,
				9f, 14f,
				
				// Line 1
				0f,  7f,
				9f,  7f,
				
				// Mallets
				4.5f,  2f,
				4.5f, 12f
		};
		vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
		vertexData.put(tableVerticesWithTriangles);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		glViewport(0,  0, width, height);
	}

	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		glClearColor(0.6f, 0.2f, 0.0f, 0.0f);
	}
}
