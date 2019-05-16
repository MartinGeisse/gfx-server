/**
 * Copyright (c) 2018 Martin Geisse
 * This file is distributed under the terms of the MIT license.
 */
package gfxserver.server;

import gfxserver.protocol.Message;
import gfxserver.server.resource.DefaultResouceLoader;
import gfxserver.server.resource.DefaultResourceManager;
import gfxserver.server.resource.Resources;
import gfxserver.server.system.Launcher;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 */
public class Server implements Runnable {

	private final ConcurrentLinkedQueue<Message> messageInputQueue = new ConcurrentLinkedQueue<>();
	private final ConcurrentLinkedQueue<Message> messageOutputQueue = new ConcurrentLinkedQueue<>();

	public ConcurrentLinkedQueue<Message> getMessageInputQueue() {
		return messageInputQueue;
	}

	public ConcurrentLinkedQueue<Message> getMessageOutputQueue() {
		return messageOutputQueue;
	}

	@Override
	public void run() {
		try {

			// startup
			Launcher launcher = new Launcher();
			launcher.startup();
			Resources.setResourceManager(new DefaultResourceManager(new DefaultResouceLoader()));

			// initialize static state
			GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0, Launcher.SCREEN_WIDTH, Launcher.SCREEN_HEIGHT, 0, -1, 1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			// main loop
			while (!Display.isCloseRequested()) {
				Display.processMessages();
				Keyboard.poll();
				Mouse.poll();
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				handleMessages();
				drawFrame();
				Util.checkGLError();
				Display.update();
			}

			// shutdown
			launcher.shutdown();
			System.exit(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void handleMessages() {
		while (true) {
			Message message = messageInputQueue.poll();
			if (message == null) {
				return;
			}
			// TODO consume message; change state; send output messages
		}
	}

	private void drawFrame() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glColor3f(1.0f, 0.0f, 0.0f);
		GL11.glBegin(GL11.GL_TRIANGLES);
		GL11.glVertex2i(-50, -50);
		GL11.glVertex2i(+50, -50);
		GL11.glVertex2i(+50, +50);
		GL11.glEnd();
	}

}
