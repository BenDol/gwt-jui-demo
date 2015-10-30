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
package nz.co.doltech.gwtjui.demo.client.application.interactions.draggable;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import nz.co.doltech.gwtjui.demo.client.application.ApplicationPresenter;
import nz.co.doltech.gwtjui.demo.client.place.NameTokens;

public class DraggablePresenter extends Presenter<DraggablePresenter.MyView, DraggablePresenter.MyProxy>
        implements DraggableUiHandlers {
    interface MyView extends View, HasUiHandlers<DraggableUiHandlers> {
    }

    @NameToken(NameTokens.draggable)
    @ProxyCodeSplit
    interface MyProxy extends ProxyPlace<DraggablePresenter> {
    }

    @Inject
    DraggablePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        getView().setUiHandlers(this);
    }

}
