/*
 * This file is part of rlp-bootstrapper.
 *
 * rlp-bootstrapper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * rlp-bootstrapper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with rlp-bootstrapper.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.thatgamerblue.gradle.plugins.version;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.tooling.provider.model.ToolingModelBuilder;
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry;

import javax.inject.Inject;

public class VersionModelPlugin implements Plugin
{
	private final ToolingModelBuilderRegistry registry;

	@Inject
	public VersionModelPlugin(ToolingModelBuilderRegistry registry)
	{
		this.registry = registry;
	}

	@Override
	public void apply(Object target)
	{
		registry.register(new CustomToolingModelBuilder());
	}

	private static class CustomToolingModelBuilder implements ToolingModelBuilder
	{
		@Override
		public boolean canBuild(String modelName)
		{
			return modelName.equals(VersionModel.class.getName());
		}

		@Override
		public Object buildAll(String modelName, Project project)
		{
			String ver = (String) project.getVersion();

			return new DefaultModel(ver);
		}
	}
}
