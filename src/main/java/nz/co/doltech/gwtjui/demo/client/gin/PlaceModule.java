/*
 * Copyright 2015 Doltech Systems Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package nz.co.doltech.gwtjui.demo.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import nz.co.doltech.gwtjui.demo.client.application.ApplicationModule;
import nz.co.doltech.gwtjui.demo.client.place.NameTokens;

public class PlaceModule extends AbstractGinModule {

    @Override
    protected void configure() {
        install(new DefaultModule.Builder()
            .defaultPlace(NameTokens.home)
            .unauthorizedPlace(NameTokens.home)
            .errorPlace(NameTokens.home)
            .tokenFormatter(RouteTokenFormatter.class)
            .build());

        install(new ApplicationModule());
    }
}
