/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.fangnx.dailyarticles;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.fangnx.dailyarticles.handlers.CancelandStopIntentHandler;
import com.fangnx.dailyarticles.handlers.HelloWorldIntentHandler;
import com.fangnx.dailyarticles.handlers.HelpIntentHandler;
import com.fangnx.dailyarticles.handlers.SessionEndedRequestHandler;
import com.fangnx.dailyarticles.handlers.LaunchRequestHandler;
import com.fangnx.dailyarticles.handlers.FallbackIntentHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.5ecfe597-2703-4ba0-97d6-1674ddaba90f")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
