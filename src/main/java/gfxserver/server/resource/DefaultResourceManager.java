/**
 * Copyright (c) 2018 Martin Geisse
 * This file is distributed under the terms of the MIT license.
 */
package gfxserver.server.resource;

import gfxserver.server.gfx.Font;
import gfxserver.server.gfx.Texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.openal.Audio;

/**
 * Default implementation of {@link ResourceManager}.
 */
public final class DefaultResourceManager implements ResourceManager {

	/**
	 * the loader
	 */
	private final ResourceLoader loader;

	/**
	 * the textures
	 */
	private final Map<String, Texture> textures = new HashMap<>();
	
	/**
	 * the fonts
	 */
	private final Map<String, Font> fonts = new HashMap<>();
	
	/**
	 * the sounds
	 */
	private final Map<String, Audio> sounds = new HashMap<>();

	/**
	 * Constructor.
	 * @param loader the resource loader
	 */
	public DefaultResourceManager(ResourceLoader loader) {
		this.loader = loader;
	}

	/* (non-Javadoc)
	 * @see game.resource.ResourceManager#getTexture(java.lang.String)
	 */
	@Override
	public Texture getTexture(String name) {
		Texture texture = textures.get(name);
		if (texture == null) {
			try {
				texture = loader.loadTexture(name);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			textures.put(name, texture);
		}
		return texture;
	}
	
	/* (non-Javadoc)
	 * @see game.resource.ResourceManager#getFont(java.lang.String)
	 */
	@Override
	public Font getFont(String name) {
		Font font = fonts.get(name);
		if (font == null) {
			try {
				font = loader.loadFont(name);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			fonts.put(name, font);
		}
		return font;
	}

	/* (non-Javadoc)
	 * @see game.resource.ResourceManager#getSound(java.lang.String)
	 */
	@Override
	public Audio getSound(String name) {
		Audio sound = sounds.get(name);
		if (sound == null) {
			try {
				sound = loader.loadSound(name);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			sounds.put(name, sound);
		}
		return sound;
	}

}
