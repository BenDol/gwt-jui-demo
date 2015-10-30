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
package nz.co.doltech.gwtjui.demo.client.application.interactions.sortable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import nz.co.doltech.gwtjui.core.client.events.ChangeEvent;
import nz.co.doltech.gwtjui.core.client.events.ChangeHandler;
import nz.co.doltech.gwtjui.core.client.events.OverEvent;
import nz.co.doltech.gwtjui.core.client.events.OverHandler;
import nz.co.doltech.gwtjui.core.client.events.ReceiveEvent;
import nz.co.doltech.gwtjui.core.client.events.ReceiveHandler;
import nz.co.doltech.gwtjui.core.client.events.UpdateEvent;
import nz.co.doltech.gwtjui.core.client.events.UpdateHandler;
import nz.co.doltech.gwtjui.interactions.client.events.SortEvent;
import nz.co.doltech.gwtjui.interactions.client.events.SortHandler;
import nz.co.doltech.gwtjui.interactions.client.events.hash.SortableHash;
import nz.co.doltech.gwtjui.interactions.client.ui.Sortable;

import javax.inject.Inject;

import static nz.co.doltech.gwtjui.core.client.JuiQuery.$;

public class SortableView extends ViewWithUiHandlers<SortableUiHandlers> implements SortablePresenter.MyView {
    interface Binder extends UiBinder<Widget, SortableView> {
    }

    @UiField HTMLPanel list;

    private Sortable sortable;

    @Inject
    SortableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        sortable = new Sortable(list);

        sortable.addOverHandler(new OverHandler<Sortable, SortableHash>() {
            @Override
            public void onOver(OverEvent<Sortable, SortableHash> event) {
                GWT.log("onOver: " + event.getHash().getPos());
            }
        });

        sortable.addSortHandler(new SortHandler<Sortable, SortableHash>() {
            @Override
            public void onSort(SortEvent<Sortable, SortableHash> event) {
                GWT.log("onSort: " + event.getHash().getPos());
            }
        });

        sortable.addChangeHandler(new ChangeHandler<Sortable, SortableHash>() {
            @Override
            public void onChange(ChangeEvent<Sortable, SortableHash> event) {
                GWT.log("onChange: " + event.getHash().getPos());
            }
        });

        sortable.addReceiveHandler(new ReceiveHandler<Sortable, SortableHash>() {
            @Override
            public void onReceive(ReceiveEvent<Sortable, SortableHash> event) {
                GWT.log("onReceive: " + event.getHash().getPos());
            }
        });

        sortable.addUpdateHandler(new UpdateHandler<Sortable, SortableHash>() {
            @Override
            public void onUpdate(UpdateEvent<Sortable, SortableHash> event) {
                GWT.log("onUpdate: " + $(event.getHash().getItem()).index());
            }
        });
    }
}
