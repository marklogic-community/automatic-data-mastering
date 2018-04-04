/*
 * Copyright 2012-2018 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.hub.deploy.commands;

import com.marklogic.appdeployer.command.CommandContext;
import com.marklogic.appdeployer.command.mimetypes.DeployMimetypesCommand;
import com.marklogic.hub.HubConfig;

import java.io.File;

public class DeployHubMimetypesCommand extends DeployMimetypesCommand {

    private HubConfig config;

    public DeployHubMimetypesCommand(HubConfig config) {
        super();
        this.config = config;
    }

    @Override
    protected File[] getResourceDirs(CommandContext context) {
        return new File[]{config.getHubMimetypesDir().toFile()};
    }
}
