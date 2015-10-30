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
package nz.co.doltech.gwtjui.demo.client.application.interactions.selectable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import nz.co.doltech.gwtjui.interactions.client.ui.Selectable;
import org.gwtbootstrap3.client.ui.html.OrderedList;

import javax.inject.Inject;

public class SelectableView extends ViewWithUiHandlers<SelectableUiHandlers> implements SelectablePresenter.MyView {
    interface Binder extends UiBinder<Widget, SelectableView> {
    }

    @UiField OrderedList list;
    @UiField FocusPanel focus;

    private Selectable selectable;

    @Inject
    SelectableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        selectable = new Selectable(list);
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        Scheduler.get().scheduleDeferred(new Command() {
            @Override
            public void execute() {
                focus.setFocus(true);
                focus.setFocus(false);
            }
        });
    }
}
