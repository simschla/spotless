/*
 * Copyright 2016-2024 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.spotless.maven.json;

import com.diffplug.spotless.biome.BiomeFlavor;
import com.diffplug.spotless.maven.generic.AbstractBiome;

/**
 * Biome formatter step for JSON.
 */
public class BiomeJson extends AbstractBiome {
	public BiomeJson() {
		super(BiomeFlavor.BIOME);
	}

	@Override
	protected String getLanguage() {
		return "json";
	}
}
