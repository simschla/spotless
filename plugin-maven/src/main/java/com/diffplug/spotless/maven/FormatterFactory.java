/*
 * Copyright 2016 DiffPlug
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
package com.diffplug.spotless.maven;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.maven.plugins.annotations.Parameter;

import com.diffplug.spotless.FormatExceptionPolicyStrict;
import com.diffplug.spotless.Formatter;
import com.diffplug.spotless.FormatterStep;
import com.diffplug.spotless.LineEnding;

public abstract class FormatterFactory {
	@Parameter
	protected String encoding;

	@Parameter
	protected LineEnding lineEndings;

	@Parameter
	protected List<FormatterStepFactory> steps;

	public abstract Set<String> fileExtensions();

	public final Formatter newFormatter(List<File> filesToFormat, FormatterConfig config) {
		Charset formatterEncoding = encoding(config);
		LineEnding formatterLineEndings = lineEndings(config);
		LineEnding.Policy formatterLineEndingPolicy = formatterLineEndings.createPolicy(config.getBaseDir(), () -> filesToFormat);

		FormatterStepConfig stepConfig = stepConfig(formatterEncoding, config);

		List<FormatterStep> formatterSteps = steps.stream()
				.filter(Objects::nonNull) // all unrecognized steps from XML config appear as nulls in the list
				.map(factory -> factory.newFormatterStep(stepConfig))
				.collect(toList());

		return Formatter.builder()
				.encoding(formatterEncoding)
				.lineEndingsPolicy(formatterLineEndingPolicy)
				.exceptionPolicy(new FormatExceptionPolicyStrict())
				.steps(formatterSteps)
				.rootDir(config.getBaseDir().toPath())
				.build();
	}

	private Charset encoding(FormatterConfig config) {
		return Charset.forName(encoding == null ? config.getEncoding() : encoding);
	}

	private LineEnding lineEndings(FormatterConfig config) {
		return lineEndings == null ? config.getLineEndings() : lineEndings;
	}

	private static FormatterStepConfig stepConfig(Charset encoding, FormatterConfig config) {
		return new FormatterStepConfig(encoding, config.getProvisioner());
	}
}
