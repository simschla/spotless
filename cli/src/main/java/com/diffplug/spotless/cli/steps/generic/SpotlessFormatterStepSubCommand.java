/*
 * Copyright 2024 DiffPlug
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
package com.diffplug.spotless.cli.steps.generic;

import java.util.List;

import javax.annotation.Nonnull;

import com.diffplug.spotless.FormatterStep;
import com.diffplug.spotless.cli.core.SpotlessActionContext;
import com.diffplug.spotless.cli.steps.SpotlessCLIFormatterStep;

import picocli.CommandLine;

@CommandLine.Command(mixinStandardHelpOptions = true)
public abstract class SpotlessFormatterStepSubCommand implements SpotlessCLIFormatterStep {

	@Nonnull
	@Override
	public List<FormatterStep> prepareFormatterSteps(SpotlessActionContext context) {
		return prepareFormatterSteps();
	}

	protected List<FormatterStep> prepareFormatterSteps() {
		throw new IllegalStateException("This method must be overridden or not be called");
	}
}
