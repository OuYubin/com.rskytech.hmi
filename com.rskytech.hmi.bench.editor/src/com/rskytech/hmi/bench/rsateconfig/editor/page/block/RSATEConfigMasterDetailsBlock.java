package com.rskytech.hmi.bench.rsateconfig.editor.page.block;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.rskytech.hmi.bench.rsateconfig.editor.page.provider.RSATEConfigDetailPageProvider;
import com.rskytech.hmi.bench.rsateconfig.editor.provider.RSATEConfigContentProvider;
import com.rskytech.hmi.bench.rsateconfig.editor.provider.RSATEConfigLabelProvider;
import com.rskytech.hmi.common.editor.page.IRskyCommonFormPage;
import com.rskytech.hmi.common.editor.page.block.AbstractRskyCommonMasterDetailsBlock;

/**
 * 
 * @author robin
 *
 */
public class RSATEConfigMasterDetailsBlock extends AbstractRskyCommonMasterDetailsBlock {

	RSATEConfigDetailPageProvider rsATEConfigDetailPageProvider;
	
	public RSATEConfigMasterDetailsBlock(IRskyCommonFormPage rskyCommonFormPage) {
		super(rskyCommonFormPage);
		this.rsATEConfigDetailPageProvider=new RSATEConfigDetailPageProvider();
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		FormToolkit formToolkit = managedForm.getToolkit();
		Section section = formToolkit.createSection(parent, Section.EXPANDED | Section.TITLE_BAR);
		section.setText("硬件资源列表");
		section.clientVerticalSpacing = 0;
		section.marginWidth = 5;
		section.marginHeight = 5;

		Composite composite = formToolkit.createComposite(section, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		// gridLayout.verticalSpacing = 0;
		//gridLayout.horizontalSpacing = 0;
		composite.setLayout(gridLayout);

		Tree tree = new Tree(composite, SWT.FULL_SELECTION);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.widthHint = SWT.DEFAULT;
		gridData.heightHint = SWT.DEFAULT;
		tree.setLayoutData(gridData);

		TreeViewer treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(getRskyCommonFormPage().getAdapterFactory()));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(getRskyCommonFormPage().getAdapterFactory()));
		Resource resource = this.getRskyCommonFormPage().getResource();
		Object object = resource.getContents().get(0);
		treeViewer.setInput(object);
		treeViewer.expandToLevel(2);
		
		final SectionPart sectionPart = new SectionPart(section);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(sectionPart, event.getSelection());

			}
		});
		
		section.setClient(composite);
		managedForm.addPart(sectionPart);
	}
	
	

	@Override
	public void createContent(IManagedForm managedForm, Composite parent) {
		super.createContent(managedForm, parent);
		this.sashForm.setWeights(new int[]{40,60});
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.setPageProvider(rsATEConfigDetailPageProvider);
	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {

	}

}