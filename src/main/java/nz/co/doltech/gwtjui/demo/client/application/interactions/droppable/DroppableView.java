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
package nz.co.doltech.gwtjui.demo.client.application.interactions.droppable;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import nz.co.doltech.gwtjui.core.client.JuiQuery;
import nz.co.doltech.gwtjui.interactions.client.events.DropEvent;
import nz.co.doltech.gwtjui.interactions.client.events.DropHandler;
import nz.co.doltech.gwtjui.interactions.client.events.hash.DroppableHash;
import nz.co.doltech.gwtjui.interactions.client.options.Accept;
import nz.co.doltech.gwtjui.interactions.client.options.Containment;
import nz.co.doltech.gwtjui.interactions.client.options.Revert;
import nz.co.doltech.gwtjui.interactions.client.options.RevertType;
import nz.co.doltech.gwtjui.interactions.client.ui.Draggable;
import nz.co.doltech.gwtjui.interactions.client.ui.Droppable;
import org.gwtbootstrap3.client.ui.PanelBody;

import javax.inject.Inject;

public class DroppableView extends ViewWithUiHandlers<DroppableUiHandlers> implements DroppablePresenter.MyView {
    interface Binder extends UiBinder<Widget, DroppableView> {
    }

    @UiField FocusPanel focus;

    // Default Functionality
    @UiField PanelBody container;
    @UiField Draggable draggable;
    @UiField Droppable droppable;

    // Accept
    @UiField PanelBody container2;
    @UiField Draggable draggable2;
    @UiField Droppable droppable2;
    @UiField Draggable draggable3;

    // Prevent Propagation
    @UiField PanelBody container3;
    @UiField Draggable draggable4;
    @UiField Droppable droppable3;
    @UiField Droppable droppable4;
    @UiField Droppable droppable5;
    @UiField Droppable droppable6;

    // Revert Draggable Position
    @UiField PanelBody container4;
    @UiField Draggable draggable5;
    @UiField Draggable draggable6;
    @UiField Droppable droppable7;

    @Inject
    DroppableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
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

        // Default Functionality

        draggable.setContainment(new Containment(container));
        droppable.addDropHandler(new DropHandler<Droppable, DroppableHash>() {
            @Override
            public void onDrop(DropEvent<Droppable, DroppableHash> event) {
                JuiQuery.$(event.getSource())
                    .addClass("ui-state-highlight")
                    .find("p")
                    .html("Dropped!");
            }
        });

        // Accept

        draggable2.setContainment(new Containment(container2));
        draggable3.setContainment(new Containment(container2));

        droppable2.setAccept(new Accept("#draggable"));
        droppable2.setActiveClass("ui-state-hover");
        droppable2.setHoverClass("ui-state-active");
        droppable2.addDropHandler(new DropHandler<Droppable, DroppableHash>() {
            @Override
            public void onDrop(DropEvent<Droppable, DroppableHash> event) {
                JuiQuery.$(event.getSource())
                    .addClass("ui-state-highlight")
                    .find("p")
                    .html("Dropped!");
            }
        });

        // Stop Propagation

        draggable4.setContainment(new Containment(container3));

        DropHandler<Droppable, DroppableHash> dropStopHandler =
                new DropHandler<Droppable, DroppableHash>() {
            @Override
            public void onDrop(DropEvent<Droppable, DroppableHash> event) {
                JuiQuery.$(event.getSource())
                    .addClass("ui-state-highlight")
                    .find("> p")
                    .html("Dropped!");
                event.getNativeEvent().stopPropagation();
            }
        };
        droppable3.addDropHandler(dropStopHandler);
        droppable4.addDropHandler(dropStopHandler);

        DropHandler<Droppable, DroppableHash> dropGreedyHandler =
                new DropHandler<Droppable, DroppableHash>() {
            @Override
            public void onDrop(DropEvent<Droppable, DroppableHash> event) {
                JuiQuery.$(event.getSource())
                    .addClass("ui-state-highlight")
                    .find("> p")
                    .html("Dropped!");
            }
        };
        droppable5.addDropHandler(dropGreedyHandler);
        droppable6.addDropHandler(dropGreedyHandler);

        // Revert Draggable Position

        draggable5.setContainment(new Containment(container4));
        draggable5.setRevert(new Revert(RevertType.VALID));
        draggable6.setContainment(new Containment(container4));
        draggable6.setRevert(new Revert(RevertType.INVALID));

        droppable7.addDropHandler(new DropHandler<Droppable, DroppableHash>() {
            @Override
            public void onDrop(DropEvent<Droppable, DroppableHash> event) {
                JuiQuery.$(event.getSource())
                    .addClass("ui-state-highlight")
                    .find("p")
                    .html("Dropped!");
            }
        });
    }
}
