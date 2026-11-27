package org.eclipse.fordiac.ide.efg.handler;

import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ILog;
import org.eclipse.fordiac.ide.model.libraryElement.Application;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class efgConversionHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Optional<FBNetwork> fbNetwork = getFBNetwork(event);
		if(fbNetwork.isEmpty()) return null;
		 
		
		return null;
	}
	
	
	private Optional<FBNetwork> getFBNetwork(ExecutionEvent event) {
		IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event); 
		Object firstSelection = selection.getFirstElement();
		if(firstSelection == null) {
			ILog.get().warn("EFG: handler invoked with empty selection");
			return  Optional.empty();
		}
		if(!(firstSelection instanceof Application app)) { //visibleWhen in plugin.xml guarantees selection is an Application
			ILog.get().warn("EFG: unexpected selection type: " + firstSelection.getClass().getName());
			return Optional.empty();
		}
		return Optional.of(app.getFBNetwork());
	}

}
