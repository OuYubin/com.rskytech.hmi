package com.rskytech.hmi.icd.model.editor.page.block;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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

import com.rskytech.hmi.common.editor.page.IRskyCommonFormPage;
import com.rskytech.hmi.common.editor.page.block.AbstractRskyCommonMasterDetailsBlock;
import com.rskytech.hmi.icd.common.model.impl.ICD;
import com.rskytech.hmi.icd.common.model.manager.RSICDConfigModelManager;
import com.rskytech.hmi.icd.model.editor.page.block.provider.RSICDConfigContentProvider;
import com.rskytech.hmi.icd.model.editor.page.block.provider.RSICDConfigLabelProvider;

/**
 * 
 * @author robin
 *
 */
public class RSICDConfigMasterDetailsBlock extends AbstractRskyCommonMasterDetailsBlock {

	public RSICDConfigMasterDetailsBlock(IRskyCommonFormPage rskyCommonFormPage) {
		super(rskyCommonFormPage);
	}

	@Override
	protected void createMasterPart(IManagedForm managedForm, Composite parent) {
		FormToolkit formTookit = managedForm.getToolkit();
		Section section = formTookit.createSection(parent, Section.EXPANDED | Section.TITLE_BAR);
		section.setText("ICD资源配置");
		section.marginHeight = 5;
		section.marginWidth = 5;

		Composite client = formTookit.createComposite(section, SWT.WRAP);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		client.setLayout(gridLayout);
		formTookit.paintBordersFor(client);

		Tree tree = new Tree(client, SWT.FULL_SELECTION|SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.widthHint = 20;
		gridData.heightHint = 20;
		tree.setLayoutData(gridData);

		TreeViewer treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new RSICDConfigContentProvider());
		// RSATEConfigStyledLabelProvider rsATEConfigLabelProvider = new
		// RSATEConfigStyledLabelProvider();
		// RSATEConfigModelLabelDecorator rsATEConfigModelLabelDecorator = new
		// RSATEConfigModelLabelDecorator();
		// treeViewer.setLabelProvider(
		// new RSATEConfigStyledCellLabelProvider(rsATEConfigLabelProvider,
		// rsATEConfigModelLabelDecorator, null));
		treeViewer.setLabelProvider(new RSICDConfigLabelProvider());

		Resource resource = this.getRskyCommonFormPage().getResource();
		EObject eObject = resource.getContents().get(0);
		ICD icd = RSICDConfigModelManager.createICD(eObject);
		// Bench bench = RSATEConfigModelManager.createBench((EObject) object);
		List list = new ArrayList();
		list.add(icd);
		treeViewer.setInput(list);
		treeViewer.expandToLevel(3);

		section.setClient(client);
		SectionPart sectionPart = new SectionPart(section);
		managedForm.addPart(sectionPart);

	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createContent(IManagedForm managedForm, Composite parent) {
		// TODO Auto-generated method stub
		super.createContent(managedForm, parent);
		this.sashForm.setWeights(new int[] { 35, 65 });
	}

}
