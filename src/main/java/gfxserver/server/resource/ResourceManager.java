/**
 * Copyright (c) 2018 Martin Geisse
 * This file is distributed under the terms of the MIT license.
 */
package gfxserver.server.resource;

import gfxserver.server.gfx.Font;
import gfxserver.server.gfx.Texture;

import org.newdawn.slick.openal.Audio;

/**
 * Keeps track of the game resources (graphics files, sounds, ...)
 */
public interface ResourceManager {

	/**
	 * Returns a texture by name.
	 * 
	 * @param name the name of the texture
	 * @return the texture
	 */
	public Texture getTexture(final String name);

	/**
	 * Returns a font by name.
	 * 
	 * @param name the name of the font
	 * @return the font
	 */
	public Font getFont(final String name);

	/**
	 * Returns a sound by name.
	 * 
	 * @param name the name of the sound
	 * @return the sound
	 */
	public Audio getSound(final String name);

}
