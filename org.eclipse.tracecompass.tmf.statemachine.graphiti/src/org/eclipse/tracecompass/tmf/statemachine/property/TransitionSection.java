package org.eclipse.tracecompass.tmf.statemachine.property;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.impl.AbstractFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.tracecompass.tmf.statemachine.util.ConvertStatemachineType;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import statemachine.AbstractTransition;
import statemachine.StateAttribute;
import statemachine.StateAttributeType;
import statemachine.StateChange;
import statemachine.StateValue;
import statemachine.StateValueType;
import statemachine.StatemachineFactory;

public class TransitionSection extends GFPropertySection implements ITabbedPropertyConstants {
	private Text transitionNameText;
	private Table stateChangeTable;
	private Group stateChangeGroup;
	
	private ConvertStatemachineType util = new ConvertStatemachineType();
	
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        final Composite composite = factory.createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        
        GridData gridData;
        
        Label label = factory.createLabel(composite, "Name");
        
        transitionNameText = factory.createText(composite, "");
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        transitionNameText.setLayoutData(gridData);
        
        stateChangeGroup = factory.createGroup(composite, "State Change");
        stateChangeGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateChangeGroup.setLayoutData(gridData);
        
        stateChangeTable = factory.createTable(stateChangeGroup, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.verticalSpan = 2;
        stateChangeTable.setLayoutData(gridData);
        
        Button addStateChangeButton = factory.createButton(stateChangeGroup, "Add/Edit state change", SWT.PUSH);
        Button removeStateChangeButton = factory.createButton(stateChangeGroup, "Remove state change", SWT.PUSH);
        
        addStateChangeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			int stateChangeTableIndex = stateChangeTable.getSelectionIndex();
    			StateChange stateChange = null;
    			if (stateChangeTableIndex >= 0) {
    				if(getTransition() != null) {
    					stateChange = getTransition().getStateChange().get(stateChangeTableIndex);
    				}
    			} else {
    				stateChange = StatemachineFactory.eINSTANCE.createStateChange();
    			}
    			
    			addEditStateChangeDialog(composite.getDisplay(), stateChange);
    		}
		});
        
        removeStateChangeButton.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			int stateChangeTableIndex = stateChangeTable.getSelectionIndex();
    			removeStateChange(stateChangeTableIndex);
    		}
        });
        
        transitionNameText.addModifyListener(new ModifyListener() {

        	@Override
        	public void modifyText(ModifyEvent e) {
        		String newTransitionName = transitionNameText.getText();
        		if (newTransitionName == null) {
        			newTransitionName = "";
        		}
        		String ActualTransitionName = null;
        		if(getTransition() != null) {
        			ActualTransitionName = getTransition().getName();
        		}
    			if (newTransitionName.equals(ActualTransitionName))
    				return;
        		final String transitionName = newTransitionName;
        		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {
        				
        			@Override
        			public void execute(IContext context) {
        				if(getTransition() != null) {
        					getTransition().setName(transitionName);
        				}
        			}
        			
        			@Override
        			public boolean canExecute(IContext context) {
        				return true;
        			}
        		};
        		CustomContext context = new CustomContext();
        		execute(feature, context);
        	}
        });
    }

	@Override
    public void refresh() {
		if(getTransition() != null) {
    		String TransitionName = getTransition().getName();
    		transitionNameText.setText((TransitionName != null) ? TransitionName : "");
    		stateChangeTable.removeAll();
    		for (int i = 0; i < getTransition().getStateChange().size(); i++) {
    			TableItem item = new TableItem(stateChangeTable, 0);
    			item.setText(getTransition().getStateChange().get(i).toString());
    		}
		}
    }
	
    /*private void addStateChangeDialog(Display display) {
        final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        GridLayout dialogLayout = new GridLayout();
        dialogLayout.numColumns = 2;
        dialog.setLayout (dialogLayout);
        dialog.setText("Add state attribute");
        
        Label stateAttributeTypeLabel = new Label(dialog, SWT.NONE);
        stateAttributeTypeLabel.setText("Type");
        
        final Combo typeCombo = new Combo(dialog, SWT.READ_ONLY);
        List<StateAttributeType> stateAttributeList = StateAttributeType.VALUES;
        final String[] stateAttributeTypeString = new String[stateAttributeList.size()];
        for(int i = 0; i < stateAttributeList.size(); i++) {
        	stateAttributeTypeString[i] = stateAttributeList.get(i).toString();
        }
        typeCombo.setItems(stateAttributeTypeString);
        typeCombo.select(1); //TODO : C'est pas bon de faire ça !!!
        
        Label stateAttributeValueLabel = new Label(dialog, SWT.NONE);
        stateAttributeValueLabel.setText("Value");
        
        final Text stateAttributeValueText = new Text(dialog, SWT.SINGLE);
                
        Button addButton = new Button(dialog, SWT.PUSH);
        addButton.setText("Add");
        addButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		//saveStateAttribute(stateAttributeTypeString[typeCombo.getSelectionIndex()], stateAttributeValueText.getText());
        		dialog.close();
        	}
		});
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
		});
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
    }*/
    
	/*private void editStateChangeDialog(Display display, int stateChangeIndex) {
        final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        GridLayout dialogLayout = new GridLayout();
        dialogLayout.numColumns = 2;
        dialog.setLayout (dialogLayout);
        
        AbstractTransition transition = null;
        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {
			transition = (AbstractTransition) Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
        }
		
        Label stateAttributeTypeLabel = new Label(dialog, SWT.NONE);
        stateAttributeTypeLabel.setText("Type");
        
        final Combo typeCombo = new Combo(dialog, SWT.READ_ONLY);
        List<StateAttributeType> stateAttributeList = StateAttributeType.VALUES;
        final String[] stateAttributeTypeString = new String[stateAttributeList.size()];
        for(int i = 0; i < stateAttributeList.size(); i++) {
        	stateAttributeTypeString[i] = stateAttributeList.get(i).toString();
        }
        typeCombo.setItems(stateAttributeTypeString);
        typeCombo.select(1); //TODO : C'est pas bon de faire ça !!!
        
        Label stateAttributeValueLabel = new Label(dialog, SWT.NONE);
        stateAttributeValueLabel.setText("Value");
        
        final Text stateAttributeValueText = new Text(dialog, SWT.SINGLE);
        stateAttributeValueText.setText(transition.getStateChange().get(stateChangeIndex).getStateAttribute().get(0).getValue()); //TODO : Enlever le zero
                
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Ok");
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		//editStateAttribute(stateAttributeTypeString[typeCombo.getSelectionIndex()], stateAttributeValueText.getText());
        		dialog.close();
        	}
		});
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
		});
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}*/
	
	private void addEditStateChangeDialog(Display display, final StateChange stateChange) {
		final Shell dialog = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        dialog.setLayout (new GridLayout(2, false));
        dialog.setText("State change");
        
        GridData gridData;
        
        Group stateAttributeGroup = new Group(dialog, SWT.NONE);
        stateAttributeGroup.setText("State attribute");
        stateAttributeGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateAttributeGroup.setLayoutData(gridData);
        
        Label stateAttributeTypeLabel = new Label(stateAttributeGroup, SWT.NONE);
        stateAttributeTypeLabel.setText("Type");
        
        final Combo stateAttributeTypeCombo = new Combo(stateAttributeGroup, SWT.READ_ONLY);
        stateAttributeTypeCombo.setItems(util.getStateAttributeTypeString());
        stateAttributeTypeCombo.select(1); //TODO : C'est pas bon de faire ça !!!
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateAttributeTypeCombo.setLayoutData(gridData);
        
        Label stateAttributeValueLabel = new Label(stateAttributeGroup, SWT.NONE);
        stateAttributeValueLabel.setText("Value");
        
        final Text stateAttributeValueText = new Text(stateAttributeGroup, SWT.SINGLE);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateAttributeValueText.setLayoutData(gridData);
        
        final Table stateAttributeTable = new Table(stateAttributeGroup, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.verticalAlignment = SWT.FILL;
        gridData.verticalSpan = 2;
        stateAttributeTable.setLayoutData(gridData);
        stateAttributeTable.removeAll();
		for (int i = 0; i < stateChange.getStateAttribute().size(); i++) {
			TableItem item = new TableItem(stateAttributeTable, 0);
			item.setText(stateChange.getStateAttribute().get(i).toString());
		}
		stateAttributeTable.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		int selectedAttributeIndex = stateAttributeTable.getSelectionIndex();
        		StateAttribute selectedAttribute = stateChange.getStateAttribute().get(selectedAttributeIndex);
        		stateAttributeValueText.setText(selectedAttribute.getValue());
        		StateAttributeType selectedAttributeType  = selectedAttribute.getType();
        		int typeComboIndex = util.getIndexFromAttributeType(selectedAttributeType);
        		stateAttributeTypeCombo.select(typeComboIndex);
        	}
		});
		
		Button addButton = new Button(stateAttributeGroup, SWT.PUSH);
		addButton.setText("Add");
		addButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		StateAttribute stateAttribute = StatemachineFactory.eINSTANCE.createStateAttribute();
        		stateAttribute.setValue(stateAttributeValueText.getText());
        		StateAttributeType stateAttributeType = util.getAttributeTypeFromindex(stateAttributeTypeCombo.getSelectionIndex());
        		stateAttribute.setType(stateAttributeType);
        		stateChange.getStateAttribute().add(stateAttribute);
        		TableItem item = new TableItem(stateAttributeTable, 0);
        		item.setText(stateAttribute.toString());
        	}
		});
		
		Button removeButton = new Button(stateAttributeGroup, SWT.PUSH);
		removeButton.setText("Remove");
		removeButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		int stateAttributeTableIndex = stateAttributeTable.getSelectionIndex();
        		stateChange.getStateAttribute().remove(stateAttributeTableIndex);
        		stateAttributeTable.remove(stateAttributeTableIndex);
        	}
		});
		
		// State value
        Group stateValueGroup = new Group(dialog, SWT.NONE);
        stateValueGroup.setText("State value");
        stateValueGroup.setLayout(new GridLayout(2, false));
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        stateValueGroup.setLayoutData(gridData);
        
		Label stateValueTypeLabel = new Label(stateValueGroup, SWT.NONE);
        stateValueTypeLabel.setText("Type");
        
        final Combo stateValueTypeCombo = new Combo(stateValueGroup, SWT.READ_ONLY);
        stateValueTypeCombo.setItems(util.getStateValueTypeString());
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateValueTypeCombo.setLayoutData(gridData);
        
        Label stateValueLabel = new Label(stateValueGroup, SWT.NONE);
        stateValueLabel.setText("Value");
        
        final Text stateValueText = new Text(stateValueGroup, SWT.SINGLE);
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        stateValueText.setLayoutData(gridData);
        
        stateValueTypeCombo.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		if(util.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()).getName().equals(StateValueType.DEFINED_STATE.getName())) {
        			stateValueText.setEnabled(false);
        			if(getTransition() != null) {
        				stateValueText.setText(getTransition().getState().getName());
        			}
        		} else {
        			stateValueText.setEnabled(true);
        			stateValueText.setText("");
        		}
        	}
		});
                
        Button okButton = new Button(dialog, SWT.PUSH);
        okButton.setText("Ok");
        okButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		StateValue stateValue = StatemachineFactory.eINSTANCE.createStateValue();
        		stateValue.setValue(stateValueText.getText());
        		stateValue.setType(util.getStateValueTypeFromindex(stateValueTypeCombo.getSelectionIndex()));
        		stateChange.setStateValue(stateValue);
        		saveStateChange(stateChange);
        		dialog.close();
        	}
		});
        Button cancelButton = new Button(dialog, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected (SelectionEvent e) {
        		dialog.close();
        	}
		});
        
        dialog.pack();
        dialog.open();
		while (!dialog.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
	
	private void saveStateChange(final StateChange stateChange) {
		TableItem stateChangeItem = new TableItem(stateChangeTable, 0);
		stateChangeItem.setText(stateChange.toString());
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				if(getTransition() != null) {
					getTransition().getStateChange().add(stateChange);
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}
	
	private void removeStateChange(final int stateChangeTableIndex) {
		stateChangeTable.remove(stateChangeTableIndex);
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				if(getTransition() != null) {
					getTransition().getStateChange().remove(stateChangeTableIndex);
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}
	
	private AbstractTransition getTransition() {
		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
			if(bo instanceof AbstractTransition) {
				return (AbstractTransition) bo;
			}        					
		}
		return null;
	}
	/*private void saveStateAttribute(String stateAttributeTypeString, String stateAttributeValue) {
		StateAttribute stateAttribute = StatemachineFactory.eINSTANCE.createStateAttribute();
		stateAttribute.setValue(stateAttributeValue);
		StateAttributeType stateAttributeType = StateAttributeType.getByName(stateAttributeTypeString);
		stateAttribute.setType(stateAttributeType);
		
		final StateChange stateChange = StatemachineFactory.eINSTANCE.createStateChange();
		stateChange.getStateAttribute().add(stateAttribute);
		
		IFeature feature = new AbstractFeature(getDiagramTypeProvider().getFeatureProvider()) {

			@Override
			public boolean canExecute(IContext arg0) {
				return true;
			}

			@Override
			public void execute(IContext arg0) {
				PictogramElement pe = getSelectedPictogramElement();
				if (pe != null) {
					Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
					if (bo == null)
						return;
        			if(bo instanceof AbstractTransition) {
        				((AbstractTransition) bo).getStateChange().add(stateChange);
        			}        					
				}
			}
		};
		CustomContext context = new CustomContext();
		execute(feature, context);
	}*/
}
