/**
 * Copyright (c) 2018 Martin Geisse
 * This file is distributed under the terms of the MIT license.
 */
package gfxserver.server.system;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

/**
 * Launches the game system.
 */
public class Launcher {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	/**
	 * Starts the game system.
	 * 
	 * @throws Exception on errors during initialization
	 */
	public void startup() throws Exception {

		// include LWJGL native libraries
		LwjglNativeLibraryHelper.prepareNativeLibraries();

		// configure the display
		Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
		Display.create(new PixelFormat(0, 24, 0));

		// initialize the keyboard
		Keyboard.create();
		Keyboard.poll();

		// initialize the mouse
		Mouse.create();
		Mouse.poll();

	}

	/**
	 * Shuts down the game system.
	 */
	public void shutdown() {
		Mouse.destroy();
		Keyboard.destroy();
		Display.destroy();
	}
	
}
